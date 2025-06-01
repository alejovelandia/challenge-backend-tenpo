package com.tenpo.challenge.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tenpo.challenge.dto.TransactionHistory;
import com.tenpo.challenge.usecase.TransactionLoggingUseCase;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RequestLoggingFilterTest {

    @Mock
    private TransactionLoggingUseCase transactionLoggingUseCase;

    @InjectMocks
    private RequestLoggingFilter filter;

    @Test
    void shouldLogRequestAndResponse() throws ServletException, IOException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final MockHttpServletRequest request = new MockHttpServletRequest("GET", "/math-operation/test");
        request.setParameter("userId", "123");
        request.setContent("{}".getBytes(StandardCharsets.UTF_8));

        final MockHttpServletResponse response = new MockHttpServletResponse();
        final FilterChain filterChain = (req, res) -> {
            res.getWriter().write("{\"status\":\"ok\"}");
        };

        this.filter.doFilter(request, response, filterChain);

        final ArgumentCaptor<TransactionHistory> captor = ArgumentCaptor.forClass(TransactionHistory.class);
        verify(this.transactionLoggingUseCase, times(1)).asyncSave(captor.capture());
        final TransactionHistory saved = captor.getValue();

        assertEquals("/math-operation/test", saved.getEndpoint());
        final Map<String, Object> paramMap = objectMapper.readValue(saved.getParameters(), Map.class);
        assertEquals("123", paramMap.get("userId"));
        final Map<String, Object> responseMap = objectMapper.readValue(saved.getResponse(), Map.class);
        assertEquals("ok", responseMap.get("status"));
        assertNotNull(saved.getCreationDate());
    }

    @Test
    void should_notLogRequest_causeUnexpectedURI() throws ServletException, IOException {
        final MockHttpServletRequest request = new MockHttpServletRequest("GET", "/unknown/test");

        final MockHttpServletResponse response = new MockHttpServletResponse();
        final FilterChain filterChain = (req, res) -> {
            res.getWriter().write("{\"status\":\"ok\"}");
        };

        this.filter.doFilter(request, response, filterChain);

        verify(this.transactionLoggingUseCase, never()).asyncSave(any());

    }
}
