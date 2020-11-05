package com.unilaw.todo.controller;

import com.unilaw.todo.dto.request.*;
import com.unilaw.todo.dto.response.*;
import com.unilaw.todo.service.*;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api")
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
     * Метод создания дела
     *
     * @param task - запрос на создание дела
     * @return ответ на запрос (созданное дело)
     */
    @PostMapping("/task")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponse createTask(@RequestBody TaskRequest task)  throws NotFoundException {
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
    public TaskResponse changeTask(@RequestBody TaskRequest task, @PathVariable("id") UUID taskId) throws NotFoundException {
        return taskService.changeTask(task, taskId);
    }

    /**
     * Метод удаления дела
     *
     * @param taskId - идентификатор удаляемого дела
     */
    @DeleteMapping("/task/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable("id") UUID taskId) throws NotFoundException {
        taskService.deleteTask(taskId);
    }

    /**
     * Отмечает дело как выполненное
     *
     * @param taskId - идентификатор дела
     */
    @PostMapping("/markDone/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void markDone(@PathVariable("id") UUID taskId) throws NotFoundException {
        taskService.markDone(taskId);
    }
}
