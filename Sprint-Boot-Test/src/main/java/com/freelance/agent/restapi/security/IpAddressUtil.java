package com.freelance.agent.restapi.security;

import jakarta.servlet.http.HttpServletRequest;

/** .
 * クライアントのIPアドレスを取得するユーティリティクラス。
 * <p>
 * 通常の {@link HttpServletRequest#getRemoteAddr()} に加え、
 * リバースプロキシ（例：Nginx、Apache）を経由する構成に対応するため、
 * {@code X-Forwarded-For} ヘッダーを優先的に参照します。
 * </p>
 */
public final class IpAddressUtil {

    private IpAddressUtil() { }
    /** .
     * クライアントのIPアドレスを取得します。
     * <p>
     * {@code X-Forwarded-For} ヘッダーが存在する場合は、最初のIPアドレスを返します。
     * 存在しない場合は {@link HttpServletRequest#getRemoteAddr()} を使用します。
     * </p>
     *
     * @param request HTTPリクエスト
     * @return クライアントのIPアドレス（リバースプロキシ対応済み）
     */
    public static String getClientIp(final HttpServletRequest request) {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader != null && !xfHeader.isEmpty()) {
            return xfHeader.split(",")[0].trim(); // 最初のIPがクライアント
        }
        return request.getRemoteAddr();
    }
}
