package com.w3dip.integration.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.Duration;

@Data
@Builder
public class RetryAfter implements Response {
    Duration delay;
}
