package com.gb;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;

/**
 * Created by gblau on 2016-11-10.
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public DataSource dataSource() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/demo?useUnicode=true&amp;charaterEncoding=utf-8&"
                + "zeroDateTimeBehavior=convertToNull");
        dataSource.setUser("root");
        dataSource.setPassword("root");// elecon
        try {
            dataSource.setDriverClass("com.mysql.jdbc.Driver");
        } catch (PropertyVetoException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        dataSource.setAcquireIncrement(5);
        dataSource.setInitialPoolSize(10);
        dataSource.setMinPoolSize(5);
        dataSource.setMaxPoolSize(60);
        dataSource.setMaxIdleTime(120);
        return dataSource;
    }

    @Bean
    public SqlSessionFactoryBean sessionFactory() {
        SqlSessionFactoryBean sFactoryBean = new SqlSessionFactoryBean();
        sFactoryBean.setDataSource(this.dataSource());
        String packageSearchPath = "classpath*:mappers/*.xml";
        Resource[] resources = null;
        try {
            resources = new PathMatchingResourcePatternResolver().getResources(packageSearchPath);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        sFactoryBean.setMapperLocations(resources);
        sFactoryBean.setTypeAliasesPackage("com.gb.model");

        return sFactoryBean;
    }

    @Bean
    public SqlSessionTemplate sessionTemplate() {
        SqlSessionTemplate sst = null;
        try {
            sst = new SqlSessionTemplate(sessionFactory().getObject());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return sst;
    }

    @Bean
    public CharacterEncodingFilter characterEncodingFilter() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        return filter;
    }

}
