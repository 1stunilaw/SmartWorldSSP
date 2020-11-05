package com.unilaw.todo.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

/**
 * Сущность списка дел
 */
@Data
@Entity
@Table(name = "lists")
@JsonIgnoreProperties(value = {"createdDate", "updatedDate"})
public final class ListEntity implements Serializable {

    /**
     * ID списка дел
     * Является первичным ключом, заполняется автоматически
     */
    @Id
    @Column(name = "id")
    private UUID id;

    /**
     * Название списка
     */
    @Column(name = "name")
    private String name;

    /**
     * Время создания записи
     * Не может быть пустым, не пеезаписывается
     */
    @Column(name = "create_date", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdDate;

    /**
     * Время последнего изменения записи
     * Не может быть пустым
     */
    @Column(name = "update_date", nullable = false)
    @LastModifiedDate
    private LocalDateTime updatedDate;
}
