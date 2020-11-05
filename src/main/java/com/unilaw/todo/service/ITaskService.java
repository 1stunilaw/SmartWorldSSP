package com.unilaw.todo.service;

import com.unilaw.todo.dto.request.TaskRequest;
import com.unilaw.todo.dto.response.TaskResponse;
import javassist.NotFoundException;

import java.util.UUID;

/**
 * Сервис для дел
 */
public interface ITaskService {

    /**
     * Создать новое дело
     *
     * @param createTaskRequest - данные для создания дела
     * @return TaskResponse
     */
    TaskResponse createTask(TaskRequest createTaskRequest) throws NotFoundException;

    /**
     * Изменить дело по id
     *
     * @param changeTaskRequest - данные для изменения дела
     * @param taskId            - идентификатор дела
     * @return TaskResponse
     */
    TaskResponse changeTask(TaskRequest changeTaskRequest, UUID taskId) throws NotFoundException; //TODO: добавить mark

    /**
     * Удалить дело по id
     *
     * @param taskId - идентификатор удаляемого дела
     */
    void deleteTask(UUID taskId) throws NotFoundException;

    /**
     * Отметить дело как выполненное
     *
     * @param taskId - идентификатор дела
     */
    void markDone(UUID taskId) throws NotFoundException;
}

