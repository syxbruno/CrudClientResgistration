package com.syxbruno.CrudClientRegistration.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginUserDTO {

    @NotBlank(message = "The login is mandatory")
    @Size(min = 3, max = 50, message = "The login must be between 3 and 100 characters")
    private String login;

    @NotBlank(message = "The password is mandatory")
    @Size(min = 3, max = 50, message = "The password must be between 3 and 50 characters")
    private String password;
}
