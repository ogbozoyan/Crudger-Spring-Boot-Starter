package com.crudlogger.crudloggerstarter.crud.controller.Abstract.Long;

import com.crudlogger.crudloggerstarter.aspect.ToLogger;
import com.crudlogger.crudloggerstarter.aspect.logger.model.json.ActionDomainEnum;
import com.crudlogger.crudloggerstarter.aspect.logger.model.json.ActionEnum;
import com.crudlogger.crudloggerstarter.aspect.logger.model.json.HttpMethodEnum;
import com.crudlogger.crudloggerstarter.crud.dto.AbstractResponseDTO;
import com.crudlogger.crudloggerstarter.crud.dto.specification.request.SearchRequest;
import com.crudlogger.crudloggerstarter.crud.model.entity.AbstractEntityLong;
import com.crudlogger.crudloggerstarter.crud.service.Long.AbstractServiceLong;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
public abstract class AbstractControllerLongImpl<E extends AbstractEntityLong, S extends AbstractServiceLong<E>>
        implements AbstractControllerLong<E> {

    protected final AbstractServiceLong<E> service;

    @Override
    @ToLogger(action = ActionEnum.READ, actionDomain = ActionDomainEnum.CRUD, httpMethod = HttpMethodEnum.GET)
    public ResponseEntity<AbstractResponseDTO> getPage(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size) {
        return ResponseEntity.ok(service.findAll(page,size));
    }

    @Override
    @ToLogger(action = ActionEnum.READ, actionDomain = ActionDomainEnum.CRUD, httpMethod = HttpMethodEnum.GET)
    public ResponseEntity<E> getOne(@PathVariable Long id) {
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
    public ResponseEntity<E> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }
}