package com.syxbruno.CrudClientRegistration.handler;

import com.syxbruno.CrudClientRegistration.dto.error.ExceptionDTO;
import com.syxbruno.CrudClientRegistration.exception.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionDTO> badRequestExceptionHandler(BadRequestException exception) {

        return new ResponseEntity<>(
                ExceptionDTO.builder()
                        .message(exception.getMessage())
                        .developerMessage("exception thrown in: " + exception.getClass().getName())
                        .timeSamp(LocalDateTime.now())
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handlerValidationException(MethodArgumentNotValidException exception) {

        Map<String, List<String>> errors = new HashMap<>();

        exception.getBindingResult().getAllErrors().forEach((error) -> {
            FieldError fieldError = (FieldError) error;

            String fieldName = fieldError.getField();
            String defaultMessage = fieldError.getDefaultMessage();

            errors.computeIfAbsent(fieldName, messages -> new ArrayList<>()).add(defaultMessage);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
