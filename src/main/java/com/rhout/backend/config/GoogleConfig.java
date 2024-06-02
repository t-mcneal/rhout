package com.rhout.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.google.maps.GeoApiContext;
import javax.annotation.PreDestroy;
import java.util.Properties;

/**
 * Configures a {@link com.google.maps.GeoApiContext} needed to access Google Maps API.
 */
@Configuration
public class GoogleConfig {
    private final GeoApiContext context;

    public GoogleConfig() {
        Properties props = PropertiesReader.readFile("MapApiKey.properties");
        this.context = new GeoApiContext.Builder()
                .apiKey(props.getProperty("GMAP_KEY"))
                .build();
    }

    @Bean
    public GeoApiContext getContext() {
        return  context;
    }

    @PreDestroy
    public void shutdown() {
        context.shutdown();
    }
}
