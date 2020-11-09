package com.unilaw.todo.controller;

import com.unilaw.todo.dto.request.ListRequest;
import com.unilaw.todo.dto.response.*;
import com.unilaw.todo.repository.ListRepository;
import com.unilaw.todo.service.ListService;
import io.swagger.annotations.*;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@Api(description = "Набор эндпоинтов для получения создания, изменения, удаления списков")
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
    @ApiOperation("Метод для проверки подключения")
    public String ping() {
        return "pong";
    }

    /**
     * Метод возврата списка списков
     *
     * @return список списков
     */
    @GetMapping("/list")
    @ApiOperation("Метод для получения списка списков (с фильтров, сортировкой и пагинацией)")
    public AllListsResponse getLists(
            @RequestParam(value = "filter", required = false) @ApiParam(value = "Фильтр", example = "name:EXAM") String filter,
            @RequestParam(value = "orderBy", required = false, defaultValue = "createdDate") @ApiParam(value = "Сортировка", example = "name") String sort,
            @RequestParam(value = "page", required = false) @ApiParam(value = "Номер страницы", example = "0") Integer page
    ) {
        return listService.getLists(filter, sort, page);
    }

    /**
     * Метод создания списка
     *
     * @param list - запрос на создание (параметр - имя списка)
     * @return ответ на запрос (созданный список)
     */
    @PostMapping("/list")
    @ApiOperation("Метод для создания списка дел")
    @ResponseStatus(HttpStatus.CREATED)
    public ListResponse createList(@ApiParam(value = "Тело запроса") @RequestBody ListRequest list) {
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
    @ApiOperation("Метод для изменения списка дел")
    public ListResponse changeList(
            @ApiParam(value = "Тело запроса") @RequestBody ListRequest list,
            @ApiParam(value = "id списка дел") @PathVariable("id") UUID listId
    ) throws NotFoundException {
        return listService.changeList(list, listId);
    }

    /**
     * Метод удаления списка
     *
     * @param listId - идентификатор удаляемого списка
     */
    @DeleteMapping("/list/{id}")
    @ApiOperation("Метод для удаления списка дел")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteList(
            @ApiParam(value = "id списка дел") @PathVariable("id") UUID listId
    ) throws NotFoundException {
        listService.deleteList(listId);
    }
}
