package com.freelance.agent.restapi.security;

import static org.mockito.Mockito.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.Test;
import org.springframework.security.access.AccessDeniedException;

class CustomAccessDeniedHandlerTest {

    @Test
    void testHandleAccessDenied() throws Exception {
        // モックの準備
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        AccessDeniedException exception = new AccessDeniedException("権限がありません");

        when(request.getMethod()).thenReturn("PUT");
        when(request.getRequestURI()).thenReturn("/secure/resource");
        when(request.getHeader("X-Forwarded-For")).thenReturn("192.168.1.10");

        CustomAccessDeniedHandler handler = new CustomAccessDeniedHandler();

        // 実行
        handler.handle(request, response, exception);

        // sendError が正しく呼ばれたか検証
        verify(response).sendError(HttpServletResponse.SC_FORBIDDEN, "アクセスが拒否されました");
    }
}
