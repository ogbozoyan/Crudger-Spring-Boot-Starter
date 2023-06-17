package ru.crudger.crudger.crud.model.audit;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;


/**
 * The service implementation responsible for retrieving the user information.
 * Example of implementation audit entity
 *
 * <pre>{@code
 *      @SpringBootApplication
 *      @EnableJpaAuditing(auditorAwareRef = "auditorAware")
 *      public class CrudLoggerStartedApplication {
 *
 *          public static void main(String[] args) {
 *
 *              SpringApplication.run(CrudLoggerStartedApplication.class, args);
 *     }
 *
 * }
 *
 *  @Component("auditAware")
 * public class AbstractAuditAwareImpl extends AbstractAuditAware {
 *      * Constructs an instance of AbstractAuditAware with the provided userServiceImpl.
 *      *
 *      * @param userServiceImpl The implementation of the AbstractUserService.
 *     protected AbstractAuditAwareImpl(AbstractUserService userServiceImpl) {
 *         super(userServiceImpl);
 *     }
 *  * }</pre>
 *
 * @author ogbozoyan
 * @date 06.05.2023
 */
public abstract class AbstractAuditAware implements AuditorAware<String> {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(AbstractAuditAware.class);
    /**
     * You need to provide an implementation of this service in your project to ensure the correct audit workflow.
     */
    @Qualifier("abstractUserServiceImpl")
    protected final AbstractUserService userServiceImpl;

    /**
     * Constructs an instance of AbstractAuditAware with the provided userServiceImpl.
     *
     * @param userServiceImpl The implementation of the AbstractUserService.
     */
    protected AbstractAuditAware(AbstractUserService userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    /**
     * Retrieves the current auditor, which is the user login.
     *
     * @return An Optional containing the user login if available, or an empty Optional otherwise.
     */
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.ofNullable(userServiceImpl.getUserLogin());
    }
}


