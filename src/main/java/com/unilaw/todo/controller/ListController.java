package com.unilaw.todo.controller;

import com.unilaw.todo.dto.request.ListRequest;
import com.unilaw.todo.dto.response.*;
import com.unilaw.todo.repository.ListRepository;
import com.unilaw.todo.service.ListService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ListController {

    private final ListService listService;

    /**
     * Конструктор
     *
     * @param listService
     */
    public ListController(ListService listService) {
        this.listService = listService;
    }

    /**
     * Метод для проверки подключения
     */
    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    /**
     * Метод возврата списка списков
     *
     * @return список списков
     */
    @GetMapping("/list")
    public AllListsResponse getLists() {
        return listService.getLists();
    }

    /**
     * Метод создания списка
     *
     * @param list - запрос на создание (параметр - имя списка)
     * @return ответ на запрос (созданный список)
     */
    @PostMapping("/list")
    @ResponseStatus(HttpStatus.CREATED)
    public ListResponse createList(@RequestBody ListRequest list) {
        return listService.createList(list);
    }

    /**
     * Метод изменения списка
     *
     * @param list   - запрос на создание (параметр - имя списка)
     * @param listId - идентификатор изменяемого списка
     * @return ответ на запрос (измененный список)
     * @throws NotFoundException
     */
    @PutMapping("/list/{id}")
    public ListResponse changeList(@RequestBody ListRequest list, @PathVariable("id") UUID listId) throws NotFoundException {
        return listService.changeList(list, listId);
    }

    /**
     * Метод удаления списка
     *
     * @param listId - идентификатор удаляемого списка
     */
    @DeleteMapping("/list/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteList(@PathVariable("id") UUID listId) throws NotFoundException {
        listService.deleteList(listId);
    }
}
