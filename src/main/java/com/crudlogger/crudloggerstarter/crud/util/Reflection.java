package com.crudlogger.crudloggerstarter.crud.util;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.ParameterizedType;

/**
 * @author ogbozoyan
 * @date 03.03.2023
 * To get generic class
 */
@Data
@NoArgsConstructor
@Slf4j
public class Reflection {
    public static Class<?> getClassGeneric(Object obj){
        ParameterizedType genericSuperclass = (ParameterizedType) obj.getClass().getGenericSuperclass();
        return (Class<?>) genericSuperclass.getActualTypeArguments()[0];
    }
}
