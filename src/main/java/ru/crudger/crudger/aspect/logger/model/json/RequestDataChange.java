package ru.crudger.crudger.aspect.logger.model.json;

import java.io.Serializable;
import java.util.List;

/**
 * The {@code RequestDataChange} class represents the data change in a request.
 *
 * <p>It contains two properties:</p>
 * <ul>
 *     <li>{@link #params}: The list of request parameters</li>
 *     <li>{@link #body}: The body of the request</li>
 * </ul>
 *
 * @author ogbozoyan
 * @date 17.03.2023
 */
public class RequestDataChange implements Serializable {
    private List<Params> params;
    private String body;

    public RequestDataChange(List<Params> params, String body) {
        this.params = params;
        this.body = body;
    }

    public RequestDataChange() {
    }

    public List<Params> getParams() {
        return this.params;
    }

    public String getBody() {
        return this.body;
    }

    public void setParams(List<Params> params) {
        this.params = params;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof RequestDataChange)) return false;
        final RequestDataChange other = (RequestDataChange) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$params = this.getParams();
        final Object other$params = other.getParams();
        if (this$params == null ? other$params != null : !this$params.equals(other$params)) return false;
        final Object this$body = this.getBody();
        final Object other$body = other.getBody();
        if (this$body == null ? other$body != null : !this$body.equals(other$body)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof RequestDataChange;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $params = this.getParams();
        result = result * PRIME + ($params == null ? 43 : $params.hashCode());
        final Object $body = this.getBody();
        result = result * PRIME + ($body == null ? 43 : $body.hashCode());
        return result;
    }

    public String toString() {
        return "RequestDataChange(params=" + this.getParams() + ", body=" + this.getBody() + ")";
    }
}