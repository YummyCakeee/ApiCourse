package ru.nikita.apicourse.spring.boot.data.domain;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.domain.Persistable;

import java.io.Serializable;

@Getter
@MappedSuperclass
@EqualsAndHashCode(of = {"id"})
public abstract class AbstractEntity implements Persistable<Long>, Serializable {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    public boolean isNew() {
        return null == id;
    }
}
