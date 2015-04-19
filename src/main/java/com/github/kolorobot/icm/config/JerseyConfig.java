package com.github.kolorobot.icm.config;

import com.github.kolorobot.icm.api.JerseyApi20;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.ApplicationPath;

@Configuration
@ApplicationPath("/api/v20")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(JerseyApi20.class);
    }
}
