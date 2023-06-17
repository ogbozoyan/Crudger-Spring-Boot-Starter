package ru.crudger.crudger.aspect.logger.model.json;

import java.io.Serializable;

/**
 * The {@code Params} class represents a key-value pair for request parameters.
 *
 * <p>It provides two properties:</p>
 * <ul>
 *     <li>{@link #key}: The key of the parameter</li>
 *     <li>{@link #value}: The value of the parameter as an array of strings</li>
 * </ul>
 *
 * @author ogbozoyan
 * @date 17.03.2023
 */
public class Params implements Serializable {
    private String key;
    private String[] value;

    public Params(String key, String[] value) {
        this.key = key;
        this.value = value;
    }

    public Params() {
    }

    public String getKey() {
        return this.key;
    }

    public String[] getValue() {
        return this.value;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(String[] value) {
        this.value = value;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Params)) return false;
        final Params other = (Params) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$key = this.getKey();
        final Object other$key = other.getKey();
        if (this$key == null ? other$key != null : !this$key.equals(other$key)) return false;
        if (!java.util.Arrays.deepEquals(this.getValue(), other.getValue())) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Params;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $key = this.getKey();
        result = result * PRIME + ($key == null ? 43 : $key.hashCode());
        result = result * PRIME + java.util.Arrays.deepHashCode(this.getValue());
        return result;
    }

    public String toString() {
        return "Params(key=" + this.getKey() + ", value=" + java.util.Arrays.deepToString(this.getValue()) + ")";
    }
}
