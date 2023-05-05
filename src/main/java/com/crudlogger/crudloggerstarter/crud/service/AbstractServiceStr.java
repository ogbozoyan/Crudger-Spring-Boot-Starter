package com.crudlogger.crudloggerstarter.crud.service;

import com.crudlogger.crudloggerstarter.crud.dto.AbstractResponseDTO;
import com.crudlogger.crudloggerstarter.crud.dto.specification.request.SearchRequest;
import com.crudlogger.crudloggerstarter.crud.model.str.AbstractEntityStr;
import org.springframework.data.domain.Pageable;

/**
 * @author ogbozoyan
 * @date 18.04.2023
 */
public interface AbstractServiceStr<T extends AbstractEntityStr> {
    T save(T entity);

    T update(T entity);


    T delete(String id);

    T findById(String id);

    AbstractResponseDTO findAll(Pageable pageable);

    AbstractResponseDTO searchFilter(SearchRequest request);
}
