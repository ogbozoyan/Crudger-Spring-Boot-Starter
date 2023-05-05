package com.crudlogger.crudloggerstarter.aspect.logger.service;


import com.crudlogger.crudloggerstarter.aspect.logger.model.LogEntity;

/**
 * @author ogbozoyan
 * @date 11.04.2023
 */
public interface LogEntityService {
    LogEntity save(LogEntity entity);
}
