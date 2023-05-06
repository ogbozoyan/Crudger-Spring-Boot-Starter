package com.crudlogger.crudger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableAspectJAutoProxy //AspectJ
@EnableJpaAuditing(auditorAwareRef = "auditorAware") //JPA Audit
public class CrudgerApplication {

    public static void main(String[] args) {
        //TODO for Misha: add abstract Entity,Controller,Service,Repository STR and INT to the project
        //TODO: AutoComponentScan
        SpringApplication.run(CrudgerApplication.class, args);
    }

}
