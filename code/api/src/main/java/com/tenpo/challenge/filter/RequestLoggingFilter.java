package com.tenpo.challenge.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tenpo.challenge.dto.TransactionHistory;
import com.tenpo.challenge.usecase.TransactionLoggingUseCase;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class RequestLoggingFilter extends OncePerRequestFilter {

    private final TransactionLoggingUseCase transactionLoggingUseCase;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(response);

        try {
            filterChain.doFilter(wrappedRequest, wrappedResponse);
        } finally {
            if (wrappedRequest.getRequestURI().contains("math-operation")) {
                final String responseBody = new String(wrappedResponse.getContentAsByteArray(), StandardCharsets.UTF_8);
                final String queryParamsJson = convertQueryParamsToJson(wrappedRequest);
                final String responseJson = tryParseJson(responseBody);

                final TransactionHistory transaction = TransactionHistory.builder()
                        .endpoint(wrappedRequest.getRequestURI())
                        .parameters(queryParamsJson)
                        .response(responseJson)
                        .creationDate(LocalDateTime.now())
                        .build();
                this.transactionLoggingUseCase.asyncSave(transaction);
            }
            wrappedResponse.copyBodyToResponse();
        }
    }

    private String convertQueryParamsToJson(HttpServletRequest request) {
        final ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String[]> paramMap = request.getParameterMap();
        Map<String, Object> flatMap = new HashMap<>();
        paramMap.forEach((key, values) -> {
            if (values.length == 1) {
                flatMap.put(key, values[0]);
            } else {
                flatMap.put(key, values);
            }
        });
        try {
            return objectMapper.writeValueAsString(flatMap);
        } catch (JsonProcessingException e) {
            log.error("ERROR :: converting request data to JSON", e);
            return "{}";
        }
    }

    private String tryParseJson(String body) {
        final ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(body);
            return objectMapper.writeValueAsString(jsonNode);
        } catch (JsonProcessingException e) {
            return body;
        }
    }
}
