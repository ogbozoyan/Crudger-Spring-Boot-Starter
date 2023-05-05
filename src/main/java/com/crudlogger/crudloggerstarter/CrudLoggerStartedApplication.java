package com.crudlogger.crudloggerstarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class CrudLoggerStartedApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudLoggerStartedApplication.class, args);
	}

}
