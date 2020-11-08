package com.holidu.interview.assignment.configuration;

import com.socrata.api.Soda2Consumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SodaConfiguration {

    @Bean
    public Soda2Consumer soda2Consumer(@Value("${integration.soda2.url}") String soda2Url){
        return Soda2Consumer.newConsumer(soda2Url);
    }
}
