package com.crudlogger.crudloggerstarter.crud.service.bigint;

import com.crudlogger.crudloggerstarter.crud.dto.AbstractResponseDTO;
import com.crudlogger.crudloggerstarter.crud.dto.specification.request.SearchRequest;
import com.crudlogger.crudloggerstarter.crud.model.AbstractEntity;

public interface AbstractService<T extends AbstractEntity> {
    T save(T entity);

    T update(T entity);

    T delete(Long id);

    T findById(Long id);

    AbstractResponseDTO findAll(Integer page, Integer size);

    AbstractResponseDTO searchFilter(SearchRequest request);
}