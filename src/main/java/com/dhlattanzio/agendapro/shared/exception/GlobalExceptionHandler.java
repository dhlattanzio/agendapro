package com.dhlattanzio.agendapro.shared.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Comparator;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final String VALIDATION_ERROR = "VALIDATION_ERROR";

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleException(Exception e) {
        return ResponseEntity.internalServerError().body(
                new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.name(), e.getMessage())
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ResponseEntity.badRequest().body(
                new ErrorDto(VALIDATION_ERROR, "One or more fields are invalid",
                        e.getFieldErrors().stream()
                                .map(x -> new ErrorDto.Field(x.getField(), x.getDefaultMessage()))
                                .sorted(Comparator.comparing(ErrorDto.Field::name))
                                .toList()
                )
        );
    }

    public record ErrorDto(String code, String message, @JsonInclude(JsonInclude.Include.NON_NULL) List<Field> fields) {
        ErrorDto(String code, String message) {
            this(code, message, null);
        }

        public record Field(String name, String details) {}
    }
}
