package ru.crudger.crudger.crud.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.crudger.crudger.aspect.ToLogger;
import ru.crudger.crudger.aspect.logger.model.json.ActionDomainEnum;
import ru.crudger.crudger.aspect.logger.model.json.ActionEnum;
import ru.crudger.crudger.aspect.logger.model.json.HttpMethodEnum;
import ru.crudger.crudger.crud.dto.AbstractResponseDTO;
import ru.crudger.crudger.crud.dto.specification.request.SearchRequest;
import ru.crudger.crudger.crud.model.entity.AbstractEntity;
import ru.crudger.crudger.crud.service.AbstractService;

/**
 * An abstract implementation of the AbstractController interface.
 *
 * @param <E> The entity type.
 * @param <S> The service type.
 * @author ogbozoyan
 * @apiNote This implementation provides basic CRUD operations and utilizes the  @ToLogger aspect as a pointcut to log the method invocations.
 * @date 08.02.2023
 */
public abstract class AbstractControllerImpl<E extends AbstractEntity, S extends AbstractService<E>>
        implements AbstractController<E> {

    protected final AbstractService<E> service;

    public AbstractControllerImpl(AbstractService<E> service) {
        this.service = service;
    }

    /**
     * Retrieves a page of entities.
     *
     * @param page The page number.
     * @param size The page size.
     * @return The ResponseEntity containing the page of entities.
     */
    @Override
    @ToLogger(action = ActionEnum.READ, actionDomain = ActionDomainEnum.CRUD, httpMethod = HttpMethodEnum.GET, isSaveInDataBase = false)
    public ResponseEntity<AbstractResponseDTO> getPage(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size) {
        return ResponseEntity.ok(service.findAll(page, size));
    }

    /**
     * Retrieves a single entity by ID.
     *
     * @param id The ID of the entity.
     * @return The ResponseEntity containing the entity.
     */
    @Override
    @ToLogger(action = ActionEnum.READ, actionDomain = ActionDomainEnum.CRUD, httpMethod = HttpMethodEnum.GET)
    public ResponseEntity<E> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    /**
     * Performs a search and filtering operation on entities.
     *
     * @param request The SearchRequest object containing the search criteria.
     * @return The ResponseEntity containing the search results.
     */
    @Override
    @ToLogger(action = ActionEnum.READ, actionDomain = ActionDomainEnum.CRUD, httpMethod = HttpMethodEnum.POST)
    public ResponseEntity<AbstractResponseDTO> searchFilter(@RequestBody SearchRequest request) {
        return ResponseEntity.ok(service.searchFilter(request));
    }

    /**
     * Updates an existing entity.
     *
     * @param update The updated entity object.
     * @return The ResponseEntity containing the updated entity.
     */
    @Override
    @ToLogger(action = ActionEnum.UPDATE, actionDomain = ActionDomainEnum.CRUD, httpMethod = HttpMethodEnum.PATCH, returnResponse = true)
    public ResponseEntity<E> update(@RequestBody E update) {
        return ResponseEntity.ok(service.update(update));
    }

    /**
     * Creates a new entity.
     *
     * @param create The entity to be created.
     * @return The ResponseEntity containing the created entity.
     */
    @Override
    @ToLogger(action = ActionEnum.CREATE, actionDomain = ActionDomainEnum.CRUD, httpMethod = HttpMethodEnum.POST, returnResponse = true)
    public ResponseEntity<E> create(@RequestBody E create) {
        return ResponseEntity.ok(service.save(create));
    }

    /**
     * Deletes an entity by ID.
     *
     * @param id The ID of the entity to be deleted.
     * @return The ResponseEntity containing the deleted entity.
     */
    @Override
    @ToLogger(action = ActionEnum.DELETE, actionDomain = ActionDomainEnum.CRUD, httpMethod = HttpMethodEnum.DELETE, returnResponse = true)
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }
}