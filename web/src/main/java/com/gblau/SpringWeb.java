package com.gblau;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Properties;

/**
 * @author gblau
 * @date 2016-11-14
 */
@Configuration
public class SpringWeb extends WebMvcConfigurerAdapter {
    @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setSuffix(".html");
        return resolver;
    }

    @Bean(name="simpleMappingExceptionResolver")
    public SimpleMappingExceptionResolver createSimpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver r = new SimpleMappingExceptionResolver();

        Properties mappings = new Properties();
        //mappings.setProperty("DatabaseException", "login");
        mappings.setProperty("AuthorizationException", "login");

        r.setExceptionMappings(mappings);  // 默认为空
        //r.setDefaultErrorView("login");    // 默认没有
        r.setExceptionAttribute("ex");
        r.setWarnLogCategory("example.MvcLogger");
        return r;
    }

    /**
     * 重写MVC对静态资源的处理，把静态资源交还给Servlet处理，可以解决所有静态资源无法访问的问题。
     * 相当于<mvc:default-servlet-handler />
     * @param configurer
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
