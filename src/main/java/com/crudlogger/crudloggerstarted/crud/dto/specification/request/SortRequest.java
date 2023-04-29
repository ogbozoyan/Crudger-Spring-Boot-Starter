package com.crudlogger.crudloggerstarted.crud.dto.specification.request;

import com.crudlogger.crudloggerstarted.crud.dto.specification.enums.SortDirection;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author ogbozoyan
 * @date 01.03.2023
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
/**
 * A data contract for sorting request there should be a key and direction.
 */
public class SortRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 3194362295851723069L;

    private String key;

    private SortDirection direction;

}