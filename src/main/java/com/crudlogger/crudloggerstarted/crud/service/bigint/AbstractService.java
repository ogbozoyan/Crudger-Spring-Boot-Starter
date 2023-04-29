package com.crudlogger.crudloggerstarted.crud.service.bigint;

import com.crudlogger.crudloggerstarted.crud.dto.AbstractResponseDTO;
import com.crudlogger.crudloggerstarted.crud.dto.specification.request.SearchRequest;
import com.crudlogger.crudloggerstarted.crud.model.bigint.AbstractEntity;
import org.springframework.data.domain.Pageable;

public interface AbstractService<T extends AbstractEntity> {
    T save(T entity);

    T update(T entity);

    T delete(Long id);

    T findById(Long id);

    AbstractResponseDTO findAll(Pageable pageable);

    AbstractResponseDTO searchFilter(SearchRequest request);
}