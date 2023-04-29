package com.crudlogger.crudloggerstarted.crud.controller.advice;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ogbozoyan
 * @date 17.02.2023
 */
@Data
@AllArgsConstructor
public class CustomErrorMessage implements Serializable {
    private Integer statusCode;
    private Date timestamp;
    private String message;
    private String description;
}
