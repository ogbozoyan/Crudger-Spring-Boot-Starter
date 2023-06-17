package ru.crudger.crudger.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class to resolve in properties "crudger"
 * @author ogbozoyan
 * @date 16.06.2023
 */
@Configuration
@ConfigurationProperties(prefix = "crudger")
public class CrudgerProperties {
//    TODO: ...

}
