package com.syxbruno.CrudClientRegistration.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientResponseDTO {

    @NotNull(message = "The id is mandatory")
    private Long id;

    @NotBlank(message = "The name is mandatory")
    @Size(min = 3, max = 100, message = "The name must be between 3 and 100 characters")
    private String name;

    @NotBlank(message = "The email is mandatory")
    @Email(message = "Email invalid")
    private String email;
}
