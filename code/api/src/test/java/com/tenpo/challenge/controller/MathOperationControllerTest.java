package com.tenpo.challenge.controller;

import com.tenpo.api.model.AdditionResponse;
import com.tenpo.challenge.usecase.MathOperationUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MathOperationControllerTest {

    @Mock
    private MathOperationUseCase mathOperationUseCase;

    @InjectMocks
    private MathOperationsController methOperationsController;

    @Test
    void addition_ShouldReturnServiceResponse() {
        final double expected = 7.0;
        when(this.mathOperationUseCase.addition(3L,4L)).thenReturn(expected);

        final ResponseEntity<AdditionResponse> result = this.methOperationsController.mathAddition(3L,4L);

        assertNotNull(result.getBody());
        assertEquals(expected, result.getBody().getResult());
        verify(this.mathOperationUseCase).addition(anyLong(), anyLong());
    }
}
