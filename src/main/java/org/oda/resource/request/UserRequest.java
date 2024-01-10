package org.oda.resource.request;

import jakarta.validation.constraints.NotBlank;
import org.oda.resource.request.validation.AlreadyRegistered;

public class UserRequest {
    @NotBlank(message="Name may not be blank")
    private String name;
    @NotBlank(message="Email may not be blank")
    @AlreadyRegistered
    private String email;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
