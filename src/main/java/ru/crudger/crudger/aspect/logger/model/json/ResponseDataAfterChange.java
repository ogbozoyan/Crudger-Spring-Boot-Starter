package ru.crudger.crudger.aspect.logger.model.json;

import java.io.Serializable;

/**
 * The {@code ResponseDataAfterChange} class represents the response data after a change operation.
 *
 * <p>It contains one property:</p>
 * <ul>
 *     <li>{@link #body}: The body of the response</li>
 * </ul>
 *
 * <p>This class is typically used for DELETE, SAVE, and UPDATE methods.</p>
 *
 * @author ogbozoyan
 * @date 17.03.2023
 */
public class ResponseDataAfterChange implements Serializable {
    private String body;

    public ResponseDataAfterChange(String body) {
        this.body = body;
    }

    public ResponseDataAfterChange() {
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ResponseDataAfterChange)) return false;
        final ResponseDataAfterChange other = (ResponseDataAfterChange) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$body = this.getBody();
        final Object other$body = other.getBody();
        if (this$body == null ? other$body != null : !this$body.equals(other$body)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ResponseDataAfterChange;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $body = this.getBody();
        result = result * PRIME + ($body == null ? 43 : $body.hashCode());
        return result;
    }

    public String toString() {
        return "ResponseDataAfterChange(body=" + this.getBody() + ")";
    }
}
