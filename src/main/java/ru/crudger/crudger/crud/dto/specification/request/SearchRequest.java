package ru.crudger.crudger.crud.dto.specification.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.slf4j.Logger;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This is the main request class used in REST API.
 * The class represents a search request with filters, sorts, page, and size parameters.
 * It includes annotations for serialization, deserialization, and naming strategies.
 * The class also uses SLF4J for logging.
 *
 * @author ogbozoyan
 * @date 01.03.2023
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SearchRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 8514625832019794838L;
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(SearchRequest.class);

    private List<FilterRequest> filters;

    private List<SortRequest> sorts;

    private Integer page;

    private Integer size;

    public SearchRequest(List<FilterRequest> filters, List<SortRequest> sorts, Integer page, Integer size) {
        this.filters = filters;
        this.sorts = sorts;
        this.page = page;
        this.size = size;
    }

    public SearchRequest() {
    }

    public static SearchRequestBuilder builder() {
        return new SearchRequestBuilder();
    }

    public List<FilterRequest> getFilters() {
        if (Objects.isNull(this.filters)) this.filters = new ArrayList<>();
        return this.filters;
    }

    public List<SortRequest> getSorts() {
        if (Objects.isNull(this.sorts)) this.sorts = new ArrayList<>();
        return this.sorts;
    }

    public Integer getPage() {
        return this.page;
    }

    public Integer getSize() {
        return this.size;
    }

    public void setFilters(List<FilterRequest> filters) {
        this.filters = filters;
    }

    public void setSorts(List<SortRequest> sorts) {
        this.sorts = sorts;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof SearchRequest)) return false;
        final SearchRequest other = (SearchRequest) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$filters = this.getFilters();
        final Object other$filters = other.getFilters();
        if (this$filters == null ? other$filters != null : !this$filters.equals(other$filters)) return false;
        final Object this$sorts = this.getSorts();
        final Object other$sorts = other.getSorts();
        if (this$sorts == null ? other$sorts != null : !this$sorts.equals(other$sorts)) return false;
        final Object this$page = this.getPage();
        final Object other$page = other.getPage();
        if (this$page == null ? other$page != null : !this$page.equals(other$page)) return false;
        final Object this$size = this.getSize();
        final Object other$size = other.getSize();
        if (this$size == null ? other$size != null : !this$size.equals(other$size)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof SearchRequest;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $filters = this.getFilters();
        result = result * PRIME + ($filters == null ? 43 : $filters.hashCode());
        final Object $sorts = this.getSorts();
        result = result * PRIME + ($sorts == null ? 43 : $sorts.hashCode());
        final Object $page = this.getPage();
        result = result * PRIME + ($page == null ? 43 : $page.hashCode());
        final Object $size = this.getSize();
        result = result * PRIME + ($size == null ? 43 : $size.hashCode());
        return result;
    }

    public String toString() {
        return "SearchRequest(filters=" + this.getFilters() + ", sorts=" + this.getSorts() + ", page=" + this.getPage() + ", size=" + this.getSize() + ")";
    }

    public static class SearchRequestBuilder {
        private List<FilterRequest> filters;
        private List<SortRequest> sorts;
        private Integer page;
        private Integer size;

        SearchRequestBuilder() {
        }

        public SearchRequestBuilder filters(List<FilterRequest> filters) {
            this.filters = filters;
            return this;
        }

        public SearchRequestBuilder sorts(List<SortRequest> sorts) {
            this.sorts = sorts;
            return this;
        }

        public SearchRequestBuilder page(Integer page) {
            this.page = page;
            return this;
        }

        public SearchRequestBuilder size(Integer size) {
            this.size = size;
            return this;
        }

        public SearchRequest build() {
            return new SearchRequest(this.filters, this.sorts, this.page, this.size);
        }

        public String toString() {
            return "SearchRequest.SearchRequestBuilder(filters=" + this.filters + ", sorts=" + this.sorts + ", page=" + this.page + ", size=" + this.size + ")";
        }
    }
    /**
     * Example request without any filter or sorting.
     *
     * {
     *     "filters": [],
     *     "sorts": [],
     *     "page": null,
     *     "size": null
     * }
     */

    /**
     * Example request with a filter by name and sorting by release date in ascending order.
     *
     * {
     *     "filters": [
     *         {
     *             "key": "name",
     *             "operator": "EQUAL",
     *             "field_type": "STRING",
     *             "value": "filterName"
     *         }
     *     ],
     *     "sorts": [
     *         {
     *             "key": "releaseDate",
     *             "direction": "ASC"
     *         }
     *     ],
     *     "page": null,
     *     "size": null
     * }
     */

    /**
     * Example request with a filter where name is not equal to "filterName".
     *
     * {
     *     "filters": [
     *         {
     *             "key": "name",
     *             "operator": "NOT_EQUAL",
     *             "field_type": "STRING",
     *             "value": "filterName"
     *         }
     *     ],
     *     "sorts": [
     *         {
     *             "key": "releaseDate",
     *             "direction": "ASC"
     *         }
     *     ],
     *     "page": null,
     *     "size": null
     * }
     */

    /**
     * Example request with a filter where name is not equal to "filterName" and a response size of 1.
     *
     * {
     *     "filters": [
     *         {
     *             "key": "name",
     *             "operator": "NOT_EQUAL",
     *             "field_type": "STRING",
     *             "value": "filterName"
     *         }
     *     ],
     *     "sorts": [
     *         {
     *             "key": "releaseDate",
     *             "direction": "ASC"
     *         }
     *     ],
     *     "page": null,
     *     "size": 1
     * }
     */

    /**
     * Example request with a filter where name is "Red" and sorting by release date in descending order.
     *
     * {
     *     "filters": [
     *         {
     *             "key": "name",
     *             "operator": "LIKE",
     *             "field_type": "STRING",
     *             "value": "Red"
     *         }
     *     ],
     *     "sorts": [
     *         {
     *             "key": "releaseDate",
     *             "direction": "DESC"
     *         }
     *     ],
     *     "page": null,
     *     "size": null
     * }
     */

    /**
     * Example request with a filter where kernel is in ["5.13", "5.8"].
     *
     * {
     *     "filters": [
     *         {
     *             "key": "kernel",
     *             "operator": "IN",
     *             "field_type": "STRING",
     *             "values": ["5.13", "5.8"]
     *         }
     *     ],
     *     "sorts": [],
     *     "page": null,
     *     "size": null
     * }
     */

    /**
     * Example request with a filter using a between operator on release date.
     *
     * {
     *     "filters": [
     *         {
     *             "key": "releaseDate",
     *             "operator": "BETWEEN",
     *             "field_type": "DATE",
     *             "value": "01-03-2022 00:00:00",
     *             "value_to": "11-03-2022 23:59:59"
     *         }
     *     ],
     *     "sorts": [],
     *     "page": null,
     *     "size": null
     * }
     * Or
     * {
     *     "filters": [
     *         {
     *             "key": "usages",
     *             "operator": "BETWEEN",
     *             "field_type": "INTEGER",
     *             "value": 100,
     *             "value_to": 250
     *         }
     *     ],
     *     "sorts": [],
     *     "page": null,
     *     "size": null
     * }
     */
}