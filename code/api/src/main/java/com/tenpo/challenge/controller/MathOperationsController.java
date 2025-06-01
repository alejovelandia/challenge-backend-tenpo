package com.tenpo.challenge.controller;

import com.tenpo.api.generated.MathOperationApi;
import com.tenpo.api.model.AdditionResponse;
import com.tenpo.challenge.usecase.MathOperationUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MathOperationsController implements MathOperationApi {

    private final MathOperationUseCase mathOperationUseCase;

    @Override
    public ResponseEntity<AdditionResponse> mathAddition(final Long num1, final Long num2) {
        final AdditionResponse response = new AdditionResponse();
        response.setResult(this.mathOperationUseCase.addition(num1, num2));
        return ResponseEntity.ok(response);
    }

}