package com.unilaw.todo.service;

import com.unilaw.todo.dto.request.ListRequest;
import com.unilaw.todo.dto.response.*;
import javassist.NotFoundException;

import java.util.UUID;

/**
 * Сервис для списков
 */
public interface IListService {

    /**
     * Получить список списков
     *
     * @return список списков
     */
    AllListsResponse getLists(); //TODO: добавить сортировку, фильтрацию, пагинацию

    /**
     * Создать новый список
     *
     * @param listRequest - данные для создания списка
     * @return ListResponse
     */
    ListResponse createList(ListRequest listRequest);

    /**
     * Изменить список по id
     *
     * @param listRequest - данные для изменения списка
     * @param listId      -  идентификатор изменяемого списка
     * @return ListResponse
     */
    ListResponse changeList(ListRequest listRequest, UUID listId) throws NotFoundException;

    /**
     * Удалить список по id
     *
     * @param listId - идентификатор удаляемого списка
     */
    void deleteList(UUID listId) throws NotFoundException;
}
