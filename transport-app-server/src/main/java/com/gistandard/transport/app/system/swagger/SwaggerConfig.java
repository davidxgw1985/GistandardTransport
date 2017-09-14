package com.gistandard.transport.app.system.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableWebMvc
@EnableSwagger2
@ComponentScan(basePackages = {"com.gistandard.transport.app.module"})
public class SwaggerConfig extends WebMvcConfigurationSupport {

    /**
     * Every Docket bean is picked up by the swagger-mvc framework - allowing for multiple
     * swagger groups i.e. same code base multiple swagger resource listings.
     */

    @Bean
    public Docket customDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                 .select()
                .apis(RequestHandlerSelectors.basePackage("com.gistandard.transport.app.module"))
                .paths(PathSelectors.any())
                .build();
    }


}