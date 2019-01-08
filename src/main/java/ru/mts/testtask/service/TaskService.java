package ru.mts.testtask.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.mts.testtask.config.AsyncConfig;
import ru.mts.testtask.config.props.ExecutorsProps;
import ru.mts.testtask.exception.TaskNotFoundException;
import ru.mts.testtask.model.Task;
import ru.mts.testtask.repository.TaskRepository;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskService {

  private final TaskRepository taskRepository;
  private final ScheduledExecutorService scheduledExecutorService;
  private final ExecutorsProps executorsProps;

  public Task save(@NonNull Task newTask) {
    return taskRepository.save(newTask);
  }

  @Async(AsyncConfig.PROCESSING_TASK_EXECUTOR)
  public void processTask(@NonNull Task task) {
    log.debug("Process task is started {}", task);
    task.setStatus(Task.Status.RUNNING);
    task.setTimestamp(LocalDateTime.now());
    Task runningTask = taskRepository.save(task);
    log.debug("Task is running: {}", runningTask);
    scheduledExecutorService.schedule(
        () -> {
          runningTask.setStatus(Task.Status.FINISHED);
          runningTask.setTimestamp(LocalDateTime.now());
          Task finishedTask = taskRepository.save(runningTask);
          log.debug("Task is finished: {}", finishedTask);

        },
        executorsProps.getDelayBeforeStartInMinutes(),
        TimeUnit.MINUTES);
  }

  public Task findById(@NonNull UUID id) {
    return taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
  }
}
