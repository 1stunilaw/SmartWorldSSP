package com.unilaw.todo.repository;

import com.unilaw.todo.model.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

/**
 * Репозиторий для дел
 */
public interface TaskRepository extends JpaRepository<TaskEntity, UUID> {

    /**
     * Найти все дела указанного списка
     *
     * @param list - идентификатор списка
     * @return список дел указанного списка
     */
    List<TaskEntity> findAllByListId(ListEntity list);
}

