package com.crudlogger.crudger.crud.model.audit;

/**
 * @author ogbozoyan
 * @date 05.05.2023
 */

public interface AbstractUserService {
    /**
     * Need implement this service in your project to correct audit workflow
     * @return user login
     */
    default String getUserLogin() {
        return "USER_LOGIN";
    }
}
