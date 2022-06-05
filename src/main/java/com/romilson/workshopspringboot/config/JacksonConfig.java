package com.romilson.workshopspringboot.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.romilson.workshopspringboot.domain.CardPayment;
import com.romilson.workshopspringboot.domain.TicketPayment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfig {

    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder(){
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder(){
            public void configure(ObjectMapper objectMapper) {
                objectMapper.registerSubtypes(CardPayment.class);
                objectMapper.registerSubtypes(TicketPayment.class);
            }
        };
        return builder;
    }

}
