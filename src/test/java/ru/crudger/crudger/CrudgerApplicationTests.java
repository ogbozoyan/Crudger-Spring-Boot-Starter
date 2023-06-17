package ru.crudger.crudger;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.runner.WebApplicationContextRunner;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.util.ContentCachingResponseWrapper;
import ru.crudger.crudger.aspect.CachingRequestBodyFilter;
import ru.crudger.crudger.aspect.LoggingAspect;
import ru.crudger.crudger.configuration.GeneralCrudgerAutoconfiguration;

import static org.assertj.core.api.Assertions.*;

@ActiveProfiles("test")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
@ExtendWith(MockitoExtension.class)
class CrudgerApplicationTests {

    @Test
    void contextLoads() {
        new WebApplicationContextRunner()
                .withConfiguration(AutoConfigurations.of(GeneralCrudgerAutoconfiguration.class))
                .run(context -> {
                    assertThat(context).hasNotFailed();
                });
    }

}
