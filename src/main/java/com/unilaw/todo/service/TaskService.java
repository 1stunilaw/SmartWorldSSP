package com.unilaw.todo.service;

import com.unilaw.todo.dto.request.TaskRequest;
import com.unilaw.todo.dto.response.TaskResponse;
import com.unilaw.todo.repository.*;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return null;
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
}
