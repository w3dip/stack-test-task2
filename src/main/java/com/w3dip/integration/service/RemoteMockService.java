package com.w3dip.integration.service;

import com.w3dip.integration.dto.response.Failure;
import com.w3dip.integration.dto.response.Response;
import com.w3dip.integration.dto.response.RetryAfter;
import com.w3dip.integration.dto.response.Success;
import lombok.SneakyThrows;
import lombok.val;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Random;
import java.util.UUID;

@Service
public class RemoteMockService {
    private static final Random rand = new Random();

    @SneakyThrows
    public Response execute(String id) {
        Thread.sleep(random(5000));
        return getResponse( id, random(3));
    }

    public Response getResponse(String id, int randomStatus) {
        switch (randomStatus) {
            case 0:
                return Success.builder()
                    .applicationId(id)
                    .applicationStatus("SUCCESS")
                    .build();
            case 1:
                return RetryAfter.builder()
                        .delay(Duration.ofMillis(random(3000)))
                        .build();
            default:
                return Failure.builder()
                        .ex(new RuntimeException("some error"))
                        .build();
        }
    }

    public static int random(int bound) {
        return rand.nextInt(bound);
    }
}
