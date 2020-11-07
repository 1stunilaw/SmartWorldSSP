package com.unilaw.todo.repository;

import com.unilaw.todo.model.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

/**
 * Репозиторий для дел
 */
public interface TaskRepository extends JpaRepository<TaskEntity, UUID> {
    List<TaskEntity> findAllByListId(ListEntity list);
}

