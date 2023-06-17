package ru.crudger.crudger.aspect;



import ru.crudger.crudger.aspect.logger.model.json.ActionDomainEnum;
import ru.crudger.crudger.aspect.logger.model.json.ActionEnum;
import ru.crudger.crudger.aspect.logger.model.json.HttpMethodEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * The {@code ToLogger} annotation is used to mark methods that should be logged by the {@link LoggingAspect} aspect.
 * It provides attributes to specify the action, action domain, HTTP method, and logging behavior.
 *
 * <p>The annotation can be applied to methods. Best workflow to use annotation on Controller Methods, if you don't
 * use @Transactional on service methods, you can use @ToLogger on service method
 * </p>
 *
 * <p>Example usage:</p>
 *
 * <pre>{@code
 * @RestController
 * @ToLogger(action = ActionEnum.CREATE, actionDomain = ActionDomainEnum.USER, httpMethod = HttpMethodEnum.POST)
 * public void createUser(User user) {
 *     ...
 * }
 * }</pre>
 *
 * @author ogbozoyan
 * @date 07.04.2023
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ToLogger {
    /**
     * Specifies the action associated with the annotated method.
     * Defaults to {@link ActionEnum#UNDEFINED}.
     *
     * @return the action associated with the method
     */
    ActionEnum action() default ActionEnum.UNDEFINED;

    /**
     * Specifies the action domain associated with the annotated method.
     * Defaults to {@link ActionDomainEnum#TEST}.
     *
     * @return the action domain associated with the method
     */
    ActionDomainEnum actionDomain() default ActionDomainEnum.UNDEFINED;

    /**
     * Specifies the HTTP method associated with the annotated method.
     * Defaults to {@link HttpMethodEnum#UNDEFINED}.
     *
     * @return the HTTP method associated with the method
     */
    HttpMethodEnum httpMethod() default HttpMethodEnum.UNDEFINED;

    /**
     * Indicates whether the response should be included in the log.
     * If set to {@code true}, the response will be set in the {@code responseDataAfterChange} field of the log entity.
     * Defaults to {@code false}.
     *
     * @return {@code true} if the response should be included in the log, {@code false} otherwise
     */
    boolean returnResponse() default false;

    /**
     * Indicates whether the log should be saved in the database.
     * If set to {@code true}, the log entity will be saved in the database.
     * If an exception is caught in the method, the log will be saved regardless of this attribute.
     * Defaults to {@code true}.
     *
     * @return {@code true} if the log should be saved in the database, {@code false} otherwise
     */
    boolean isSaveInDataBase() default true;

}
