package com.syxbruno.CrudClientRegistration.dto.user;

import com.syxbruno.CrudClientRegistration.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDTO {

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