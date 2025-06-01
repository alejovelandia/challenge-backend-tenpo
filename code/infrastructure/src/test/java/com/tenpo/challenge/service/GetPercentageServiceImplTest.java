package com.tenpo.challenge.service;

import com.tenpo.challenge.properties.ExternalApiProperties;
import com.tenpo.challenge.service.dto.PercentageApiResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import static com.tenpo.challenge.util.MockRestObjects.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetPercentageServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ExternalApiProperties properties;

    @InjectMocks
    private GetPercentageServiceImpl getPercentageService;

    @Test
    void save_ShouldReturnMappedSavedEntity() {
        final PercentageApiResponse response = mockPercentageApiResponse();

        when(this.properties.getHost()).thenReturn(EXTERNAL_API_HOST);
        when(this.properties.getPath()).thenReturn(EXTERNAL_API_PATH);
        when(this.restTemplate.getForObject(EXTERNAL_API_HOST.concat(EXTERNAL_API_PATH), PercentageApiResponse.class))
                .thenReturn(response);

        final double result = this.getPercentageService.getPercentage();

        assertEquals(response.getPercentage(), result);
        verify(this.properties).getHost();
        verify(this.properties).getPath();
        verify(this.restTemplate).getForObject(anyString(), any());
    }
}
