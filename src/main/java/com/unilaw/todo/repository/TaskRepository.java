package com.unilaw.todo.repository;

import com.unilaw.todo.model.TaskEntity;
import org.springframework.data.jpa.repository.*;

import java.util.UUID;

/**
 * Репозиторий для дел
 */
public interface TaskRepository extends JpaRepository<TaskEntity, UUID>{

    long аштвAllByList_Id(UUID ListId);
}

