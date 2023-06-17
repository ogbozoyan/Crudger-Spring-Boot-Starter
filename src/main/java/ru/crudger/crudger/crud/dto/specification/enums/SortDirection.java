package ru.crudger.crudger.crud.dto.specification.enums;


import ru.crudger.crudger.crud.dto.specification.request.SortRequest;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

/**
 * This is used when need to sort result query. It can be ascending or descending direction.
 *
 * @author ogbozoyan
 * @date 01.03.2023
 */
public enum SortDirection {
    /**
     * ASC	SELECT * FROM table ORDER BY field ASC
     */
    ASC {
        public <T> Order build(Root<T> root, CriteriaBuilder cb, SortRequest request, Path<Object> path) {
            return cb.asc(path);
        }
    },
    /**
     * DESC	SELECT * FROM table ORDER BY field DESC
     */
    DESC {
        public <T> Order build(Root<T> root, CriteriaBuilder cb, SortRequest request, Path<Object> path) {
            return cb.desc(path);
        }
    };

    public abstract <T> Order build(Root<T> root, CriteriaBuilder cb, SortRequest request, Path<Object> path);

}
