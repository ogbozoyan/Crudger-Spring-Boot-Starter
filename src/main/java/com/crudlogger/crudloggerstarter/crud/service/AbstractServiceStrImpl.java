package com.crudlogger.crudloggerstarter.crud.service;

import com.crudlogger.crudloggerstarter.crud.controller.advice.*;
import com.crudlogger.crudloggerstarter.crud.dto.AbstractResponseDTO;
import com.crudlogger.crudloggerstarter.crud.dto.specification.SearchSpecification;
import com.crudlogger.crudloggerstarter.crud.dto.specification.request.SearchRequest;
import com.crudlogger.crudloggerstarter.crud.model.str.AbstractEntityStr;
import com.crudlogger.crudloggerstarter.crud.repository.AbstractRepositoryStr;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ogbozoyan
 * @date 18.04.2023
 */
@RequiredArgsConstructor
public class AbstractServiceStrImpl<T extends AbstractEntityStr, R extends AbstractRepositoryStr<T>> implements AbstractServiceStr<T> {
    private final R repository;

    @Autowired
    private ModelMapper patchingMapper;

    @Override
    @Transactional
    public T save(T entity) {
        try {
            return repository.save(entity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SaveException(e.getClass().getSimpleName() + " Can't save entity: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public T update(T entity) {

        T entityFromBd = repository.findById(entity.getId()).orElseThrow(() -> new FindException("Entity not found " + entity));
        patchingMapper.map(entity, entityFromBd);
        try {
            return repository.saveAndFlush(entityFromBd);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UpdateException(e.getClass().getSimpleName() + " Can't update entity: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public T delete(String id) {
        try {
            T entity = repository.findById(id).orElseThrow(() -> new DeleteNotFoundException("Entity not found with id: " + id));
            repository.delete(entity);
            return entity;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DeleteException(e.getClass().getSimpleName() + " Can't delete with id: " + id + " " + e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public T findById(String id) {
        return repository.findById(id).orElseThrow(() -> new FindException(" Entity not found with id " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public AbstractResponseDTO findAll(Pageable pageable) {
        try {
            Page<T> page = repository.findAll(pageable);
            return new AbstractResponseDTO(page.getContent(), page.getTotalElements(), page.getTotalPages());
        } catch (Exception e) {
            e.printStackTrace();

            throw new PageException(e.getClass().getSimpleName() + " Page Exception: " + e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public AbstractResponseDTO searchFilter(SearchRequest request) {
        try {
            SearchSpecification<T> specification = new SearchSpecification<>(request);
            Pageable pageable = SearchSpecification.getPageable(request.getPage(), request.getSize());
            Page<T> page = repository.findAll(specification, pageable);
            return new AbstractResponseDTO(page.getContent(), page.getTotalElements(), page.getTotalPages());
        } catch (Exception e) {
            e.printStackTrace();
            throw new FilterException(e.getClass().getSimpleName() + " Filter exception: " + e.getMessage());
        }
    }
}
