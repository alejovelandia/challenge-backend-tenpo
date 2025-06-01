package com.tenpo.challenge.service;

import com.tenpo.challenge.exception.PercentageNotFoundException;
import com.tenpo.challenge.properties.ExternalApiProperties;
import com.tenpo.challenge.service.dto.PercentageApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetPercentageServiceImpl implements GetPercentageService {

    private final RestTemplate restTemplate;

    private final ExternalApiProperties apiProperties;

    @Override
    public double getPercentage() {
        final String url = apiProperties.getHost().concat(apiProperties.getPath());
        try {
            final PercentageApiResponse response = restTemplate.getForObject(url, PercentageApiResponse.class);

            final double percentage = Objects.requireNonNull(response).getPercentage();
            this.putPercentageToCache(percentage);
            return percentage;
        } catch (RestClientException e) {
            log.error("ERROR :: Calling percentage api: {}", e.getMessage(), e);
            log.info("INFO :: Get percentage from cache");
            return getPercentageFromCache();
        }
    }

    @Cacheable("percentageApiCache")
    private double getPercentageFromCache() {
        log.error("ERROR :: not value saved in cache");
        throw new PercentageNotFoundException("ERROR ::external api error and not value saved in cache");
    }

    @CachePut("percentageApiCache")
    private void putPercentageToCache(double percentage) {
        log.info("INFO :: Put percentage to cache: {}", percentage);
    }
}
