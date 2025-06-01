package com.tenpo.challenge.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "properties.external-api.percentage-api")
@Data
public class ExternalApiProperties {

    String host;

    String path;
}
