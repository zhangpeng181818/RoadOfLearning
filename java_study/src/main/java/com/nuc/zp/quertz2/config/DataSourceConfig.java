package com.nuc.zp.quertz2.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean(name = "druidDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource primaryDatasource() {
        return DruidDataSourceBuilder.create().build();
    }



}
