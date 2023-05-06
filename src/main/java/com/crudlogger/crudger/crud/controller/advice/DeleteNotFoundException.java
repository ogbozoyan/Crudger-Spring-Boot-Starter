package com.crudlogger.crudger.crud.controller.advice;

/**
 * @author ogbozoyan
 * @date 08.03.2023
 */
public class DeleteNotFoundException extends AbstractException {
    public DeleteNotFoundException(String msg) {
        super(msg);
    }
}
