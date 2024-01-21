package org.oda.resource.request;

import jakarta.validation.constraints.NotBlank;
import org.oda.resource.request.validation.AlreadyRegisteredEmailUser;

public class UserRequest {
    @NotBlank(message="Name may not be blank")
    private String name;
    @NotBlank(message="Email may not be blank")
    @AlreadyRegisteredEmailUser
    private String email;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
