package com.freelance.agent.restapi.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.freelance.agent.restapi.model.UserEntity;
import com.freelance.agent.restapi.repository.UserMapper;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailsServiceTest {

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private CustomUserDetailsService userDetailsService;

    @Test
    void testLoadUserByUsername_found() {
        UserEntity user = new UserEntity();
        user.setLoginId("testuser");
        user.setPassword("hashedpass");
        user.setRole("USER");

        when(userMapper.findByLoginId("testuser")).thenReturn(user);

        UserDetails details = userDetailsService.loadUserByUsername("testuser");

        assertEquals("testuser", details.getUsername());
        assertEquals("hashedpass", details.getPassword());
        assertTrue(details.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_USER")));
    }

    @Test
    void testLoadUserByUsername_notFound() {
        when(userMapper.findByLoginId("unknown")).thenReturn(null);

        UsernameNotFoundException ex = assertThrows(
            UsernameNotFoundException.class,
            () -> userDetailsService.loadUserByUsername("unknown")
        );

        assertEquals("User not found: unknown", ex.getMessage());
    }
}
