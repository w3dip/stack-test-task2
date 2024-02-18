package com.w3dip.app.config;


import com.google.common.util.concurrent.ListeningExecutorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static com.google.common.util.concurrent.MoreExecutors.listeningDecorator;

@Configuration
public class AppConfig {

    @Bean
    public ExecutorService executorService() {
        return Executors.newScheduledThreadPool(10);
    }
}
