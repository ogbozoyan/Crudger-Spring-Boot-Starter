package com.crudlogger.crudger.aspect.logger.model.json;

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
/*
    * DELETE SAVE UPDATE methods
*/
public class ResponseDataAfterChange implements Serializable {
    private String body;
}
