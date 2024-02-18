package com.w3dip.app.rest;

import com.w3dip.app.dto.response.ApplicationStatusResponse;
import com.w3dip.app.service.Handler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class Task1Controller {
    private final Handler handler;

    @GetMapping("/")
    public ApplicationStatusResponse index() {
        return handler.performOperation(UUID.randomUUID().toString());
    }
}
