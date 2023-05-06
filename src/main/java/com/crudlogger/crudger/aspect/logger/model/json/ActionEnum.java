package com.crudlogger.crudger.aspect.logger.model.json;

import lombok.Getter;

/**
 * @author ogbozoyan
 * @date 04.04.2023
 */
@Getter
public enum ActionEnum {
    CREATE,
    READ,
    UPDATE,
    DELETE,
    SIGN_IN,
    SIGN_UP,
    UNDEFINED,
    SIGN_OUT
}
