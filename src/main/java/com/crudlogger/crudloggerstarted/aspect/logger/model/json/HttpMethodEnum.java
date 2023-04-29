package com.crudlogger.crudloggerstarted.aspect.logger.model.json;

import lombok.Getter;

/**
 * @author ogbozoyan
 * @date 04.04.2023
 */
@Getter
public enum HttpMethodEnum {
    GET,
    POST,
    PATCH,
    DELETE,
    UNDEFINED
}
