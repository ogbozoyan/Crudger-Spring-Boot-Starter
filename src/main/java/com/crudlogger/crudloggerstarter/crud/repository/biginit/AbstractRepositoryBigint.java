package com.crudlogger.crudloggerstarter.crud.repository.biginit;

import com.crudlogger.crudloggerstarter.crud.model.entity.AbstractEntityBigint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author ogbozoyan
 * @date 29.04.2023
 */
@NoRepositoryBean
public interface AbstractRepositoryBigint<E extends AbstractEntityBigint> extends JpaRepository<E, Long>,
        JpaSpecificationExecutor<E> {
}
