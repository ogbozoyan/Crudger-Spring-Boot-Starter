package ru.crudger.crudger.crud.controller.advice;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ogbozoyan
 * @date 17.02.2023
 */
public class CustomErrorMessage implements Serializable {
    private Integer statusCode;
    private Date timestamp;
    private String message;
    private String description;

    public CustomErrorMessage(Integer statusCode, Date timestamp, String message, String description) {
        this.statusCode = statusCode;
        this.timestamp = timestamp;
        this.message = message;
        this.description = description;
    }

    public Integer getStatusCode() {
        return this.statusCode;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    public String getMessage() {
        return this.message;
    }

    public String getDescription() {
        return this.description;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof CustomErrorMessage)) return false;
        final CustomErrorMessage other = (CustomErrorMessage) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$statusCode = this.getStatusCode();
        final Object other$statusCode = other.getStatusCode();
        if (this$statusCode == null ? other$statusCode != null : !this$statusCode.equals(other$statusCode))
            return false;
        final Object this$timestamp = this.getTimestamp();
        final Object other$timestamp = other.getTimestamp();
        if (this$timestamp == null ? other$timestamp != null : !this$timestamp.equals(other$timestamp)) return false;
        final Object this$message = this.getMessage();
        final Object other$message = other.getMessage();
        if (this$message == null ? other$message != null : !this$message.equals(other$message)) return false;
        final Object this$description = this.getDescription();
        final Object other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CustomErrorMessage;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $statusCode = this.getStatusCode();
        result = result * PRIME + ($statusCode == null ? 43 : $statusCode.hashCode());
        final Object $timestamp = this.getTimestamp();
        result = result * PRIME + ($timestamp == null ? 43 : $timestamp.hashCode());
        final Object $message = this.getMessage();
        result = result * PRIME + ($message == null ? 43 : $message.hashCode());
        final Object $description = this.getDescription();
        result = result * PRIME + ($description == null ? 43 : $description.hashCode());
        return result;
    }

    public String toString() {
        return "CustomErrorMessage(statusCode=" + this.getStatusCode() + ", timestamp=" + this.getTimestamp() + ", message=" + this.getMessage() + ", description=" + this.getDescription() + ")";
    }
}
