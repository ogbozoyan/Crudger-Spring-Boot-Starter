package ru.crudger.crudger.crud.model.audit;

import org.springframework.stereotype.Component;

/**
 * @author ogbozoyan
 * @date 05.05.2023
 */
@Component
public interface AbstractUserService {
    /**
     * Need implement this service in your project to correct audit workflow
     *
     * @return user login
     */
    default String getUserLogin() {
        return "USER_LOGIN";
    }
}
