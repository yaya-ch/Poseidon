package com.nnk.poseidon.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
}
