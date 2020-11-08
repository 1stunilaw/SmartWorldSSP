package com.unilaw.todo.service.filter;

import lombok.Data;

@Data
public class SearchCriteria {

    private String key;

    private String operation;

    private Object value;
}