package com.syxbruno.CrudClientRegistration.dto.client;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Schema(description = "This dto class is aimed at creating a client, it receives all the attributes that a client has except an id (which will be generated in the database automatically)")

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientCreateDTO {
    @NotBlank(message = "The name is mandatory")
    @Size(min = 3, max = 100, message = "The name must be between 3 and 100 characters")
    private String name;

    @NotBlank(message = "The CPF is mandatory")
    @CPF(message = "CPF invalid")
    private String cpf;

    @NotBlank(message = "The password is mandatory")
    @Size(min = 6, max = 50, message = "The password must be between 6 and 50 characters")
    private String password;

    @NotBlank(message = "The email is mandatory")
    @Email(message = "Email invalid")
    private String email;

    @NotBlank(message = "The phone is mandatory")
    @Pattern(regexp = "^\\(\\d{2}\\) \\d{5}-\\d{4}$", message = "Telephone must be in the format (XX) XXXXX-XXXX")
    private String phone;

    @NotBlank(message = "The date of birth is mandatory")
    @Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$", message = "Date of birth must be in the format dd/MM/yyyy")
    private String dateBirth;
}
