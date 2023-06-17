package ru.crudger.crudger.crud.service;


import ru.crudger.crudger.crud.dto.AbstractResponseDTO;
import ru.crudger.crudger.crud.dto.specification.request.SearchRequest;
import ru.crudger.crudger.crud.model.entity.AbstractEntity;

/**
 * The AbstractService interface defines the contract for service classes that handle CRUD operations on entities.
 * It provides methods for saving, updating, deleting, and retrieving entities, as well as performing search and pagination.
 *
 * @param <E> The type of entity that the service operates on, must extend AbstractEntity.
 * @author ogbozoyan
 * @date 08.02.2023
 */
public interface AbstractService<E extends AbstractEntity> {
    E save(E entity);

    E update(E entity);

    String delete(Long id);

    E findById(Long id);

    AbstractResponseDTO findAll(Integer page, Integer size);

    AbstractResponseDTO searchFilter(SearchRequest request);
}