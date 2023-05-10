package com.crudlogger.crudloggerstarter.crud.service.Long;

import com.crudlogger.crudloggerstarter.crud.dto.AbstractResponseDTO;
import com.crudlogger.crudloggerstarter.crud.dto.specification.request.SearchRequest;
import com.crudlogger.crudloggerstarter.crud.model.entity.AbstractEntityLong;

public interface AbstractServiceLong<E extends AbstractEntityLong> {
    E save(E entity);

    E update(E entity);

    E delete(Long id);

    E findById(Long id);

    AbstractResponseDTO findAll(Integer page, Integer size);

    AbstractResponseDTO searchFilter(SearchRequest request);
}