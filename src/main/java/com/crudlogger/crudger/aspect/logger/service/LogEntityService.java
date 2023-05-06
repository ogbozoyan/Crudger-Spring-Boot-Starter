package com.crudlogger.crudger.aspect.logger.service;


import com.crudlogger.crudger.aspect.logger.model.LogEntity;

/**
 * @author ogbozoyan
 * @date 11.04.2023
 */
public interface LogEntityService {
    LogEntity save(LogEntity entity);
}
