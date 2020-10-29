package com.unilaw.todo.repository;

import com.unilaw.todo.model.ListEntity;
import org.springframework.data.jpa.repository.*;

import java.util.UUID;

/**
 * Репозиторий для списков дел
 */
public interface ListRepository extends JpaRepository<ListEntity, UUID> {

}
