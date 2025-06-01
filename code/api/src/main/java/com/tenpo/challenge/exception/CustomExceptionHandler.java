package com.tenpo.challenge.exception;

import com.tenpo.api.model.ApiResponseError;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Hidden
@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ExceptionHandler(PercentageNotFoundException.class)
    public ResponseEntity<ApiResponseError> handlePercentageNotFound(PercentageNotFoundException ex) {
        final ApiResponseError error = new ApiResponseError();
        error.setCode(String.valueOf(HttpStatus.NOT_FOUND.value()));
        error.setMessage(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseError> handleGenericException(Exception ex) {
        log.error("An unexpected error occurred", ex);
        final ApiResponseError error = new ApiResponseError();
        error.setCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        error.setMessage(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
