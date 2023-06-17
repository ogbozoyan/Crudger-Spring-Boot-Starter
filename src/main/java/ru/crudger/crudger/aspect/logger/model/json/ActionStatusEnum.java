package ru.crudger.crudger.aspect.logger.model.json;

/**
 * <p>
 * The {@code ActionStatusEnum} enum represents different action statuses that can be associated with a method
 * for logging purposes.
 *
 * <p>The enum provides the following action statuses:</p>
 * <ul>
 *     <li>{@link #SUCCESSFULLY}: Action completed successfully</li>
 *     <li>{@link #FAILED}: Action failed</li>
 *     <li>{@link #ERROR}: Action encountered an error</li>
 *     <li>{@link #UNKNOWN}: Unknown action status</li>
 * </ul>
 *
 * @author ogbozoyan
 * @date 11.04.2023
 */
public enum ActionStatusEnum {
    SUCCESSFULLY,
    FAILED,
    ERROR,
    UNKNOWN
}
