package com.crudlogger.crudloggerstarter.crud.repository;

import com.crudlogger.crudloggerstarter.crud.model.AbstractEntityStr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author ogbozoyan
 * @date 18.04.2023
 */
@NoRepositoryBean
public interface AbstractRepositoryStr<T extends AbstractEntityStr> extends JpaRepository<T, String>,
        JpaSpecificationExecutor<T> {
}
