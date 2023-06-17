package ru.crudger.crudger.crud.dto.specification.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.springframework.data.domain.Sort;
import ru.crudger.crudger.crud.dto.specification.enums.SortDirection;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * A data contract for sorting request there should be a key and direction.
 *
 * @author ogbozoyan
 * @date 01.03.2023
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SortRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 3194362295851723069L;

    private String key;

    private SortDirection direction;

    public SortRequest(String key, SortDirection direction) {
        this.key = key;
        this.direction = direction;
    }

    public SortRequest() {
    }

    /**
     * Converts a Spring Sort object to a list of SortRequest objects.
     *
     * @param s The Spring Sort object to convert.
     * @return A list of SortRequest objects representing the sorting request.
     */
    public static List<SortRequest> getSortRequests(Sort s) {
        return s.stream().map(x -> {
            SortDirection curDirection = null;
            if (x.getDirection().isAscending()) {
                curDirection = SortDirection.ASC;
            } else if (x.getDirection().isDescending()) {
                curDirection = SortDirection.DESC;
            }
            return SortRequest.builder().key(x.getProperty()).direction(curDirection).build();
        }).toList();
    }

    public static SortRequestBuilder builder() {
        return new SortRequestBuilder();
    }

    public String getKey() {
        return this.key;
    }

    public SortDirection getDirection() {
        return this.direction;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setDirection(SortDirection direction) {
        this.direction = direction;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof SortRequest)) return false;
        final SortRequest other = (SortRequest) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$key = this.getKey();
        final Object other$key = other.getKey();
        if (this$key == null ? other$key != null : !this$key.equals(other$key)) return false;
        final Object this$direction = this.getDirection();
        final Object other$direction = other.getDirection();
        if (this$direction == null ? other$direction != null : !this$direction.equals(other$direction)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof SortRequest;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $key = this.getKey();
        result = result * PRIME + ($key == null ? 43 : $key.hashCode());
        final Object $direction = this.getDirection();
        result = result * PRIME + ($direction == null ? 43 : $direction.hashCode());
        return result;
    }

    public String toString() {
        return "SortRequest(key=" + this.getKey() + ", direction=" + this.getDirection() + ")";
    }

    public static class SortRequestBuilder {
        private String key;
        private SortDirection direction;

        SortRequestBuilder() {
        }

        public SortRequestBuilder key(String key) {
            this.key = key;
            return this;
        }

        public SortRequestBuilder direction(SortDirection direction) {
            this.direction = direction;
            return this;
        }

        public SortRequest build() {
            return new SortRequest(this.key, this.direction);
        }

        public String toString() {
            return "SortRequest.SortRequestBuilder(key=" + this.key + ", direction=" + this.direction + ")";
        }
    }
}