package com.unilaw.todo.repository;

import com.unilaw.todo.model.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.*;


import java.util.*;

/**
 * Репозиторий для списков дел
 */
public interface ListRepository extends JpaRepository<ListEntity, UUID>, JpaSpecificationExecutor<ListEntity> {

}
