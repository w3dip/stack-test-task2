package com.w3dip.app.service.impl;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.w3dip.app.converter.ResponseConverter;
import com.w3dip.app.service.Client;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;
import java.util.concurrent.ScheduledExecutorService;

import static org.junit.jupiter.api.Assertions.*;

//@MockitoSettings
@SpringBootTest
class HandlerImplTest {

    @Autowired
    private HandlerImpl handler;

    /*@Spy
    private Client client;

    @Spy
    private ResponseConverter responseConverter;

    @Spy
    private ListeningExecutorService executorService;

    @Spy
    private ScheduledExecutorService scheduledExecutorService;*/

    @Test
    void performOperation() {
        val result = handler.performOperation(UUID.randomUUID().toString());
        System.out.println(result.toString());
    }
}