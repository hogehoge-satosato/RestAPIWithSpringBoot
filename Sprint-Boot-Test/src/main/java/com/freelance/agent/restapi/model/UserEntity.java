package com.freelance.agent.restapi.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/** .
 * 認証・認可に使用されるユーザー情報のエンティティ。
 * <p>
 * ログインID、パスワード、ロール（権限）を保持し、Spring Security による認証処理や
 * ロールベースのアクセス制御に利用されます。
 * </p>
 */
@Data
@NoArgsConstructor
public class UserEntity {

    /** .
     * ユーザーのログインID。
     * <p>
     * 一意な識別子として使用され、認証時のユーザー名に相当します。
     * </p>
     */
    private String loginId;

    /** .
     * ユーザーのパスワード（ハッシュ化された値）。
     * <p>
     * セキュリティ上、平文では保持せず、
     * {@link org.springframework.security.crypto.password.PasswordEncoder}
     * によってハッシュ化された値を保存することが推奨されます。
     * </p>
     */
    private String password;

    /** .
     * ユーザーのロール（権限）。
     * <p>
     * 例：{@code "admin"}, {@code "user"} など。
     * Spring Security では {@code ROLE_} プレフィックスが付与されます。
     * アクセス制御に使用され、{@code hasRole("admin")} のような条件で評価されます。
     * </p>
     */
    private String role;
}

