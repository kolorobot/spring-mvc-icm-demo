package com.github.kolorobot.icm.config;

import com.sun.xml.ws.transport.http.servlet.WSSpringServlet;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(value = "classpath:spring-jaxws-context.xml")
public class JaxWsConfig {
    @Bean
    public ServletRegistrationBean wsSpringServlet() {
        return new ServletRegistrationBean(new WSSpringServlet(), "/api/v10");
    }
}
