package com.nnk.poseidon.config;

import org.modelmapper.ModelMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * The type Poseidon config.
 *
 * @author Yahia CHERIFI
 */

@Configuration
public class PoseidonConfig {

    /**
     * Mapper model mapper.
     *
     * @return the model mapper
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    /**
     * Create a new instance of RestTemplate.
     * @param builder creates and builds a RestTemplate.
     * @return a new instace of RestTemplate
     */
    @Bean
    public RestTemplate template(final RestTemplateBuilder builder) {
        return builder.build();
    }
}
