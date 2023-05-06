package com.crudlogger.crudger.aspect;


import com.crudlogger.crudger.aspect.logger.model.json.ActionDomainEnum;
import com.crudlogger.crudger.aspect.logger.model.json.ActionEnum;
import com.crudlogger.crudger.aspect.logger.model.json.HttpMethodEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ToLogger {
    ActionEnum action() default ActionEnum.UNDEFINED;

    ActionDomainEnum actionDomain() default ActionDomainEnum.TEST;

    HttpMethodEnum httpMethod() default HttpMethodEnum.UNDEFINED;
    boolean returnResponse() default false;
    boolean isPrintToTerminal() default true;
    boolean isSaveInDataBase() default false;
    boolean printAsJson() default false;
}
