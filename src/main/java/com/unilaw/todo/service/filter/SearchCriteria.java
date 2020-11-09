package com.unilaw.todo.service.filter;

import lombok.Data;

/**
 * Конструкция критерия фильтрации
 */
@Data
public class SearchCriteria {

    /**
     * Поле фильтрации
     */
    private String key;

    /**
     * Оператор
     */
    private String operation;

    /**
     * Значение фильтрации
     */
    private Object value;
}