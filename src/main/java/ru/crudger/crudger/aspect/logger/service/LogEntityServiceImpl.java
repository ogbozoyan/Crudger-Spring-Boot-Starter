package ru.crudger.crudger.aspect.logger.service;

import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.crudger.crudger.aspect.logger.model.LogEntity;
import ru.crudger.crudger.aspect.logger.repository.LogEntityRepository;

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
//    @Transactional
//    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRES_NEW)
//    @Transactional(propagation = Propagation.REQUIRES_NEW,timeout = 5)
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 5)
    @Retryable(retryFor = Exception.class, maxAttempts = 2, backoff = @Backoff(delay = 100))
    public LogEntity save(LogEntity entity) {
        try {
            return repository.save(entity);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
