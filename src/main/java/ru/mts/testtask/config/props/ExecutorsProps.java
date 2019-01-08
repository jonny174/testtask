package ru.mts.testtask.config.props;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("executors")
@Getter
@Setter
public class ExecutorsProps {

  private int threadPoolSize = 1;
  private int threadPoolMaxSize = 1;
  private int scheduledPoolSize = 1;
  private int delayBeforeStartInMinutes = 1;
}
