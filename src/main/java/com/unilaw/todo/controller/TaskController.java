package com.unilaw.todo.controller;

import com.unilaw.todo.dto.request.*;
import com.unilaw.todo.dto.response.*;
import com.unilaw.todo.service.*;
import io.swagger.annotations.*;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api")
@Api(description = "Набор эндпоинтов для получения создания, изменения, удаления дел в списках")
public class TaskController {

    private final TaskService taskService;

    /**
     * Конструктор
     *
     * @param taskService
     */
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * Метод возврата списка дел
     *
     * @return список дел
     */
    @GetMapping("/list/{id}")
    @ApiOperation("Метод для получения дел по id списка")
    public AllTasksResponse getTasks(
            @ApiParam(value = "id списка дел") @PathVariable("id") UUID listId
    ) throws NotFoundException {
        return taskService.getTasks(listId);
    }

    /**
     * Метод создания дела
     *
     * @param task - запрос на создание дела
     * @return ответ на запрос (созданное дело)
     */
    @PostMapping("/task")
    @ApiOperation("Метод для создания дела")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponse createTask(
            @ApiParam(value = "Тело запроса") @RequestBody TaskRequest task
    ) throws NotFoundException {
        return taskService.createTask(task);
    }

    /**
     * Метод изменения дела
     *
     * @param task   - запрос на создание (имя, описание, приоритет)
     * @param taskId - идентификатор изменяемого дела
     * @return ответ на запрос (измененное дело
     * @throws NotFoundException
     */
    @PutMapping("/task/{id}")
    @ApiOperation("Метод для изменения дела")
    public TaskResponse changeTask(
            @ApiParam(value = "Тело запроса") @RequestBody TaskRequest task,
            @ApiParam(value = "id дела") @PathVariable("id") UUID taskId
    ) throws NotFoundException {
        return taskService.changeTask(task, taskId);
    }

    /**
     * Метод удаления дела
     *
     * @param taskId - идентификатор удаляемого дела
     */
    @DeleteMapping("/task/{id}")
    @ApiOperation("Метод для удаления дела")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(
            @ApiParam(value = "id дела") @PathVariable("id") UUID taskId
    ) throws NotFoundException {
        taskService.deleteTask(taskId);
    }

    /**
     * Отмечает дело как выполненное
     *
     * @param taskId - идентификатор дела
     */
    @PostMapping("/markDone/{id}")
    @ApiOperation("Метод для изменения метки дела на противоположную")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void markDone(
            @ApiParam(value = "id дела") @PathVariable("id") UUID taskId
    ) throws NotFoundException {
        taskService.markDone(taskId);
    }
}
