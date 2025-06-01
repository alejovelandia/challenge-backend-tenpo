package com.tenpo.challenge.usecase;

import com.tenpo.challenge.service.GetPercentageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MathOperationUseCaseImpl implements MathOperationUseCase {

    private final GetPercentageService getPercentageService;

    @Override
    public double addition(final Long firstNumber, final Long secondNumber) {
        final double sum = Math.addExact(firstNumber, secondNumber);
        final double percentage = this.getPercentageService.getPercentage();
        return sum + (sum * percentage);
    }
}
