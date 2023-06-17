package ru.crudger.crudger.crud.controller.string;

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
import ru.crudger.crudger.crud.model.entity.AbstractEntityString;
import ru.crudger.crudger.crud.service.string.AbstractServiceString;

public abstract class AbstractControllerStringImpl<E extends AbstractEntityString, S extends AbstractServiceString<E>>
        implements AbstractControllerString<E> {

    protected final AbstractServiceString<E> service;

    public AbstractControllerStringImpl(AbstractServiceString<E> service) {
        this.service = service;
    }

    @Override
    @ToLogger(action = ActionEnum.READ, actionDomain = ActionDomainEnum.CRUD, httpMethod = HttpMethodEnum.GET)
    public ResponseEntity<AbstractResponseDTO> getPage(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size) {
        return ResponseEntity.ok(service.findAll(page, size));
    }

    @Override
    @ToLogger(action = ActionEnum.READ, actionDomain = ActionDomainEnum.CRUD, httpMethod = HttpMethodEnum.GET)
    public ResponseEntity<E> getOne(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Override
    @ToLogger(action = ActionEnum.READ, actionDomain = ActionDomainEnum.CRUD, httpMethod = HttpMethodEnum.POST)
    public ResponseEntity<AbstractResponseDTO> searchFilter(@RequestBody SearchRequest request) {
        return ResponseEntity.ok(service.searchFilter(request));
    }

    @Override
    @ToLogger(action = ActionEnum.UPDATE, actionDomain = ActionDomainEnum.CRUD, httpMethod = HttpMethodEnum.PATCH, returnResponse = true)
    public ResponseEntity<E> update(@RequestBody E update) {
        return ResponseEntity.ok(service.update(update));
    }

    @Override
    @ToLogger(action = ActionEnum.CREATE, actionDomain = ActionDomainEnum.CRUD, httpMethod = HttpMethodEnum.POST, returnResponse = true)
    public ResponseEntity<E> create(@RequestBody E create) {
        return ResponseEntity.ok(service.save(create));
    }

    @Override
    @ToLogger(action = ActionEnum.DELETE, actionDomain = ActionDomainEnum.CRUD, httpMethod = HttpMethodEnum.DELETE, returnResponse = true)
    public ResponseEntity<E> delete(@PathVariable String id) {
        return ResponseEntity.ok(service.delete(id));
    }
}