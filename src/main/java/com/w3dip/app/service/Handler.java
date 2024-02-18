package com.w3dip.app.service;

import com.w3dip.app.dto.response.ApplicationStatusResponse;

public interface Handler {
    ApplicationStatusResponse performOperation(String id);
}
