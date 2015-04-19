package com.github.kolorobot.icm.config;

import com.github.kolorobot.icm.webservice.JerseyIncidentManagamentService;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.ApplicationPath;

@Configuration
@ApplicationPath("/api/v20")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(JerseyIncidentManagamentService.class);
    }
}
