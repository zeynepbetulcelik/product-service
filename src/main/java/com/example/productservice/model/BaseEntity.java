package com.example.productservice.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Getter
    @Setter
    @CreatedDate
    @Column(nullable = false)
    @ColumnDefault("current_timestamp")
    private LocalDateTime createdAt;

    @Getter
    @Setter
    @LastModifiedDate
    @Column(nullable = false)
    @ColumnDefault("current_timestamp")
    private LocalDateTime updatedAt;

    public abstract Object getId();

}