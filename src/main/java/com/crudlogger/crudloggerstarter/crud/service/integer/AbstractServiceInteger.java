package com.crudlogger.crudloggerstarter.crud.service.integer;

import com.crudlogger.crudloggerstarter.crud.dto.AbstractResponseDTO;
import com.crudlogger.crudloggerstarter.crud.dto.specification.request.SearchRequest;
import com.crudlogger.crudloggerstarter.crud.model.entity.AbstractEntityInteger;

public interface AbstractServiceInteger<E extends AbstractEntityInteger> {
    E save(E entity);

    E update(E entity);

    E delete(Integer id);

    E findById(Integer id);

    AbstractResponseDTO findAll(Integer page, Integer size);

    AbstractResponseDTO searchFilter(SearchRequest request);
}