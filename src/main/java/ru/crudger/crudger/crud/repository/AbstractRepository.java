package ru.crudger.crudger.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import ru.crudger.crudger.crud.model.entity.AbstractEntity;

/**
 * An abstract repository interface that extends JpaRepository and JpaSpecificationExecutor.
 *
 * @param <E> The entity type.
 * @author ogbozoyan
 * @date 08.02.2023
 */

@NoRepositoryBean
public interface AbstractRepository<E extends AbstractEntity> extends JpaRepository<E, Long>,
        JpaSpecificationExecutor<E> {
}
