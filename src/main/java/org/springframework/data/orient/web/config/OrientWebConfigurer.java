package org.springframework.data.orient.web.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.orient.web.OrientSourceHandlerArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class OrientWebConfigurer extends WebMvcConfigurerAdapter {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new OrientSourceHandlerArgumentResolver());
    }
}
