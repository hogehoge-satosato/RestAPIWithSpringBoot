package com.freelance.agent.restapi.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.freelance.agent.restapi.model.UserEntity;
import com.freelance.agent.restapi.util.CsvDataSetLoader;
import com.freelance.agent.restapi.util.DbUnitTestConfig;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

@SpringBootTest
@ActiveProfiles("test")
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
@Import(DbUnitTestConfig.class)
@TestExecutionListeners({
    DependencyInjectionTestExecutionListener.class,
    TransactionalTestExecutionListener.class,
    DbUnitTestExecutionListener.class
})
class UserMapperTest {
    @Autowired
    UserMapper mapper;

    @Test
    @DatabaseSetup("/dataset/repository/product/case1/")
    void shouldFindUserById() {
        UserEntity user = mapper.findByLoginId("sato");
        assertNotNull(user);
    }
}

