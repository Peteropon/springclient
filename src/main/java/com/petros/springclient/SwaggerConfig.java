package com.petros.springclient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.petros.springclient.ClientController.URL;


@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors
                        .basePackage(URL))
                .paths(PathSelectors.regex("/.*"))
                .build().apiInfo(apiEndPointsInfo());

    }

    private ApiInfo apiEndPointsInfo() {

        return new ApiInfoBuilder().title("Spring Boot REST client")
                .description("Client")
                .contact(new Contact("Petros Tsavdaridis", "www.ithsdistans.se", "petros.tsavdaridis@iths.se"))
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .version("1.0.0")
                .build();

    }
}
