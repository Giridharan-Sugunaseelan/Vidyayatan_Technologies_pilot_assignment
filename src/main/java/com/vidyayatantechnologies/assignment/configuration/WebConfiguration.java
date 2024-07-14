package com.vidyayatantechnologies.assignment.configuration;

import com.vidyayatantechnologies.assignment.interceptors.PermissionsInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@AllArgsConstructor
public class WebConfiguration implements WebMvcConfigurer {

    private final PermissionsInterceptor permissionsInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(permissionsInterceptor).addPathPatterns("/books/**", "/permission/**", "/role/**");
    }
}
