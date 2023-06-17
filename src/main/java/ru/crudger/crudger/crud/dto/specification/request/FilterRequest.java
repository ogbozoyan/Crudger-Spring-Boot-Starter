package ru.crudger.crudger.crud.dto.specification.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import ru.crudger.crudger.crud.dto.specification.enums.FieldType;
import ru.crudger.crudger.crud.dto.specification.enums.Operator;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * A data contract for filter request there should be a key, operator, value and fieldType.
 *
 * @author ogbozoyan
 * @date 01.03.2023
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FilterRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 6293344849078612450L;

    private String key;

    private Operator operator;

    private FieldType fieldType;

    private transient Object value;

    private transient Object valueTo;

    private transient List<Object> values;

    public FilterRequest(String key, Operator operator, FieldType fieldType, Object value, Object valueTo, List<Object> values) {
        this.key = key;
        this.operator = operator;
        this.fieldType = fieldType;
        this.value = value;
        this.valueTo = valueTo;
        this.values = values;
    }

    public FilterRequest() {
    }

    public static FilterRequestBuilder builder() {
        return new FilterRequestBuilder();
    }

    public String getKey() {
        return this.key;
    }

    public Operator getOperator() {
        return this.operator;
    }

    public FieldType getFieldType() {
        return this.fieldType;
    }

    public Object getValue() {
        return this.value;
    }

    public Object getValueTo() {
        return this.valueTo;
    }

    public List<Object> getValues() {
        return this.values;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public void setFieldType(FieldType fieldType) {
        this.fieldType = fieldType;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public void setValueTo(Object valueTo) {
        this.valueTo = valueTo;
    }

    public void setValues(List<Object> values) {
        this.values = values;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof FilterRequest)) return false;
        final FilterRequest other = (FilterRequest) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$key = this.getKey();
        final Object other$key = other.getKey();
        if (this$key == null ? other$key != null : !this$key.equals(other$key)) return false;
        final Object this$operator = this.getOperator();
        final Object other$operator = other.getOperator();
        if (this$operator == null ? other$operator != null : !this$operator.equals(other$operator)) return false;
        final Object this$fieldType = this.getFieldType();
        final Object other$fieldType = other.getFieldType();
        if (this$fieldType == null ? other$fieldType != null : !this$fieldType.equals(other$fieldType)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof FilterRequest;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $key = this.getKey();
        result = result * PRIME + ($key == null ? 43 : $key.hashCode());
        final Object $operator = this.getOperator();
        result = result * PRIME + ($operator == null ? 43 : $operator.hashCode());
        final Object $fieldType = this.getFieldType();
        result = result * PRIME + ($fieldType == null ? 43 : $fieldType.hashCode());
        return result;
    }

    public String toString() {
        return "FilterRequest(key=" + this.getKey() + ", operator=" + this.getOperator() + ", fieldType=" + this.getFieldType() + ", value=" + this.getValue() + ", valueTo=" + this.getValueTo() + ", values=" + this.getValues() + ")";
    }

    public static class FilterRequestBuilder {
        private String key;
        private Operator operator;
        private FieldType fieldType;
        private Object value;
        private Object valueTo;
        private List<Object> values;

        FilterRequestBuilder() {
        }

        public FilterRequestBuilder key(String key) {
            this.key = key;
            return this;
        }

        public FilterRequestBuilder operator(Operator operator) {
            this.operator = operator;
            return this;
        }

        public FilterRequestBuilder fieldType(FieldType fieldType) {
            this.fieldType = fieldType;
            return this;
        }

        public FilterRequestBuilder value(Object value) {
            this.value = value;
            return this;
        }

        public FilterRequestBuilder valueTo(Object valueTo) {
            this.valueTo = valueTo;
            return this;
        }

        public FilterRequestBuilder values(List<Object> values) {
            this.values = values;
            return this;
        }

        public FilterRequest build() {
            return new FilterRequest(this.key, this.operator, this.fieldType, this.value, this.valueTo, this.values);
        }

        public String toString() {
            return "FilterRequest.FilterRequestBuilder(key=" + this.key + ", operator=" + this.operator + ", fieldType=" + this.fieldType + ", value=" + this.value + ", valueTo=" + this.valueTo + ", values=" + this.values + ")";
        }
    }
}