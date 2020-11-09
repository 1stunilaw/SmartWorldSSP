package com.unilaw.todo.dto.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * Запрос на создание или изменение списка дел
 */
@Data
@ApiModel(description = "Запрос (данные) для создания и изменения списка")
public class ListRequest implements Serializable {

    @NotBlank(message = "Name may not be empty")
    @Length(max = 100, message = "The field must be less than {max} characters")
    private String name;
}

