package com.tenpo.challenge.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class NextPageInfo {
    int offset;

    int limit;

    int total;
}
