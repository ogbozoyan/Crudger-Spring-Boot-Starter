package com.crudlogger.crudloggerstarter.crud.service.biginit;

import com.crudlogger.crudloggerstarter.crud.dto.AbstractResponseDTO;
import com.crudlogger.crudloggerstarter.crud.dto.specification.request.SearchRequest;
import com.crudlogger.crudloggerstarter.crud.model.entity.AbstractEntityBigint;

public interface AbstractServiceBigint<E extends AbstractEntityBigint> {
    E save(E entity);

    E update(E entity);

    E delete(Long id);

    E findById(Long id);

    AbstractResponseDTO findAll(Integer page, Integer size);

    AbstractResponseDTO searchFilter(SearchRequest request);
}