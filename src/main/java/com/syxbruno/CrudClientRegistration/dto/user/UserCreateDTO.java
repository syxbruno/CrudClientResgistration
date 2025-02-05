package com.syxbruno.CrudClientRegistration.dto.user;

import com.syxbruno.CrudClientRegistration.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "This dto class is aimed at creating a user, it receives all the attributes that a user has except an id(which will be generated in the database automatically)")

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDTO {

    @NotBlank(message = "The name is mandatory")
    @Size(min = 3, max = 100, message = "The name must be between 3 and 100 characters")
    private String name;

    @NotBlank(message = "The login is mandatory")
    @Size(min = 3, max = 50, message = "The login must be between 3 and 100 characters")
    private String login;

    @NotBlank(message = "The password is mandatory")
    @Size(min = 3, max = 50, message = "The password must be between 3 and 50 characters")
    private String password;

    private Role role;
}