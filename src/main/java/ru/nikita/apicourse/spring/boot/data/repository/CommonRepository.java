package ru.nikita.apicourse.spring.boot.data.repository;

import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CommonRepository <T extends Persistable<Long>> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {
}
