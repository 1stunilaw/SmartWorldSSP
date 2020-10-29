package com.unilaw.todo.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Сущность задания
 */
@Data
@Entity
@Table(name = "task")
public class TaskEntity {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "comment")
    private String comment;

    @Column(name = "mark")
    private boolean mark;

    @Column(name = "priority")
    @Enumerated(EnumType.ORDINAL)
    private Priority priority;

    @Column(name = "create_date")
    private LocalDateTime createdData;

    @Column(name = "update_date")
    private LocalDateTime updatedData;

    @ManyToOne
    @JoinColumn(name = "list")
    private ListEntity listId;
}
