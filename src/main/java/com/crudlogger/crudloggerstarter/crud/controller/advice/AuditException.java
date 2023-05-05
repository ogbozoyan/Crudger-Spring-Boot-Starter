package com.crudlogger.crudloggerstarter.crud.controller.advice;

/**
 * @author ogbozoyan
 * @date 09.03.2023
 */
public class AuditException extends AbstractException {
    public AuditException(String msg) {
        super(msg);
    }
}
