package ru.crudger.crudger.crud.repository.string;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import ru.crudger.crudger.crud.model.entity.AbstractEntityString;

/**
 * @author Lytov
 * @date 10.05.2023
 */

@NoRepositoryBean
public interface AbstractRepositoryString<T extends AbstractEntityString>  extends JpaRepository<T, String>,
        JpaSpecificationExecutor<T> {
}
