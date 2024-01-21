package org.oda.resource.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.ext.Provider;
import org.oda.resource.type.LinkEnum;

import java.lang.reflect.Method;
import java.net.URI;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Provider
public class CustomResponseFilter implements ContainerResponseFilter {

    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) {
        try {
            URI uri = null;
            String functionName = resourceInfo.getResourceMethod().getName();
            Object entity = containerResponseContext.getEntity();
            Method getIdMethod = entity.getClass().getMethod("getId");

            if (functionName.equals("create")) {
                UUID id = (UUID) getIdMethod.invoke(entity);
                uri = containerRequestContext.getUriInfo().getAbsolutePathBuilder().path(id.toString()).build();
            } else {
                uri = containerRequestContext.getUriInfo().getAbsolutePathBuilder().build();
            }

            Method[] methods = resourceInfo.getResourceClass().getMethods();
            List<LinkEnum> linksEnum = filterLinks(functionName, methods);

            if (entity == null) {
                containerResponseContext.setEntity(new ResponseWithHateoas(uri, linksEnum));
            } else {
                containerResponseContext.setEntity(new ResponseWithHateoas(containerResponseContext.getEntity(), uri, linksEnum));
            }
        } catch (Exception ignored) {
        }
    }

    private List<LinkEnum> filterLinks(String method, Method[] resourceMethods) {
        List<LinkEnum> links = new ArrayList<>();
        for (Method resourceMethod : resourceMethods) {
            if (resourceMethod.getDeclaringClass().getPackageName().equals("org.oda.resource") && !resourceMethod.getName().equals(method)) {
                switch (resourceMethod.getName()) {
                    case "find" -> links.add(LinkEnum.SELF);
                    case "update" -> links.add(LinkEnum.UPDATE);
                    case "delete" -> links.add(LinkEnum.DELETE);
                }
            }
        }
        return links;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private class ResponseWithHateoas {
        private final Object data;
        private final Map<String, Map<String, String>> links;
        private final Instant requestDateTime;

        public <T> ResponseWithHateoas(T data, URI uri, List<LinkEnum> linksEnum) {
            this.data = data;
            this.links = buildLinks(uri, linksEnum);
            this.requestDateTime = Instant.now();
        }

        public ResponseWithHateoas(URI uri, List<LinkEnum> linksEnum) {
            this.data = null;
            this.links = buildLinks(uri, linksEnum);
            this.requestDateTime = Instant.now();
        }

        public Object getData() {
            return data;
        }

        public Map<String, Map<String, String>> getLinks() {
            return links;
        }

        public Instant getRequestDateTime() {
            return requestDateTime;
        }


        private Map<String, Map<String, String>> buildLinks(URI uri, List<LinkEnum> linksEnum) {
            return linksEnum.stream()
                    .collect(Collectors.toMap(LinkEnum::getDescription, linkEnum -> buildLink(linkEnum, uri.toString())));
        }

        private Map<String, String> buildLink(LinkEnum linkEnum, String url) {
            Map<String, String> attributesLink = new HashMap<>();
            attributesLink.put("href", url);
            attributesLink.put("type", linkEnum.getMethod());
            return attributesLink;
        }
    }
}
