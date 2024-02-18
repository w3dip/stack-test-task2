package com.w3dip.app.service.impl;

import com.google.common.util.concurrent.*;
import com.w3dip.app.converter.ResponseConverter;
import com.w3dip.app.dto.response.ApplicationStatusResponse;
import com.w3dip.app.dto.response.Failure;
import com.w3dip.app.service.Client;
import com.w3dip.app.service.Handler;
import com.w3dip.integration.dto.response.Response;
import com.w3dip.integration.dto.response.RetryAfter;
import com.w3dip.integration.dto.response.Success;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.val;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class HandlerImpl implements Handler {
    private final Client client;
    private final ExecutorService executorService;

    @Override
    @SneakyThrows
    public ApplicationStatusResponse performOperation(String id) {
        List<CompletableFuture<ApplicationStatusResponse>> tasks = new ArrayList<>();
        tasks.add(addTask(() -> client.getApplicationStatus1(id), 0));
        tasks.add(addTask(() -> client.getApplicationStatus2(id), 0));

        return anyOf(tasks)
                .completeOnTimeout(Failure.builder().build(), 15, TimeUnit.SECONDS)
                .get();
    }

    public static <T> CompletableFuture<T> anyOf(List<CompletableFuture<T>> cfs) {
        return CompletableFuture.anyOf(cfs.toArray(new CompletableFuture[0]))
                .thenApply(o -> (T) o);
    }

    private CompletableFuture<ApplicationStatusResponse> addTask(Supplier<Response> task, int retryCount) {
        val requestTime = Instant.now().toString();
        return CompletableFuture.supplyAsync(task, executorService)
                .handle((response, ex) -> {
                    if (ex != null) {
                        return Failure.builder().lastRequestTime(requestTime).build();
                    }
                    System.out.println("Received " + response.toString());
                    if (response instanceof RetryAfter) {
                        val responseRetry = (RetryAfter) response;
                        try {
                            TimeUnit.MILLISECONDS.sleep(responseRetry.getDelay().toMillis());
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            Failure.builder().lastRequestTime(requestTime).build();
                        }
                        try {
                            return addTask(task, retryCount + 1).get();
                        } catch (Exception e) {
                            return Failure.builder().lastRequestTime(requestTime).build();
                        }
                    }
                    if (response instanceof Success) {
                        val successResponse = (Success) response;
                        return com.w3dip.app.dto.response.Success.builder()
                                .id(successResponse.getApplicationId())
                                .status(successResponse.getApplicationStatus())
                                .build();
                    }
                    return (ApplicationStatusResponse) Failure.builder()
                            .lastRequestTime(requestTime)
                            .retriesCount(retryCount);
                });
    }
}
