package com.crudlogger.crudloggerstarted.crud.dto.specification.enums;

import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;

/**
 * @author ogbozoyan
 * @date 01.03.2023
 */

@Slf4j
/**
 *  Define enum of field type which is can be used to parse into data type.
 */
public enum FieldType {

    BOOLEAN {
        public Object parse(String value) {
            return Boolean.valueOf(value);
        }
    },

    DATE {
        public Object parse(String value) {
            Object date = null;
            try {
                date = Timestamp.valueOf(value);
            } catch (Exception e) {
                log.info("Failed parse field type DATE {}", e.getMessage());
            }

            return date;
        }
    },
    DOUBLE {
        public Object parse(String value) {
            return Double.valueOf(value);
        }
    },

    INTEGER {
        public Object parse(String value) {
            return Integer.valueOf(value);
        }
    },

    LONG {
        public Object parse(String value) {
            return Long.valueOf(value);
        }
    },

    STRING {
        public Object parse(String value) {
            return value;
        }
    };

    public abstract Object parse(String value);

}