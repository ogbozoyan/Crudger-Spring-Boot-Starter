package com.crudlogger.crudloggerstarted.aspect.logger.service;


import com.crudlogger.crudloggerstarted.aspect.logger.model.LogEntity;

/**
 * @author ogbozoyan
 * @date 11.04.2023
 */
public interface LogEntityService {
    LogEntity save(LogEntity entity);
}
