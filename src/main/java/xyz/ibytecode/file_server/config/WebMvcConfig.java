package xyz.ibytecode.file_server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import xyz.ibytecode.file_server.interceptor.InitInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private String[] excludePathPatterns = new String[]{"/files/**"};
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new InitInterceptor()).addPathPatterns("/**").excludePathPatterns(excludePathPatterns);
    }
}
