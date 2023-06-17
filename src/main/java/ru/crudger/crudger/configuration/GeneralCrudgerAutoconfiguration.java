package ru.crudger.crudger.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.retry.annotation.EnableRetry;
import ru.crudger.crudger.aspect.CachingRequestBodyFilter;
import ru.crudger.crudger.aspect.CachingResponseBodyFilter;
import ru.crudger.crudger.aspect.LoggingAspect;
import ru.crudger.crudger.aspect.logger.model.LogEntity;
import ru.crudger.crudger.aspect.logger.repository.LogEntityRepository;
import ru.crudger.crudger.aspect.logger.service.LogEntityService;

import java.util.List;

/**
 * In @EnableConfigurationProperties need to add all Beans and @Configuration classes
 *
 * @author ogbozoyan
 * @date 29.04.2023
 */
@EnableRetry
@EnableConfigurationProperties({CrudgerProperties.class})
@ComponentScan(basePackages = "ru.crudger.crudger", basePackageClasses = {LoggingAspect.class,
        LogEntity.class,
        LogEntityRepository.class,
        LogEntityService.class
})
@Configuration
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class GeneralCrudgerAutoconfiguration {
    public static final String DELETE_REGEX = "[\n\r\t ]";
    public static final String REGEX_FOR_LOGGER = "[\n\r\t]";

    @Bean(name = "patchingMapper")
    @ConditionalOnMissingBean(ModelMapper.class)
    ModelMapper patchingModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setAmbiguityIgnored(true)
                .setSkipNullEnabled(true)
                .setCollectionsMergeEnabled(false)
                .setPropertyCondition(Conditions.isNotNull());
        return modelMapper;
    }

    @Bean(name = "objectWritter")
    @ConditionalOnMissingBean(ObjectWriter.class)
    ObjectWriter objectWriter() {
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        return mapper.writer().withDefaultPrettyPrinter();
    }

    //Swagger configuration
    @Bean
    @ConditionalOnMissingBean(OpenAPI.class)
    public OpenAPI springDocOpenApi() {
        return new OpenAPI()
                .info(springDocapiInfo())
                .security(List.of(new SecurityRequirement()));

    }

    Info springDocapiInfo() {
        return new Info()
                .title("Crudger")
                .description("Autogenerated API by Crudger Swagger")
                .version("");

    }
}
