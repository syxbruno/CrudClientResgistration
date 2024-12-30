package com.syxbruno.investmentManagement.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Entity
@Data
@Builder
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "The name is mandatory")
    @Size(min = 3, max = 100, message = "The name must be between 3 and 100 characters")
    private String name;

    @NotBlank(message = "The CPF is mandatory")
    @CPF(message = "CPF invalid")
    private String cpf;

    @NotBlank(message = "The password is mandatory")
    private String password;

    @NotBlank(message = "The email is mandatory")
    @Email(message = "Email invalid")
    private String email;

    @NotBlank(message = "The phone is mandatory")
    @Pattern(regexp = "^\\(\\d{2}\\) \\d{5}-\\d{4}$", message = "Telephone must be in the format (XX) XXXXX-XXXX")
    private String phone;

    @NotBlank(message = "The date of birth is mandatory")
    @Past(message = "Date of birth invalid")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateBirth;
}
