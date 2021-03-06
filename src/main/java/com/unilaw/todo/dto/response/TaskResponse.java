package com.unilaw.todo.dto.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.unilaw.todo.model.Priority;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Ответ на запросы о делах (задачах)
 */
@Data
@ApiModel(description = "Ответ, содержащий данные о деле")
public class TaskResponse implements Serializable {

    private UUID id;

    private String name;

    private String comment;

    private boolean mark;

    private Priority priority;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createdDate;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime updatedDate;

    private UUID listId;
}