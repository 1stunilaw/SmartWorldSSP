package com.unilaw.todo.dto.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Ответ на запрос о создании или изменении списка дел
 */
@Data
public class ListResponse implements Serializable {

    private UUID id;

    private String name;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createdDate;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime updatedDate;
}
