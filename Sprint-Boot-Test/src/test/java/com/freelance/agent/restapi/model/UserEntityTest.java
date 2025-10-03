package com.freelance.agent.restapi.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class UserEntityTest {
    @Test
    void shouldTouchUserEntityDirectly() {
        UserEntity user = new UserEntity();
        user.setLoginId("テスト");
        user.setPassword("TESTpass");
        user.setRole("test");

        assertEquals("テスト", user.getLoginId());
        assertEquals("TESTpass", user.getPassword());
        assertEquals("test", user.getRole());
    }
}
