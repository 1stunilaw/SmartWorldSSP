package com.unilaw.todo.swagger;

import org.springframework.context.annotation.*;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * Конфигурация для Swagger
 */
@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig {

    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.unilaw.todo"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "SmartWorld-SSP test app",
                "DESCIPRION of test app",
                "0.0.1",
                "TERMS OF SERVICE URL",
                new Contact("Alina Golubenko", "https://github.com/1stunilaw", "EMAIL"),
                "LICENSE",
                "LICENSE URL",
                Collections.emptyList()
        );
    }
}
