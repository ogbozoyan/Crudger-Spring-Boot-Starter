package ru.crudger.crudger.aspect.logger.model.json;

/**
 * <p>
 * The {@code ActionDomainEnum} enum represents different action domains that can be associated with a method
 * for logging purposes.
 *
 * <p>The enum provides the following action domains:</p>
 * <ul>
 *     <li>{@link #TEST}: Test domain</li>
 *     <li>{@link #UNDEFINED}: Undefined domain</li>
 *     <li>{@link #CRUD}: CRUD domain</li>
 *     <li>{@link #AUTH}: Authentication domain</li>
 *     <li>{@link #FILE}: File domain</li>
 * </ul>
 *
 * @author ogbozoyan
 * @date 06.04.2023
 */
public enum ActionDomainEnum {
    TEST,
    UNDEFINED,
    CRUD,
    AUTH,
    FILE
}
