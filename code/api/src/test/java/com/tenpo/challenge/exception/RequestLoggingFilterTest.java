package com.tenpo.challenge.exception;

import com.tenpo.api.model.ApiResponseError;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RequestLoggingFilterTest {

    final CustomExceptionHandler handler = new CustomExceptionHandler();

    @Test
    void handlePercentageNotFound_returnsNotFound() {
        PercentageNotFoundException ex = new PercentageNotFoundException("No encontrado");
        ResponseEntity<ApiResponseError> response = handler.handlePercentageNotFound(ex);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(String.valueOf(HttpStatus.NOT_FOUND.value()), response.getBody().getCode());
        assertEquals("No encontrado", response.getBody().getMessage());
    }

    @Test
    void handleGenericException_returnsInternalServerError() {
        Exception ex = new Exception("Error genérico");
        ResponseEntity<ApiResponseError> response = handler.handleGenericException(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), response.getBody().getCode());
        assertEquals("Error genérico", response.getBody().getMessage());
    }
}
