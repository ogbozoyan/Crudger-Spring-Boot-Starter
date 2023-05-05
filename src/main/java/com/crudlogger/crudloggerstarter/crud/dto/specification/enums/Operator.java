package com.crudlogger.crudloggerstarter.crud.dto.specification.enums;

import com.crudlogger.crudloggerstarter.crud.dto.specification.request.FilterRequest;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.criteria.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author ogbozoyan
 * @date 01.03.2023
 * This is a logical for predicate of Criteria API likes EQUAL, NOT_EQUAL, LIKE, IN, and BETWEEN.
 */
@Slf4j
public enum Operator {
    /**
     * EQUAL  >	SELECT * FROM table WHERE field = ?
     */
    EQUAL { //String Long
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate, Path<Object> path) {
            Object value = request.getFieldType().parse(request.getValue().toString());
            return cb.and(cb.equal(path.as(value.getClass()), value), predicate);
        }
    },
    /**
     * NOT_EQUAL >	SELECT * FROM table WHERE field != ?
     */
    NOT_EQUAL {
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate, Path<Object> path) {
            Object value = request.getFieldType().parse(request.getValue().toString());
            return cb.and(cb.notEqual(path.as(value.getClass()), value), predicate);
        }
    },
    /**
     * LIKE  >	SELECT * FROM table WHERE field LIKE '%?%'
     */
    LIKE { //String
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate, Path<Object> path) {
            return cb.and(cb.like(cb.lower(path.as(String.class)), "%" + request.getValue().toString().toLowerCase() + "%"), predicate);
        }
    },
    /**
     * IN	> SELECT * FROM table WHERE field IN (?)
     */
    IN { //String Long Data
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate, Path<Object> path) {
            List<Object> values = request.getValues();
            CriteriaBuilder.In<Object> inClause = cb.in(path.as(values.get(0).getClass()));
            for (Object value : values) {
                inClause.value(request.getFieldType().parse(value.toString()));
            }
            return cb.and(inClause, predicate);
        }
    },
    /**
     * BETWEEN	> SELECT * FROM table WHERE field >= ? AND field <= ?
     */
    BETWEEN { //Data
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate, Path<Object> path) {
            Object value = request.getFieldType().parse(request.getValue().toString());
            Object valueTo = request.getFieldType().parse(request.getValueTo().toString());
            if (request.getFieldType() == FieldType.DATE) {
                Timestamp startDate = (Timestamp) value;
                Timestamp endDate = (Timestamp) valueTo;
                Expression<Timestamp> key = root.get(request.getKey());
                return cb.and(cb.and(cb.greaterThanOrEqualTo(key, startDate), cb.lessThanOrEqualTo(key, endDate)), predicate);
            }

            if (request.getFieldType() != FieldType.BOOLEAN) {
                Number start = (Number) request.getValue();
                Number end = (Number) request.getValueTo();
                Expression<Number> key = root.get(request.getKey());
                return cb.and(cb.and(cb.ge(key, start), cb.le(key, end)), predicate);
            }

            log.info("Can not use between for {} field type.", request.getFieldType());
            return predicate;
        }
    };

    public abstract <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate, Path<Object> path);


}