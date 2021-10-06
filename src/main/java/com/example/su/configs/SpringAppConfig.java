package com.example.su.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringAppConfig{

    @Bean(name  = "modelMapper")
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
