package ru.crudger.crudger.aspect.logger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;
import ru.crudger.crudger.aspect.logger.model.LogEntity;

import javax.persistence.LockModeType;

/**
 * The {@code LogEntityRepository} interface provides CRUD operations for {@link LogEntity} objects in the database.
 *
 * <p>It is annotated with {@code @Repository} to indicate that it is a repository component.</p>
 *
 * @author ogbozoyan
 * @date 12.04.2023
 */
@Repository
public interface LogEntityRepository extends JpaRepository<LogEntity, Long> {
    /**
     * Saves a {@link LogEntity} object in the database.
     *
     * <p>It is annotated with {@code @Lock} to specify the lock mode for concurrent access.</p>
     *
     * @param entity the log entity to be saved
     * @return the saved log entity
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Override
    //    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    LogEntity save(LogEntity entity);
}