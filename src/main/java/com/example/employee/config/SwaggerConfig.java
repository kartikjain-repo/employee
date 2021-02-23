package com.example.employee.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;


/**
* This is the Swagger Configuration Class
* 
* @author Kartik Jain
* 
*/

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket employeeApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.employee.web"))
                .paths(regex("/v1.*"))
                .build()
                .apiInfo(metaData());
    }
    
    /**
     * <p>This is a method that populates the application info that is to be displayed in Swagger-ui
     * </p>
     * @return the API info that is to be displayed in Swagger-ui
     * @see <a href="https://swagger.io/tools/swagger-ui/">Swagger UI Website</a>
     * @since 1.0
     */
    private ApiInfo metaData() {
        return new ApiInfo(
                "Employees Spring Boot Application",
                "APIs to cater to different operations for employee",
                "1.0",
                "Terms of service",
                new Contact("Kartik Jain", "", "kj.kartikjain@gmail.com"),
               "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0");
    }
}
