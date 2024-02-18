package com.w3dip.app.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.Duration;
import java.time.Instant;

@Data
@Builder
public class Failure implements ApplicationStatusResponse {
    String lastRequestTime;
    int retriesCount;
}
