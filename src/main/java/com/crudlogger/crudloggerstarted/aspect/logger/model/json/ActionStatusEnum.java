package com.crudlogger.crudloggerstarted.aspect.logger.model.json;

import lombok.Getter;

/**
 * @author ogbozoyan
 * @date 11.04.2023
 */
@Getter
public enum ActionStatusEnum {
    SUCCESSFULLY,
    FAILED,
    ERROR,
    UNKNOWN
}
