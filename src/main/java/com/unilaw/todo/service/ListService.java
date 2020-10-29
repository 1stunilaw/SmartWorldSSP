package com.unilaw.todo.service;

import com.unilaw.todo.dto.request.ListRequest;
import com.unilaw.todo.dto.response.*;
import com.unilaw.todo.model.ListEntity;
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
public class ListService implements IListService {

    private final TaskRepository taskRepository;

    private final ListRepository listRepository;

    /**
     * Конструктор
     *
     * @param taskRepository
     * @param listRepository
     */
    @Autowired
    public ListService(TaskRepository taskRepository, ListRepository listRepository) {
        this.taskRepository = taskRepository;
        this.listRepository = listRepository;
    }

    @Override
    public AllListsResponse getLists() {
        return null;
    }

    @Override
    public ListResponse createList(ListRequest listRequest) {
        ListEntity list = new ListEntity();

        LocalDateTime date = LocalDateTime.now();

        list.setId(UUID.randomUUID());
        list.setName(listRequest.getName());
        list.setCreatedDate(date);
        list.setUpdatedDate(date);
        listRepository.saveAndFlush(list);

        return createListResponse(list);
    }

    @Override
    public ListResponse changeList(ListRequest listRequest, UUID listId) throws NotFoundException {
        return null;
    }

    @Override
    public void deleteList(UUID listId) throws NotFoundException {

    }

    /**
     * Формирование ответа (список)
     * @param entity - объект сущности (созданный список)
     * @return ответ на запрос
     */
    private static ListResponse createListResponse(ListEntity entity){
        ListResponse listResponse = new ListResponse();
        listResponse.setId(entity.getId());
        listResponse.setName(entity.getName());
        listResponse.setCreatedDate(entity.getCreatedDate());
        listResponse.setUpdatedDate(entity.getUpdatedDate());

        return listResponse;
    }
}
