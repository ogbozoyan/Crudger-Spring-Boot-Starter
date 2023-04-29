package com.crudlogger.crudloggerstarted.aspect.logger.service;

import com.crudlogger.crudloggerstarted.aspect.logger.model.LogEntity;
import com.crudlogger.crudloggerstarted.aspect.logger.repository.LogEntityRepository;
import com.crudlogger.crudloggerstarted.crud.controller.advice.SaveException;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ogbozoyan
 * @date 07.04.2023
 */
@Service
public class LogEntityServiceImpl implements LogEntityService {
    @Autowired
    private ObjectWriter objectWriter;
    @Autowired
    private LogEntityRepository repository;

    @Override
    public LogEntity save(LogEntity entity) {
        try {
            return repository.save(entity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SaveException(e.getMessage());
        }
    }
}
