package com.w3dip.app.service.impl;

import com.w3dip.app.service.Client;
import com.w3dip.integration.dto.response.Response;
import com.w3dip.integration.service.RemoteMockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientImpl implements Client {
    private final RemoteMockService remoteService;

    @Override
    public Response getApplicationStatus1(String id) {
        return remoteService.execute(id);
    }

    @Override
    public Response getApplicationStatus2(String id) {
        return remoteService.execute(id);
    }
}
