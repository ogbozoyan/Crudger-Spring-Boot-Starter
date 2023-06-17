package ru.crudger.crudger.aspect.logger.model.json;

/**
 * The {@code HttpMethodEnum} enum represents the HTTP methods that can be used in API requests.
 *
 * <p>The enum provides the following HTTP methods:</p>
 * <ul>
 *     <li>{@link #GET}: HTTP GET method</li>
 *     <li>{@link #POST}: HTTP POST method</li>
 *     <li>{@link #PATCH}: HTTP PATCH method</li>
 *     <li>{@link #DELETE}: HTTP DELETE method</li>
 *     <li>{@link #UNDEFINED}: Undefined HTTP method</li>
 * </ul>
 *
 * @author ogbozoyan
 * @date 04.04.2023
 */
public enum HttpMethodEnum {
    GET,
    POST,
    PATCH,
    DELETE,
    UNDEFINED
}
