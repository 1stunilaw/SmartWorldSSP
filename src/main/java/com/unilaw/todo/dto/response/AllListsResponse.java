package com.unilaw.todo.dto.response;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Ответ на запрос о получении списка списков
 */
@Data
@ApiModel(description = "Ответ, содержащий список из списков дел")
public class AllListsResponse implements Serializable {

    private List<ListResponse> lists;
}
