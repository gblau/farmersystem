package com.gblau;

import com.gblau.common.AppConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
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
     * 资源映射配置
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(AppConstant.FILE_MAP)
                .addResourceLocations("file:" + AppConstant.FILE_PATH);

        registry.addResourceHandler("/images/**")
                .addResourceLocations("classpath:/static/images/");

        registry.addResourceHandler("/javascripts/**")
                .addResourceLocations("classpath:/static/javascripts/");

        registry.addResourceHandler("/js/**")
                .addResourceLocations("classpath:/static/js/");

        registry.addResourceHandler("/stylesheets/**")
                .addResourceLocations("classpath:/static/stylesheets/");

        super.addResourceHandlers(registry);
    }

    /**
     * 重写MVC对静态资源的处理，把静态资源交还给Servlet处理，可以解决所有静态资源无法访问的问题。
     * 相当于<mvc:default-servlet-handler />
     * @param configurer
     */
    /*    @Override
        public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
            configurer.enable();
        }*/
}
