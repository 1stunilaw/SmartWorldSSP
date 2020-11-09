package com.unilaw.todo.dto.request;

import com.unilaw.todo.model.Priority;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * Запрос на создание или изменение дела (задачи)
 */
@Data
@ApiModel(description = "Запрос (данные) для создания и изменения дела")
public class TaskRequest implements Serializable {

    @NotBlank(message = "Name may not be empty")
    @Length(max = 100, message = "The field must be less than {max} characters")
    private String name;

    @Length(max = 500, message = "The field must be less than {max} characters")
    private String comment;

    @NotNull
    private Priority priority;

    @NotNull
    private UUID listId;
}
