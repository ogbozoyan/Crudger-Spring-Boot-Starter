package com.crudlogger.crudloggerstarter.aspect.logger.model.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author ogbozoyan
 * @date 17.03.2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestDataChange implements Serializable {
    private List<Params> params;
    private String body;
}