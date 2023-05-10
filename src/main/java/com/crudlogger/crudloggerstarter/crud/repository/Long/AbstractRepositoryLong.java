package com.crudlogger.crudloggerstarter.crud.repository.Long;

import com.crudlogger.crudloggerstarter.crud.model.entity.AbstractEntityLong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author ogbozoyan
 * @date 29.04.2023
 */
@NoRepositoryBean
public interface AbstractRepositoryLong<E extends AbstractEntityLong> extends JpaRepository<E, Long>,
        JpaSpecificationExecutor<E> {
}