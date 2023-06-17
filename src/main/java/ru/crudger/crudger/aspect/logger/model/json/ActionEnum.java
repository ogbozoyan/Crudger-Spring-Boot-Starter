package ru.crudger.crudger.aspect.logger.model.json;

/**
 * <p>
 * The {@code ActionEnum} enum represents different actions that can be associated with a method
 * for logging purposes.
 *
 * <p>The enum provides the following actions:</p>
 * <ul>
 *     <li>{@link #CREATE}: Create action</li>
 *     <li>{@link #READ}: Read action</li>
 *     <li>{@link #UPDATE}: Update action</li>
 *     <li>{@link #DELETE}: Delete action</li>
 *     <li>{@link #SIGN_IN}: Sign-in action</li>
 *     <li>{@link #SIGN_UP}: Sign-up action</li>
 *     <li>{@link #UNDEFINED}: Undefined action</li>
 *     <li>{@link #SIGN_OUT}: Sign-out action</li>
 * </ul>
 *
 * @author ogbozoyan
 * @date 04.04.2023
 */
public enum ActionEnum {
    CREATE,
    READ,
    UPDATE,
    DELETE,
    SIGN_IN,
    SIGN_UP,
    UNDEFINED,
    SIGN_OUT
}
