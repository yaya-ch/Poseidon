package com.nnk.poseidon.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collection;

import static springfox.documentation.builders.PathSelectors.any;


/**
 * The type Swagger config.
 * provides some configuration rules for the Poseidon api documentation
 * (config for swagger)
 *
 * @author Yahia CHERIFI
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * Docket docket.
     *
     * @return the docket
     */
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors
                        .basePackage("com.nnk.poseidon.controllers.api"))
                .paths(any()).build().apiInfo(apiDocumentationData());
    }

    /**
     * Provides some information about the api.
     * @return a new instance of ApiInfo
     */
    private ApiInfo apiDocumentationData() {
        Collection<VendorExtension> vendorExtensions = new ArrayList<>();
        return new ApiInfo("Spring Boot Rest Api",
                "Spring Boot Rest Api for the Poseidon Application",
                "1.0.0", "https://swagger.io/specification/",
                new Contact("Yahia CHERIFI",
                        "https://yaya-ch.github.io/3-page-web/",
                        "yahia@cherifi.fake-email.com"),
                "Apache Licence Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0",
                vendorExtensions);
    }
}
