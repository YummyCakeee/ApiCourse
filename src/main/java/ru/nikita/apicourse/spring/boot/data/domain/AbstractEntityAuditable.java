package ru.nikita.apicourse.spring.boot.data.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractEntityAuditable extends AbstractEntity{
    @Column(name = "created_at", updatable = false, columnDefinition = "TIMESTAMP")
    protected LocalDateTime createdAt;
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP")
    protected LocalDateTime updatedAt;

    @PrePersist
    private void prePersist() {
        LocalDateTime currentTIme = LocalDateTime.now();
        createdAt = currentTIme;
        updatedAt = currentTIme;
    }

    @PreUpdate
    private void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
