package com.crudlogger.crudloggerstarter.crud.controller.advice;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author ogbozoyan
 * @date 17.02.2023
 */
@Data
public class AbstractException extends RuntimeException implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    public AbstractException(String msg){
        super(msg);
    }
}
