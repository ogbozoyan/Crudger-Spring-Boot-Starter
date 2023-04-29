package com.crudlogger.crudloggerstarted.crud.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ogbozoyan
 * @date 13.02.2023
 */

@Data
public class AbstractResponseDTO implements Serializable {
    private Object content;
    private Long totalElements;
    private Integer totalPages;

    public AbstractResponseDTO(Object content, Long totalElements, Integer totalPages) {
        this.content = content;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }
}
