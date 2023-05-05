package com.crudlogger.crudloggerstarter.crud.model.audit;

import org.springframework.stereotype.Component;

/**
 * @author ogbozoyan
 * @date 05.05.2023
 */
@Component("abstractUserServiceImpl")
public abstract class AbstractUserServiceImpl implements AbstractUserService {
    /**
     * To correct work of AuditAware need to extend this AbstractUserServiceImpl in your project and override getUserLogin method
     */
}
