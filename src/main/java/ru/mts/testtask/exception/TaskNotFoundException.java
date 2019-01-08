package ru.mts.testtask.exception;

import java.util.UUID;

public class TaskNotFoundException extends RuntimeException {

  public TaskNotFoundException(UUID id) {
    super(String.format("Task with id %s not found", id));
  }
}
