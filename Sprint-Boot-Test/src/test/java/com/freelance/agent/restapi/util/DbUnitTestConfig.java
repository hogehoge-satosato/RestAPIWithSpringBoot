package com.freelance.agent.restapi.util;

import org.dbunit.ext.postgresql.PostgresqlDataTypeFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.springtestdbunit.bean.DatabaseConfigBean;

@Configuration
public class DbUnitTestConfig {

    @Bean
    public DatabaseConfigBean databaseConfigBean() {
        DatabaseConfigBean config = new DatabaseConfigBean();
        config.setDatatypeFactory(new PostgresqlDataTypeFactory());
        return config;
    }
}


