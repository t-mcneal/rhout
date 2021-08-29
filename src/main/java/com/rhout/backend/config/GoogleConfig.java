package com.rhout.backend.config;

import com.google.maps.GeoApiContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Properties;


@Configuration
public class GoogleConfig {

    @Bean
    public GeoApiContext getContext() {
        Properties props = PropertiesReader.readFile("apikey.properties");
        return new GeoApiContext.Builder()
                .apiKey(props.getProperty("GMAP_KEY"))
                .build();
    }
}
