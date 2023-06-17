package ru.crudger.crudger.crud.model.entity;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class AbstractEntityString implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected String id;

    public AbstractEntityString(String id) {
        this.id = id;
    }

    public AbstractEntityString() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof AbstractEntityString)) return false;
        final AbstractEntityString other = (AbstractEntityString) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof AbstractEntityString;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        return result;
    }

    public String toString() {
        return "AbstractEntityString(id=" + this.getId() + ")";
    }
}
