package com.unilaw.todo.service;

import com.unilaw.todo.dto.request.ListRequest;
import com.unilaw.todo.dto.response.*;
import com.unilaw.todo.model.*;
import com.unilaw.todo.repository.*;
import com.unilaw.todo.service.filter.*;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.*;
import java.util.stream.Collectors;

/**
 * Реализация интерфейса
 */
@Service
public class ListService implements IListService {

    private final TaskRepository taskRepository;

    private final ListRepository listRepository;

    private static final int DEFAULT_PAGE_SIZE = 10;

    /**
     * Конструктор
     *
     * @param taskRepository - репозиторий для доступа к делам
     * @param listRepository - репозиторий для доступа к спискам
     */
    @Autowired
    public ListService(TaskRepository taskRepository, ListRepository listRepository) {
        this.taskRepository = taskRepository;
        this.listRepository = listRepository;
    }

    /**
     * Формирование ответа (список списков)
     *
     * @return ответ-список списков
     */
    @Override
    public AllListsResponse getLists(String filter, String sort, Integer page) {

        if (page == null) {
            page = 0;
        }

        Page<ListEntity> listPage;
        Pageable sortAndPageable = PageRequest.of(page, DEFAULT_PAGE_SIZE, Sort.by(sort).descending());
        if (filter == null) {
            listPage = listRepository.findAll(sortAndPageable);
        } else {
            listPage = listRepository.findAll(Specification.where(getFilter(filter)), sortAndPageable);
        }

        List<ListResponse> listResponse = listPage.stream().map(ListService::createListResponse).collect(Collectors.toList());
        AllListsResponse allListResponse = new AllListsResponse();
        allListResponse.setLists(listResponse);

        return allListResponse;
    }

    /**
     * Инициализация полей сущности, сохранение в БД
     *
     * @param listRequest - данные для создания списка
     * @return ответ на запрос (формируется в методе)
     */
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

    /**
     * Изменение списка по id
     *
     * @param listRequest - данные для изменения списка
     * @param listId      -  идентификатор изменяемого списка
     * @return ответ на запрос (формируется в методе)
     * @throws NotFoundException
     */
    @Override
    public ListResponse changeList(ListRequest listRequest, UUID listId) throws NotFoundException {
        ListEntity list = listRepository.findById(listId).orElseThrow(() -> new NotFoundException("Couldn't find list with id"));

        list.setName(listRequest.getName());
        list.setUpdatedDate(LocalDateTime.now());
        listRepository.save(list);

        return createListResponse(list);
    }

    /**
     * Удаление списка по id
     *
     * @param listId - идентификатор удаляемого списка
     * @throws NotFoundException
     */
    @Override
    public void deleteList(UUID listId) throws NotFoundException {
        if (!listRepository.existsById(listId)) {
            throw new NotFoundException("Couldn't find list with id");
        }

        listRepository.deleteById(listId);
    }

    /**
     * Формирование ответа (список)
     *
     * @param listEntity - объект сущности (список)
     * @return ответ на запрос
     */
    private static ListResponse createListResponse(ListEntity listEntity) {
        ListResponse listResponse = new ListResponse();
        listResponse.setId(listEntity.getId());
        listResponse.setName(listEntity.getName());
        listResponse.setCreatedDate(listEntity.getCreatedDate());
        listResponse.setUpdatedDate(listEntity.getUpdatedDate());

        return listResponse;
    }

    /**
     * Метод создания спецификации для фильтрации списка
     *
     * @param filter - строка, содержащая ключ, операцию и значение
     * @return объект (key,operation,value)
     */
    public ListSpecification getFilter(String filter) {

        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),"); //(key)(operation)(value) example: ?filter=name:'math ...'
        // Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)('[\\w\\s]+'),"); //(key)(operation)(value) example: ?filter=name:'math exam'
        Matcher matcher = pattern.matcher(filter + ",");
        matcher.find();

        SearchCriteria criteria = new SearchCriteria();
        criteria.setKey(matcher.group(1));
        criteria.setOperation(matcher.group(2));
        criteria.setValue(matcher.group(3)/*.replace("'","")*/);

        ListSpecification specFilter = new ListSpecification(criteria);
        return specFilter;
    }
}
