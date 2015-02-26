package com.github.kolorobot.icm.config;

import com.github.kolorobot.icm.account.User;
import com.github.kolorobot.icm.support.thymeleaf.ThymeleafLayoutInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

import java.util.List;
import java.util.Locale;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Bean
    public LocaleResolver localeResolver() {
        return new FixedLocaleResolver(new Locale("pl", "PL"));
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ThymeleafLayoutInterceptor());
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new UserHandlerMethodArgumentResolver());
    }

    private static class UserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

        public boolean supportsParameter(MethodParameter parameter) {
            return User.class.isAssignableFrom(parameter.getParameterType());
        }

        public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
            Authentication auth = (Authentication) webRequest.getUserPrincipal();
            return auth != null && auth.getPrincipal() instanceof User ? auth.getPrincipal() : null;
        }
    }
}
