package edu.web.countries.configurations;

import edu.web.countries.repositories.TokenRepo;
import edu.web.countries.services.MainInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@RequiredArgsConstructor
public class InterceptConfig implements WebMvcConfigurer {
    private final TokenRepo tokenRepo;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry
                .addInterceptor(new MainInterceptor(this.tokenRepo))
                .addPathPatterns("/user/api-tokens", "/countries");
    }
}
