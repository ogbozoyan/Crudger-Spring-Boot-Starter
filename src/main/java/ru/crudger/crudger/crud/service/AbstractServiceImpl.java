package ru.crudger.crudger.crud.service;

import com.fasterxml.jackson.databind.ObjectWriter;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import ru.crudger.crudger.configuration.GeneralCrudgerAutoconfiguration;
import ru.crudger.crudger.crud.controller.advice.*;
import ru.crudger.crudger.crud.dto.AbstractResponseDTO;
import ru.crudger.crudger.crud.dto.specification.SearchSpecification;
import ru.crudger.crudger.crud.dto.specification.request.SearchRequest;
import ru.crudger.crudger.crud.model.entity.AbstractEntity;
import ru.crudger.crudger.crud.repository.AbstractRepository;

import java.util.List;

/**
 * An abstract implementation of the AbstractService interface.
 *
 * @param <E> The entity type.
 * @param <R> The repository type.
 * @author ogbozoyan
 * @date 08.02.2023
 */
public abstract class AbstractServiceImpl<E extends AbstractEntity, R extends AbstractRepository<E>> implements AbstractService<E> {

    protected final R repository;
    protected final ModelMapper patchingMapper;
    protected final ObjectWriter objectWriter;

    public AbstractServiceImpl(R repository, ModelMapper patchingMapper, ObjectWriter objectWriter) {
        this.repository = repository;
        this.patchingMapper = patchingMapper;
        this.objectWriter = objectWriter;
    }

    /**
     * Saves the given entity to the database.
     *
     * @param entity The entity to save.
     * @return The saved entity.
     * @throws SaveException if an error occurs during the save operation.
     */
    @Override
    @Transactional
    public E save(E entity) {
        try {
            return repository.save(entity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SaveException(e.getClass().getSimpleName() + " Can't save entity: " + e.getMessage());
        }
    }

    /**
     * Updates the given entity in the database.
     *
     * @param entity The entity to update.
     * @return The updated entity.
     * @throws UpdateException if an error occurs during the update operation.
     */
    @Override
    @Transactional
    public E update(E entity) {

        E entityFromBd = repository.findById(entity.getId()).orElseThrow(() -> new FindException("Entity not found " + entity));
        patchingMapper.map(entity, entityFromBd);
        try {
            return repository.saveAndFlush(entityFromBd);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UpdateException(e.getClass().getSimpleName() + " Can't update entity: " + e.getMessage());
        }
    }

    /**
     * Deletes the entity with the given ID from the database.
     *
     * @param id The ID of the entity to delete.
     * @return The JSON representation of the deleted entity.
     * @throws DeleteException if an error occurs during the delete operation.
     */
    @Override
    @Transactional
    public String delete(Long id) {
        try {
            E entity = repository.findById(id).orElseThrow(() -> new DeleteNotFoundException("Entity not found with id: " + id));
            repository.delete(entity);
            return objectWriter.writeValueAsString(entity).replaceAll(GeneralCrudgerAutoconfiguration.DELETE_REGEX, "");
        } catch (Exception e) {
            e.printStackTrace();
            throw new DeleteException(e.getClass().getSimpleName() + " Can't delete with id: " + id + " " + e.getMessage());
        }
    }

    /**
     * Retrieves the entity with the given ID from the database.
     *
     * @param id The ID of the entity to retrieve.
     * @return The retrieved entity.
     * @throws FindException if the entity is not found.
     */
    @Override
    @Transactional(readOnly = true)
    public E findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new FindException(" Entity not found with id " + id));
    }

    /**
     * Retrieves all entities from the database with pagination support.
     *
     * @param page The page number (0-based index).
     * @param size The number of items per page.
     * @return An AbstractResponseDTO object containing the list of entities and pagination information.
     * @throws PageException if an error occurs during the pagination operation.
     */
    @Override
    @Transactional(readOnly = true)
    public AbstractResponseDTO findAll(Integer page, Integer size) {
        try {
            if (page != null && size != null) {
                PageRequest request = PageRequest.of(page, size);
                Page<E> pageResponse = repository.findAll(request);
                return new AbstractResponseDTO(pageResponse.getContent(), pageResponse.getTotalElements(), pageResponse.getTotalPages());
            } else {
                List<E> fullData = repository.findAll();
                return new AbstractResponseDTO(fullData, (long) fullData.size(), 1);
            }

        } catch (Exception e) {
            e.printStackTrace();

            throw new PageException(e.getClass().getSimpleName() + " Page Exception: " + e.getMessage());
        }
    }

    /**
     * Performs a search query with filters based on the given SearchRequest.
     *
     * @param request The SearchRequest containing the search criteria.
     * @return An AbstractResponseDTO object containing the search results and pagination information.
     * @throws FilterException if an error occurs during the search query execution.
     */
    @Override
    @Transactional(readOnly = true)
    public AbstractResponseDTO searchFilter(SearchRequest request) {
        try {
            SearchSpecification<E> specification = new SearchSpecification<>(request);
            Pageable pageable = SearchSpecification.getPageable(request.getPage(), request.getSize());
            Page<E> page = repository.findAll(specification, pageable);
            return new AbstractResponseDTO(page.getContent(), page.getTotalElements(), page.getTotalPages());
        } catch (Exception e) {
            e.printStackTrace();
            throw new FilterException(e.getClass().getSimpleName() + " Filter exception: " + e.getMessage());
        }
    }
}
