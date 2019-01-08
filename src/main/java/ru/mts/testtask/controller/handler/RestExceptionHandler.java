package ru.mts.testtask.controller.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.mts.testtask.exception.InvalidIdentifierException;
import ru.mts.testtask.exception.TaskNotFoundException;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler {

  @ExceptionHandler(InvalidIdentifierException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public void badRequest(Exception e) {
    log.error(e.getMessage(), e);
  }

  @ExceptionHandler(TaskNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public void nodFound(Exception e) {
    log.error(e.getMessage(), e);
  }
}
