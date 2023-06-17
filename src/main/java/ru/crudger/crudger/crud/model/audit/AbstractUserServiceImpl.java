package ru.crudger.crudger.crud.model.audit;

import org.springframework.stereotype.Component;

/**
 * To correct work of AuditAware need to extend this AbstractUserServiceImpl in your project and override getUserLogin method
 * if you already have UserService just implement AbstractUserService
 *
 * @author ogbozoyan
 * @date 05.05.2023
 */
@Component("abstractUserServiceImpl")
public abstract class AbstractUserServiceImpl implements AbstractUserService {

}
