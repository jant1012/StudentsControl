package com.janchondo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.XADataSourceAutoConfiguration;

@SpringBootApplication
public class SpringAlumnosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAlumnosApplication.class, args);
	}

}
