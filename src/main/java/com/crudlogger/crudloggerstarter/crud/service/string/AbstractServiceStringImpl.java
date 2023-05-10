package com.crudlogger.crudloggerstarter.crud.service.string;

import com.crudlogger.crudloggerstarter.crud.controller.advice.*;
import com.crudlogger.crudloggerstarter.crud.dto.AbstractResponseDTO;
import com.crudlogger.crudloggerstarter.crud.dto.specification.SearchSpecification;
import com.crudlogger.crudloggerstarter.crud.dto.specification.request.SearchRequest;
import com.crudlogger.crudloggerstarter.crud.model.entity.AbstractEntityString;
import com.crudlogger.crudloggerstarter.crud.repository.string.AbstractRepositoryString;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
public abstract class AbstractServiceStringImpl<E extends AbstractEntityString, R extends AbstractRepositoryString<E>>
        implements AbstractServiceString<E> {
    protected final R repository;

    //@Autowired
    protected ModelMapper patchingMapper;


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

    @Override
    @Transactional
    public E delete(String id) {
        try {
            E entity = repository.findById(id).orElseThrow(() -> new DeleteNotFoundException("Entity not found with id: " + id));
            repository.delete(entity);
            return entity;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DeleteException(e.getClass().getSimpleName() + " Can't delete with id: " + id + " " + e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public E findById(String id) {
        return repository.findById(id).orElseThrow(() -> new FindException(" Entity not found with id " + id));
    }

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
