package com.w3dip.integration.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Failure implements Response {
    Throwable ex;
}
