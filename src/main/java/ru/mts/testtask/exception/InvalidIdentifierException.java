package ru.mts.testtask.exception;

public class InvalidIdentifierException extends RuntimeException {

  public InvalidIdentifierException(String id) {
    super(String.format("Id %s is not uuid", id));
  }
}
