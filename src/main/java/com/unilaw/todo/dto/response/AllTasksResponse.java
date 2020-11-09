package com.unilaw.todo.dto.response;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.*;

/**
 * Ответ на запрос о получении списка списков
 */
@Data
@ApiModel(description = "Ответ, содержащий id и название списка, а также список дел выбранного списка")
public class AllTasksResponse implements Serializable {

    private UUID id;

    private String name;

    private List<TaskResponse> tasks;
}
