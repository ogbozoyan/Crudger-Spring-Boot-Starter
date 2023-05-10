package com.crudlogger.crudloggerstarter.aspect.logger.repository;

import com.crudlogger.crudloggerstarter.aspect.logger.model.LogEntity;
import com.crudlogger.crudloggerstarter.crud.repository.biginit.AbstractRepositoryBigint;
import org.springframework.stereotype.Repository;

/**
 * @author ogbozoyan
 * @date 12.04.2023
 */
@Repository
public interface LogEntityRepository extends AbstractRepositoryBigint<LogEntity> {
}
