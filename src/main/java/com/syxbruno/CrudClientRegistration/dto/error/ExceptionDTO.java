package com.syxbruno.CrudClientRegistration.dto.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
public class ExceptionDTO {

    private String message;
    private String developerMessage;
    private int statusCode;
    private LocalDateTime timeSamp;
}
