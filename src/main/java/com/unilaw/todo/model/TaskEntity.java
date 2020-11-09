package com.unilaw.todo.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Сущность задания
 */
@Data
@Entity
@Table(name = "tasks")
public class TaskEntity {

    /**
     * ID дела (задачи)
     * Является первичным ключом, заполняется автоматически
     */
    @Id
    @Column(name = "id")
    private UUID id;

    /**
     * Название дела (задачи)
     */
    @Column(name = "name")
    private String name;

    /**
     * Описание дела
     */
    @Column(name = "comment")
    private String comment;

    /**
     * Метка: выполено / не выполнено
     */
    @Column(name = "mark")
    private boolean mark;

    /**
     * Приоритет дела
     */
    @Column(name = "priority")
    @Enumerated(EnumType.ORDINAL)
    private Priority priority;

    /**
     * Время создания записи
     * Не может быть пустым, не пеезаписывается
     */
    @Column(name = "create_date")
    private LocalDateTime createdDate;

    /**
     * Время последнего изменения записи
     * Не может быть пустым
     */
    @Column(name = "update_date")
    private LocalDateTime updatedDate;

    /**
     * ID списка
     * Является внешним ключом
     * Связь "многие к одному" (многие задачи могут относиться к одному листу)
     */
    @ManyToOne
    @JoinColumn(name = "list")
    private ListEntity listId;
}
