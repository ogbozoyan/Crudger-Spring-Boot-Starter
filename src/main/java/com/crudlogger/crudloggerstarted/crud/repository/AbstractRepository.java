package com.crudlogger.crudloggerstarted.crud.repository;

import com.crudlogger.crudloggerstarted.crud.model.bigint.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author ogbozoyan
 * @date 29.04.2023
 */
@NoRepositoryBean
public interface AbstractRepository<T extends AbstractEntity> extends JpaRepository<T, Long>,
        JpaSpecificationExecutor<T> {
}
