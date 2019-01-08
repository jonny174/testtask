package ru.mts.testtask.config;

import lombok.val;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.mts.testtask.model.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Configuration
public class ModelMapperConfig {

  @Bean
  public ModelMapper modelMapper() {
    val modelMapper = new ModelMapper();
    modelMapper.addConverter(new LocalDateTimeToStringConverter());
    modelMapper.addConverter(new TaskStatusToStringConverter());
    return modelMapper;
  }

  private static class LocalDateTimeToStringConverter implements Converter<LocalDateTime, String> {
    @Override
    public String convert(MappingContext<LocalDateTime, String> context) {
      return Optional.ofNullable(context.getSource())
          .map(it -> {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            return it.format(formatter);
          }).orElse(null);
    }
  }

  private static class TaskStatusToStringConverter implements Converter<Task.Status, String> {
    @Override
    public String convert(MappingContext<Task.Status, String> context) {
      return Optional.ofNullable(context.getSource())
          .map(Task.Status::name)
          .map(String::toLowerCase)
          .orElse(null);
    }
  }
}
