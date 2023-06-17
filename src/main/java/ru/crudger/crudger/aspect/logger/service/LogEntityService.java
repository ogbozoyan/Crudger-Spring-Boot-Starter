package ru.crudger.crudger.aspect.logger.service;


import ru.crudger.crudger.aspect.logger.model.LogEntity;

/**
 * The {@code LogEntityService} interface defines operations for managing {@link LogEntity} objects.
 *
 * @author ogbozoyan
 * @date 11.04.2023
 */
public interface LogEntityService {
    LogEntity save(LogEntity entity);
}
