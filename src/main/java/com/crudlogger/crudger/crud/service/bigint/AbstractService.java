package com.crudlogger.crudger.crud.service.bigint;

import com.crudlogger.crudger.crud.dto.AbstractResponseDTO;
import com.crudlogger.crudger.crud.dto.specification.request.SearchRequest;
import com.crudlogger.crudger.crud.model.AbstractEntity;

public interface AbstractService<E extends AbstractEntity> {
    E save(E entity);

    E update(E entity);

    E delete(Long id);

    E findById(Long id);

    AbstractResponseDTO findAll(Integer page, Integer size);

    AbstractResponseDTO searchFilter(SearchRequest request);
}