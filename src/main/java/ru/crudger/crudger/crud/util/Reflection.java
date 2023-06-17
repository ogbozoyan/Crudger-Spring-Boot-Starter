package ru.crudger.crudger.crud.util;

import org.slf4j.Logger;

import java.lang.reflect.ParameterizedType;

/**
 * @author ogbozoyan
 * @date 03.03.2023
 * To get generic class
 */
public class Reflection {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Reflection.class);

    public Reflection() {
    }

    public static Class<?> getClassGeneric(Object obj) {
        ParameterizedType genericSuperclass = (ParameterizedType) obj.getClass().getGenericSuperclass();
        return (Class<?>) genericSuperclass.getActualTypeArguments()[0];
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Reflection)) return false;
        final Reflection other = (Reflection) o;
        if (!other.canEqual((Object) this)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Reflection;
    }

    public int hashCode() {
        int result = 1;
        return result;
    }

    public String toString() {
        return "Reflection()";
    }
}
