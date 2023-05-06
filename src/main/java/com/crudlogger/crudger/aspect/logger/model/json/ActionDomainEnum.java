package com.crudlogger.crudger.aspect.logger.model.json;

import lombok.Getter;

/**
 * @author ogbozoyan
 * @date 06.04.2023
 */
@Getter
public enum ActionDomainEnum {
    TEST,
    UNDEFINED,
    CRUD,
    AUTH
}
