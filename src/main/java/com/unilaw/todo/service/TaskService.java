package com.unilaw.todo.service;

import com.unilaw.todo.dto.request.TaskRequest;
import com.unilaw.todo.dto.response.*;
import com.unilaw.todo.model.*;
import com.unilaw.todo.repository.*;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

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

    /**
     * Формирование списка дел по id списка
     *
     * @return ответ-список дел
     */
    @Override
    public AllTasksResponse getTasks(UUID listId) throws NotFoundException {
        ListEntity list = listRepository.findById(listId)
                .orElseThrow(() -> new NotFoundException("Couldn't find list with id"));

        List<TaskEntity> tasks = taskRepository.findAllByListId(list);

        List<TaskResponse> taskResponse = tasks.stream().map(TaskService::createTaskResponse).collect(Collectors.toList());

        AllTasksResponse allTaskResponse = new AllTasksResponse();
        allTaskResponse.setTasks(taskResponse);

        return allTaskResponse;
    }

    @Override
    public TaskResponse createTask(TaskRequest taskRequest) throws NotFoundException {
        ListEntity list = listRepository.findById(taskRequest.getListId())
                .orElseThrow(() -> new NotFoundException("Couldn't find list with id"));

        TaskEntity task = new TaskEntity();

        LocalDateTime date = LocalDateTime.now();

        task.setId(UUID.randomUUID());
        task.setName(taskRequest.getName());
        task.setComment(taskRequest.getComment());
        task.setMark(false);
        task.setPriority(taskRequest.getPriority());
        task.setCreatedDate(date);
        task.setUpdatedDate(date);
        task.setListId(list);

        taskRepository.saveAndFlush(task);

        return createTaskResponse(task);
    }

    @Override
    public TaskResponse changeTask(TaskRequest taskRequest, UUID taskId) throws NotFoundException {
        TaskEntity task = taskRepository.findById(taskId)
                .orElseThrow(() -> new NotFoundException("Couldn't find task with id"));

        task.setName(taskRequest.getName());
        task.setComment(taskRequest.getComment());
        task.setPriority(taskRequest.getPriority());
        task.setUpdatedDate(LocalDateTime.now());
        taskRepository.saveAndFlush(task);

        return createTaskResponse(task);
    }

    @Override
    public void deleteTask(UUID taskId) throws NotFoundException {
        if (!taskRepository.existsById(taskId)) {
            throw new NotFoundException("Couldn't find task with id");
        }
        taskRepository.deleteById(taskId);
    }

    @Override
    public void markDone(UUID taskId) throws NotFoundException {
        TaskEntity task = taskRepository.findById(taskId)
                .orElseThrow(() -> new NotFoundException("Couldn't find task with id"));
        task.setMark(!(task.isMark()));
        taskRepository.flush();
    }

    /**
     * Формирование ответа (дело)
     *
     * @param task - объект сущности (дело)
     * @return ответ на запрос
     */
    private static TaskResponse createTaskResponse(TaskEntity task) {
        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setId(task.getId());
        taskResponse.setName(task.getName());
        taskResponse.setComment(task.getComment());
        taskResponse.setMark(task.isMark());
        taskResponse.setPriority(task.getPriority());
        taskResponse.setCreatedDate(task.getCreatedDate());
        taskResponse.setUpdatedDate(task.getUpdatedDate());
        taskResponse.setListId(task.getListId().getId());

        return taskResponse;
    }
}
