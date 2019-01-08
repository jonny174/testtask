package ru.mts.testtask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mts.testtask.model.Task;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {
}
