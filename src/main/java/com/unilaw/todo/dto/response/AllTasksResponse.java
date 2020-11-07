package com.unilaw.todo.dto.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Ответ на запрос о получении списка списков
 */
@Data
public class AllTasksResponse implements Serializable {

    private List<TaskResponse> tasks;
}
