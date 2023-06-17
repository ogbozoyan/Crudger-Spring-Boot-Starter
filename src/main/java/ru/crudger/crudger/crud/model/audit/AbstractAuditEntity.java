package ru.crudger.crudger.crud.model.audit;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import ru.crudger.crudger.crud.model.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.sql.Timestamp;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractAuditEntity extends AbstractEntity implements Serializable {
    @CreatedBy
    @Column(name = "created_by")
    protected String createdBy;
    @CreatedDate
    @Column(name = "created_date")
    protected Timestamp createdDate;
    @LastModifiedBy
    @Column(name = "updated_by")
    protected String updatedBy;
    @LastModifiedDate
    @Column(name = "updated_date")
    protected Timestamp updatedDate;

    public AbstractAuditEntity(String createdBy, Timestamp createdDate, String updatedBy, Timestamp updatedDate) {
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.updatedBy = updatedBy;
        this.updatedDate = updatedDate;
    }

    public AbstractAuditEntity() {
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public Timestamp getCreatedDate() {
        return this.createdDate;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public Timestamp getUpdatedDate() {
        return this.updatedDate;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public void setUpdatedDate(Timestamp updatedDate) {
        this.updatedDate = updatedDate;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof AbstractAuditEntity)) return false;
        final AbstractAuditEntity other = (AbstractAuditEntity) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$createdBy = this.getCreatedBy();
        final Object other$createdBy = other.getCreatedBy();
        if (this$createdBy == null ? other$createdBy != null : !this$createdBy.equals(other$createdBy)) return false;
        final Object this$createdDate = this.getCreatedDate();
        final Object other$createdDate = other.getCreatedDate();
        if (this$createdDate == null ? other$createdDate != null : !this$createdDate.equals(other$createdDate))
            return false;
        final Object this$updatedBy = this.getUpdatedBy();
        final Object other$updatedBy = other.getUpdatedBy();
        if (this$updatedBy == null ? other$updatedBy != null : !this$updatedBy.equals(other$updatedBy)) return false;
        final Object this$updatedDate = this.getUpdatedDate();
        final Object other$updatedDate = other.getUpdatedDate();
        if (this$updatedDate == null ? other$updatedDate != null : !this$updatedDate.equals(other$updatedDate))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof AbstractAuditEntity;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $createdBy = this.getCreatedBy();
        result = result * PRIME + ($createdBy == null ? 43 : $createdBy.hashCode());
        final Object $createdDate = this.getCreatedDate();
        result = result * PRIME + ($createdDate == null ? 43 : $createdDate.hashCode());
        final Object $updatedBy = this.getUpdatedBy();
        result = result * PRIME + ($updatedBy == null ? 43 : $updatedBy.hashCode());
        final Object $updatedDate = this.getUpdatedDate();
        result = result * PRIME + ($updatedDate == null ? 43 : $updatedDate.hashCode());
        return result;
    }

    public String toString() {
        return "AbstractAuditEntity(createdBy=" + this.getCreatedBy() + ", createdDate=" + this.getCreatedDate() + ", updatedBy=" + this.getUpdatedBy() + ", updatedDate=" + this.getUpdatedDate() + ")";
    }
}

