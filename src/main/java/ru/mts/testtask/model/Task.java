package ru.mts.testtask.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "task")
@Getter
@Setter
@ToString(callSuper = true)
public class Task extends Identifiable {

  @Column(name = "status", length = 8)
  @Enumerated(EnumType.STRING)
  private Status status = Status.CREATED;

  @Column(name = "timestamp")
  private LocalDateTime timestamp = LocalDateTime.now();

  public enum Status {
    CREATED,
    RUNNING,
    FINISHED
  }
}
