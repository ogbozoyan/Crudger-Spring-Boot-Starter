package com.crudlogger.crudloggerstarter.crud.service.string;

import com.crudlogger.crudloggerstarter.crud.dto.AbstractResponseDTO;
import com.crudlogger.crudloggerstarter.crud.dto.specification.request.SearchRequest;
import com.crudlogger.crudloggerstarter.crud.model.entity.AbstractEntityString;

public interface
AbstractServiceString<E extends AbstractEntityString> {
    E save(E entity);

    E update(E entity);

    E delete(String id);

    E findById(String id);

    AbstractResponseDTO findAll(Integer page, Integer size);

    AbstractResponseDTO searchFilter(SearchRequest request);
}