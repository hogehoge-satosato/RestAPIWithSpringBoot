package com.freelance.agent.restapi.security;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

/** .
 * 認可失敗時（403 Forbidden）に呼び出されるカスタムハンドラー。
 * <p>
 * ユーザーが認証済みであっても、アクセス権限が不足している場合にこのハンドラーが実行されます。
 * アクセス元のIPアドレス・HTTPメソッド・リクエストURI・例外メッセージをログに記録し、
 * クライアントには {@code 403 Forbidden} を返します。
 * </p>
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    /** 認可失敗の詳細を記録するロガー。 .*/
    private static final Logger LOG =
            LoggerFactory.getLogger(CustomAccessDeniedHandler.class);

    /** .
     * 認可失敗時に呼び出され、ログ出力とエラーレスポンスを行います。
     *
     * @param request クライアントからの HTTP リクエスト
     * @param response サーバーからの HTTP レスポンス
     * @param accessDeniedException 認可失敗の原因となった例外
     * @throws IOException レスポンス送信時の入出力例外
     */
    @Override
    public void handle(final HttpServletRequest request,
                       final HttpServletResponse response,
                       final AccessDeniedException accessDeniedException)
                             throws IOException {
        LOG.warn("認可失敗: {} {} from IP [{}] - {}",
                 request.getMethod(),
                 request.getRequestURI(),
                 IpAddressUtil.getClientIp(request),
                 accessDeniedException.getMessage());
        response.sendError(HttpServletResponse.SC_FORBIDDEN, "アクセスが拒否されました");
    }
}

