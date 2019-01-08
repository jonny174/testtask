package ru.mts.testtask.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "ru.mts.testtask.repository")
public class JdbcConfig {
}
