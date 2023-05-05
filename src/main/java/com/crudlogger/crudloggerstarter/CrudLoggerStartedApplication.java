package com.crudlogger.crudloggerstarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class CrudLoggerStartedApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudLoggerStartedApplication.class, args);
    }

}
