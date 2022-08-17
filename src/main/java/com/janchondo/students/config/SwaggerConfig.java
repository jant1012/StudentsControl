package com.janchondo.students.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
<<<<<<< HEAD
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
=======
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
>>>>>>> 57f7174554d13f04c53cce0979ba23f9455952e1
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

<<<<<<< HEAD
=======

>>>>>>> 57f7174554d13f04c53cce0979ba23f9455952e1
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.janchondo.students.controller"))
                .paths(PathSelectors.any())
<<<<<<< HEAD
                .build()
                .apiInfo(apiInfo());
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger with Student Controller")
                .description("Spring project using Swagger to control the endpoints")
                .version("1.0")
                .contact(new Contact("Jesus Anchondo","https://www.janchondo.com",""))
=======
>>>>>>> 57f7174554d13f04c53cce0979ba23f9455952e1
                .build();
    }
}
