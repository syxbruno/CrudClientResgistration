package com.syxbruno.CrudClientRegistration.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Schema(description = "This dto class was created to work together with the 'auth/admin/delete' endpoint, making it easier to delete a user")

@Data
@Builder
public class UserDeleteDTO {

    @NotBlank(message = "The login is mandatory")
    @Size(min = 3, max = 50, message = "The login must be between 3 and 100 characters")
    private String login;
}
