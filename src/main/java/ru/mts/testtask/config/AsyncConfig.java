package ru.mts.testtask.config;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import ru.mts.testtask.config.props.ExecutorsProps;

import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.Executors.newScheduledThreadPool;

@Configuration
@EnableAsync
@RequiredArgsConstructor
public class AsyncConfig {

  public final static String PROCESSING_TASK_EXECUTOR = "processingThreadPoolTaskExecutor";

  private final ExecutorsProps executorsProps;

  @Bean(AsyncConfig.PROCESSING_TASK_EXECUTOR)
  public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
    val executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(executorsProps.getThreadPoolSize());
    executor.setMaxPoolSize(executorsProps.getThreadPoolMaxSize());
    return executor;
  }

  @Bean
  public ScheduledExecutorService schedulingTaskExecutor() {
    return newScheduledThreadPool(executorsProps.getScheduledPoolSize());
  }
}
