package com.project.LibraryMgmt.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {  // 👈 Rename this class!
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
