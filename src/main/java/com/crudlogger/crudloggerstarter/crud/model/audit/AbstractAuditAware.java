package com.crudlogger.crudloggerstarter.crud.model.audit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Slf4j
@Component("auditorAware")
public abstract class AbstractAuditAware implements AuditorAware<String> {
    /**
     * Need implement this service in your project to correct audit workflow
     */
    @Qualifier("abstractUserServiceImpl")
    protected final AbstractUserServiceImpl userServiceImpl;

    protected AbstractAuditAware(AbstractUserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    /**
     * @return Optional<String> User login
     */
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.ofNullable(userServiceImpl.getUserLogin());
    }
}

