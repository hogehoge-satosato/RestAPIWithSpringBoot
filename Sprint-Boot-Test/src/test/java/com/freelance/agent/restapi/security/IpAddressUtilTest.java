package com.freelance.agent.restapi.security;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import jakarta.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.Test;

class IpAddressUtilTest {

    @Test
    void testGetClientIp_withXForwardedFor() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getHeader("X-Forwarded-For")).thenReturn("192.168.1.100, proxy1, proxy2");

        String ip = IpAddressUtil.getClientIp(request);
        assertEquals("192.168.1.100", ip); // 最初のIPが返る
    }

    @Test
    void testGetClientIp_withoutXForwardedFor() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getHeader("X-Forwarded-For")).thenReturn(null);
        when(request.getRemoteAddr()).thenReturn("10.0.0.1");

        String ip = IpAddressUtil.getClientIp(request);
        assertEquals("10.0.0.1", ip); // リモートアドレスが返る
    }

    @Test
    void testGetClientIp_withEmptyXForwardedFor() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getHeader("X-Forwarded-For")).thenReturn("");
        when(request.getRemoteAddr()).thenReturn("127.0.0.1");

        String ip = IpAddressUtil.getClientIp(request);
        assertEquals("127.0.0.1", ip); // 空ならリモートアドレス
    }
}
