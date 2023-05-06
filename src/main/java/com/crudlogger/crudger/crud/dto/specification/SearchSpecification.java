package com.crudlogger.crudger.crud.dto.specification;

import com.crudlogger.crudger.crud.dto.specification.request.FilterRequest;
import com.crudlogger.crudger.crud.dto.specification.request.SearchRequest;
import com.crudlogger.crudger.crud.dto.specification.request.SortRequest;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ogbozoyan
 * @date 01.03.2023
 * Generic class that implements the Specification interface and going to
 * pass in our own constraint to construct actual query
 */
@Slf4j
@AllArgsConstructor
public class SearchSpecification<T> implements Specification<T> {
    public static final int DEFAULT_ITEMS_SIZE = 10;
    @Serial
    private static final long serialVersionUID = -9153865343320750644L;

    private final transient SearchRequest request;

    @Override
    public Predicate toPredicate(@NonNull Root<T> root, @NonNull CriteriaQuery<?> query, CriteriaBuilder cb) {
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
    }

    public static Pageable getPageable(Integer page, Integer size) {
        size = size == null ? DEFAULT_ITEMS_SIZE : size;
        page = page == null ? 1 : page;
        page = page == 0 ? 1 : page;
        return PageRequest.of(page - 1, size);
    }
}
