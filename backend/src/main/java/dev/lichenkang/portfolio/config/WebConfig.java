package dev.lichenkang.portfolio.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final ContentStorageProperties storageProperties;

    public WebConfig(ContentStorageProperties storageProperties) {
        this.storageProperties = storageProperties;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins(storageProperties.getAllowedOrigins().toArray(String[]::new))
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                .allowCredentials(false)
                .exposedHeaders("*");
        registry.addMapping("/files/**")
                .allowedOrigins(storageProperties.getAllowedOrigins().toArray(String[]::new))
                .allowedMethods("GET");
    }
}
