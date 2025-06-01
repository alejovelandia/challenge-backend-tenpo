package com.tenpo.challenge.usecase;

import com.tenpo.challenge.service.GetPercentageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MathOperationUseCaseImplTest {

    @Mock
    private GetPercentageService getPercentageService;

    @InjectMocks
    private MathOperationUseCaseImpl mathOperationUseCase;

    @Test
    void addition_ShouldReturnParamsAddition() {
        final double percentage = 0.1;
        final Long num1 = 3L;
        final Long num2 = 4L;
        final double expected = num1 + num2 + ((num1 + num2) * percentage);
        when(this.getPercentageService.getPercentage()).thenReturn(percentage);

        final double result = this.mathOperationUseCase.addition(num1, num2);


        assertEquals(expected, result);
    }
}
