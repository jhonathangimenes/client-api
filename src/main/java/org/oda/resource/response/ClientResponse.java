package org.oda.resource.response;

import org.oda.type.LinkEnum;

import java.net.URI;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ClientResponse<T> {
    private final T data;
    private final Map<String, Map<String, String>> links;
    private final Instant requestDateTime;

    public ClientResponse(T data, URI uri, List<LinkEnum> linksEnum) {
        this.data = data;
        this.links = buildLinks(uri, linksEnum);
        this.requestDateTime = Instant.now();
    }

    public T getData() {
        return data;
    }

    public Map<String, Map<String, String>> getLinks() {
        return links;
    }

    public Instant getRequestDateTime() {
        return requestDateTime;
    }

    private Map<String, Map<String, String>> buildLinks (URI uri, List<LinkEnum> linksEnum) {
        return linksEnum.stream().collect(Collectors.toMap(LinkEnum::getDescription, linkEnum -> buildLink(linkEnum, uri.toString())));
    }

    private Map<String, String> buildLink(LinkEnum linkEnum, String url) {
        Map<String, String> attributesLink = new HashMap<>();
        attributesLink.put("href", url);
        attributesLink.put("type", linkEnum.getMethod());
        return attributesLink;
    }
}
