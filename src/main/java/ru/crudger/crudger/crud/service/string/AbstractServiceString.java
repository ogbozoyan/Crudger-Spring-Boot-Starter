package ru.crudger.crudger.crud.service.string;


import ru.crudger.crudger.crud.dto.AbstractResponseDTO;
import ru.crudger.crudger.crud.dto.specification.request.SearchRequest;
import ru.crudger.crudger.crud.model.entity.AbstractEntityString;

public interface
AbstractServiceString<E extends AbstractEntityString> {
    E save(E entity);

    E update(E entity);

    E delete(String id);

    E findById(String id);

    AbstractResponseDTO findAll(Integer page, Integer size);

    AbstractResponseDTO searchFilter(SearchRequest request);
}