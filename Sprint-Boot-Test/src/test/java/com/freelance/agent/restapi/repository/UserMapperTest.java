package com.freelance.agent.restapi.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

import com.freelance.agent.restapi.model.UserEntity;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class UserMapperTest {
    @Autowired UserMapper mapper;

    @Test
    void shouldFindUserById() {
        UserEntity user = mapper.findByLoginId("sato");
        assertNotNull(user);
    }
}

