package com.w3dip.app.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Success implements ApplicationStatusResponse {
    String id;
    String status;
}
