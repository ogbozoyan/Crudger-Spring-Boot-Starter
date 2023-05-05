package com.crudlogger.crudloggerstarter.aspect.logger.model.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ogbozoyan
 * @date 17.03.2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Params implements Serializable {
        private String key;
        private String[] value;
}
