package ru.mts.testtask.controller.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
@Getter
public class IdDto {

  private final UUID taskId;
}
