package com.syxbruno.CrudClientRegistration.dto.error;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FieldErrorDTO {

    private String field;
    private String message;
}
