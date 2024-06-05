package com.example.springframework32730;

import jakarta.annotation.PostConstruct;
import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@ApplicationPath("/")
@Component
public class TestApplication extends ResourceConfig {
    @PostConstruct
    void register() {
        register(TestResource.class);
    }
}
