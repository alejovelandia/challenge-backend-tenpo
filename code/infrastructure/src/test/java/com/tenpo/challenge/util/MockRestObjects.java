package com.tenpo.challenge.util;

import com.tenpo.challenge.service.dto.PercentageApiResponse;

public class MockRestObjects {

    public static final String EXTERNAL_API_HOST = "http://localhost:8080";
    public static final String EXTERNAL_API_PATH = "/percentage";

    public static PercentageApiResponse mockPercentageApiResponse() {
        PercentageApiResponse response = new PercentageApiResponse();
        response.setStatus("SUCCESS");
        response.setPercentage(0.3);
        return response;
    }
}
