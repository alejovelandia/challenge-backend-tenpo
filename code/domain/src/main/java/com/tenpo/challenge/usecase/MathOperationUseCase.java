package com.tenpo.challenge.usecase;

public interface MathOperationUseCase {

    /**
     * Performs a mathematical operation addition on two integers.
     *
     * @param firstNumber  the first Long
     * @param secondNumber the second Long
     * @return the result of the addition as a double
     */
    double addition(final Long firstNumber, final Long secondNumber);
}
