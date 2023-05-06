package com.crudlogger.crudger.aspect.logger.repository;

import com.crudlogger.crudger.aspect.logger.model.LogEntity;
import com.crudlogger.crudger.crud.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ogbozoyan
 * @date 12.04.2023
 */
@Repository
public interface LogEntityRepository extends AbstractRepository<LogEntity> {
}
