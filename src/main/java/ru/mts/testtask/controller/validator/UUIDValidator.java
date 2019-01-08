package ru.mts.testtask.controller.validator;

import org.springframework.stereotype.Component;
import ru.mts.testtask.exception.InvalidIdentifierException;

import java.util.regex.Pattern;

@Component
public class UUIDValidator {

  private final Pattern UUID_PATTERN = Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");

  public void validate(String any) {
    if (!UUID_PATTERN.matcher(any).matches()) {
      throw new InvalidIdentifierException(any);
    }
  }
}
