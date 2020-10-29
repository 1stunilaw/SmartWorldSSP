package com.unilaw.todo.controller;

import com.unilaw.todo.dto.request.ListRequest;
import com.unilaw.todo.dto.response.ListResponse;
import com.unilaw.todo.repository.ListRepository;
import com.unilaw.todo.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
     * @param list - запрос на создание (параметр - имя списка)
     * @return ответ на запрос (созданный список)
     */
    @PostMapping("/list")
    @ResponseStatus(HttpStatus.CREATED)
    public ListResponse createList(@RequestBody ListRequest list) {
        return listService.createList(list);
    }
}
