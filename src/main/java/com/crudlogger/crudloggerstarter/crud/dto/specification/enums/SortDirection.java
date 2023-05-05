package com.crudlogger.crudloggerstarter.crud.dto.specification.enums;


import com.crudlogger.crudloggerstarter.crud.dto.specification.request.SortRequest;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

/**
 * @author ogbozoyan
 * @date 01.03.2023
 */
@Slf4j
/*
  This is used when need to sort result query. It can be ascending or descending direction.
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
