package com.syxbruno.CrudClientRegistration.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Schema(description = "This dto class is aimed at a user's login, it has the necessary attributes to perform a login")

@Data
@Builder
public class UserLoginDTO {

    @NotBlank(message = "The login is mandatory")
    @Size(min = 3, max = 50, message = "The login must be between 3 and 100 characters")
    private String login;

    @NotBlank(message = "The password is mandatory")
    @Size(min = 3, max = 50, message = "The password must be between 3 and 50 characters")
    private String password;
}
