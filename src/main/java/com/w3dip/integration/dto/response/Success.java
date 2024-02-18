package com.w3dip.integration.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Success implements Response {
    String applicationStatus;
    String applicationId;
}
