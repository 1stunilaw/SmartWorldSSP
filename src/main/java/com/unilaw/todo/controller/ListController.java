package com.unilaw.todo.controller;

import com.unilaw.todo.model.ListEntity;
import com.unilaw.todo.repository.ListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.util.*;

@RestController
@RequestMapping("/api")
public class ListController {

    @Autowired
    private ListRepository listRepository;

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

}
