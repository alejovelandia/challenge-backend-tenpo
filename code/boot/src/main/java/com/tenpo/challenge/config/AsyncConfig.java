package com.tenpo.challenge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean(name = "customExecutor")
    public Executor customExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);  // Minimum number of active threads
        executor.setMaxPoolSize(10);  // Maximum number of threads the pool can grow to
        executor.setQueueCapacity(50);  // Queue size for tasks waiting to be executed
        executor.setKeepAliveSeconds(60);  // Time idle threads remain alive before termination
        executor.setThreadNamePrefix("AsyncExecutor-");  // Naming pattern for worker threads
        executor.initialize();
        return executor;
    }
}