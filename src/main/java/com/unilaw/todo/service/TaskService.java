package com.unilaw.todo.service;

import com.unilaw.todo.dto.request.TaskRequest;
import com.unilaw.todo.dto.response.*;
import com.unilaw.todo.model.*;
import com.unilaw.todo.repository.*;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Реализация интерфейса
 */
@Service
public class TaskService implements ITaskService {

    private final TaskRepository taskRepository;

    private final ListRepository listRepository;

    /**
     * Конструктор
     *
     * @param taskRepository
     * @param listRepository
     */
    @Autowired
    public TaskService(TaskRepository taskRepository, ListRepository listRepository) {
        this.listRepository = listRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public TaskResponse createTask(TaskRequest createTaskRequest) throws NotFoundException {
        ListEntity listEntity = listRepository.findById(createTaskRequest.getListId())
                .orElseThrow(() -> new NotFoundException("Couldn't find list with id"));

        TaskEntity task = new TaskEntity();

        LocalDateTime date = LocalDateTime.now();

        task.setId(UUID.randomUUID());
        task.setName(createTaskRequest.getName());
        task.setComment(createTaskRequest.getComment());
        task.setMark(false);
        task.setPriority(createTaskRequest.getPriority());
        task.setCreatedDate(date);
        task.setUpdatedDate(date);
        task.setListId(listEntity);

        taskRepository.saveAndFlush(task);

        return createTaskResponse(task);
    }

    @Override
    public TaskResponse changeTask(TaskRequest changeTaskRequest, UUID taskId) throws NotFoundException {
        return null;
    }

    @Override
    public void deleteTask(UUID taskId) throws NotFoundException {

    }

    @Override
    public void markDone(UUID taskId) throws NotFoundException {

    }

    /**
     * Формирование ответа (дело)
     *
     * @param taskEntity - объект сущности (дело)
     * @return ответ на запрос
     */
    private static TaskResponse createTaskResponse(TaskEntity taskEntity) {
        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setId(taskEntity.getId());
        taskResponse.setName(taskEntity.getName());
        taskResponse.setCreatedDate(taskEntity.getCreatedDate());
        taskResponse.setUpdatedDate(taskEntity.getUpdatedDate());

        return taskResponse;
    }
}
