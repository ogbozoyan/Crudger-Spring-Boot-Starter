package com.crudlogger.crudloggerstarted.aspect.logger.repository;

import com.crudlogger.crudloggerstarted.aspect.logger.model.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ogbozoyan
 * @date 12.04.2023
 */
@Repository
public interface LogEntityRepository extends JpaRepository<LogEntity, Long> {
}
