package ru.crudger.crudger.crud.controller.advice;


import java.io.Serial;
import java.io.Serializable;

/**
 * @author ogbozoyan
 * @date 17.02.2023
 */
public abstract class AbstractException extends RuntimeException implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;


    public AbstractException(String msg) {
        super(msg);
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof AbstractException)) return false;
        final AbstractException other = (AbstractException) o;
        if (!other.canEqual((Object) this)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof AbstractException;
    }

    public int hashCode() {
        int result = 1;
        return result;
    }

    public String toString() {
        return "AbstractException()";
    }
}
