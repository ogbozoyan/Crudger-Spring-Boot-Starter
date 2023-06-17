package ru.crudger.crudger.crud.dto.specification.enums;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.logging.Logger;

/**
 * Define enum of field type which is can be used to parse into data type.
 *
 * @author ogbozoyan
 * @date 01.03.2023
 */
public enum FieldType {

    BOOLEAN {
        public Object parse(String value) {
            return Boolean.valueOf(value);
        }
    },

    TIMESTAMP {
        public Object parse(String value) {
            Object date = null;
            try {
                date = Timestamp.valueOf(value);
            } catch (Exception e) {
                Logger.getLogger(FieldType.class.getName()).info("Failed parse field type DATE {} " +  e.getMessage());
            }

            return date;
        }
    },
    DATE {
        public Object parse(String value) {
            Object date = null;
            try {
                date = Date.valueOf(value);
            } catch (Exception e) {
                Logger.getLogger(FieldType.class.getName()).info("Failed parse field type DATE {} " +  e.getMessage());
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