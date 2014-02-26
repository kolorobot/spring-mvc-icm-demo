package com.github.kolorobot.icm.config;

import org.springframework.context.annotation.*;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(basePackages = { "com.github.kolorobot.icm" }, excludeFilters = @ComponentScan.Filter({Controller.class, Configuration.class}))
public class RootConfig {
	
	@Bean
	public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
		return new PropertyPlaceholderConfigurer();
	}
	
}