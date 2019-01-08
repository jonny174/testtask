package ru.mts.testtask.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.mts.testtask.controller.dto.IdDto;
import ru.mts.testtask.controller.dto.TaskDto;
import ru.mts.testtask.controller.validator.UUIDValidator;
import ru.mts.testtask.model.Task;
import ru.mts.testtask.service.TaskService;

import java.util.UUID;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
@Slf4j
public class TaskController {

  private final UUIDValidator uuidValidator;
  private final TaskService taskService;
  private final ModelMapper modelMapper;

  @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseStatus(ACCEPTED)
  public IdDto create() {
    log.debug("Request to create new task");
    Task newTask = taskService.save(new Task());
    taskService.processTask(newTask);
    return new IdDto(newTask.getId());
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseStatus(OK)
  public TaskDto getById(@PathVariable String id) {
    log.debug("Request to find task by id: {}", id);
    uuidValidator.validate(id);
    UUID uuid = UUID.fromString(id);
    Task task = taskService.findById(uuid);
    return modelMapper.map(task, TaskDto.class);
  }

}
