package com.freelance.agent.restapi.security;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.freelance.agent.restapi.model.UserEntity;

class CustomUserDetailsTest {

    @Test
    void testUserDetailsProperties() {
        UserEntity user = mock(UserEntity.class);
        when(user.getLoginId()).thenReturn("testuser");
        when(user.getPassword()).thenReturn("hashedpass");
        when(user.getRole()).thenReturn("ADMIN");

        CustomUserDetails details = new CustomUserDetails(user);

        assertEquals("testuser", details.getUsername());
        assertEquals("hashedpass", details.getPassword());

        Collection<? extends GrantedAuthority> authorities = details.getAuthorities();
        assertEquals(1, authorities.size());
        assertTrue(authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN")));

        assertTrue(details.isAccountNonExpired());
        assertTrue(details.isAccountNonLocked());
        assertTrue(details.isCredentialsNonExpired());
        assertTrue(details.isEnabled());
    }
}
