package com.crudlogger.crudloggerstarter.crud.repository.integer;

import com.crudlogger.crudloggerstarter.crud.model.entity.AbstractEntityInteger;
import com.crudlogger.crudloggerstarter.crud.model.entity.AbstractEntityLong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author Lytov
 * @date 10.05.2023
 */

@NoRepositoryBean
public interface AbstractRepositoryInteger<T extends AbstractEntityInteger>  extends JpaRepository<T, Integer>,
        JpaSpecificationExecutor<T> {
}
