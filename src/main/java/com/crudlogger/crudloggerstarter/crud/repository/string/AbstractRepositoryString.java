package com.crudlogger.crudloggerstarter.crud.repository.string;

import com.crudlogger.crudloggerstarter.crud.model.entity.AbstractEntityString;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author Lytov
 * @date 10.05.2023
 */

@NoRepositoryBean
public interface AbstractRepositoryString<T extends AbstractEntityString>  extends JpaRepository<T, String>,
        JpaSpecificationExecutor<T> {
}
