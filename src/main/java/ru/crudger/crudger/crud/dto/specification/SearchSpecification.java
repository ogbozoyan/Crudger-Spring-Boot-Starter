package ru.crudger.crudger.crud.dto.specification;

import org.slf4j.Logger;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import ru.crudger.crudger.crud.controller.advice.FilterException;
import ru.crudger.crudger.crud.dto.specification.request.FilterRequest;
import ru.crudger.crudger.crud.dto.specification.request.SearchRequest;
import ru.crudger.crudger.crud.dto.specification.request.SortRequest;

import javax.persistence.criteria.*;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

/**
 * A generic class that implements the Specification interface and constructs the actual query based on the provided constraints.
 *
 * @param <T> The entity type for which the query is being constructed.
 * @author ogbozoyan
 * @date 01.03.2023
 */
public class SearchSpecification<T> implements Specification<T> {
    public static final int DEFAULT_ITEMS_SIZE = 10;
    @Serial
    private static final long serialVersionUID = -9153865343320750644L;
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(SearchSpecification.class);

    /**
     * The search request containing filters and sorts.
     */
    private final transient SearchRequest request;

    public SearchSpecification(SearchRequest request) {
        this.request = request;
    }

    /**
     * Constructs the predicate (criteria) for the query based on the provided filters and sorts.
     *
     * @param root  The root entity in the query.
     * @param query The criteria query being constructed.
     * @param cb    The criteria builder to build predicates and expressions.
     * @return The predicate representing the constructed criteria for the query.
     * @throws FilterException if an error occurs during filtering.
     */
    @Override
    public Predicate toPredicate(@NonNull Root<T> root, @NonNull CriteriaQuery<?> query, CriteriaBuilder cb) {
        try {
            Predicate predicate = cb.equal(cb.literal(Boolean.TRUE), Boolean.TRUE);
            List<Order> orders = new ArrayList<>();
            for (FilterRequest filter : this.request.getFilters()) {
                log.info("Filter: {} {} {}", filter.getKey(), filter.getOperator().toString(), filter.getValue());
                String[] nestedFields = filter.getKey().split("\\.");
                Path<Object> path = root.get(nestedFields[0]);
                for (int i = 1; i < nestedFields.length; i++) {
                    path = path.get(nestedFields[i]);
                }
                predicate = filter.getOperator().build(root, cb, filter, predicate, path);
            }

            for (SortRequest sort : this.request.getSorts()) {
                String[] nestedFields = sort.getKey().split("\\.");
                Path<Object> path = root.get(nestedFields[0]);
                for (int i = 1; i < nestedFields.length; i++) {
                    path = path.get(nestedFields[i]);
                }
                orders.add(sort.getDirection().build(root, cb, sort, path));
            }

            query.orderBy(orders);
            return predicate;
        } catch (Exception e) {
            e.printStackTrace();
            throw new FilterException(e.getClass().getSimpleName() + " Can't filter : " + e.getMessage());
        }
    }

    /**
     * Creates a Pageable object for pagination based on the provided page number and size.
     * Sets default values if the parameters are null or invalid.
     *
     * @param page The page number for pagination (1-based index).
     * @param size The number of items per page.
     * @return A Pageable object representing the pagination configuration.
     */
    public static Pageable getPageable(Integer page, Integer size) {
        size = size == null ? DEFAULT_ITEMS_SIZE : size;
        page = page == null ? 1 : page;
        page = page == 0 ? 1 : page;
        return PageRequest.of(page - 1, size);
    }

}
