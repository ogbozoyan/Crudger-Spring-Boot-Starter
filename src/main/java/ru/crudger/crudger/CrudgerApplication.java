package ru.crudger.crudger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy //AspectJ
public class CrudgerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudgerApplication.class, args);
    }

}
