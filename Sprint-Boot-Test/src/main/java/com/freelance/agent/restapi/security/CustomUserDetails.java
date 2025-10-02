package com.freelance.agent.restapi.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.freelance.agent.restapi.model.UserEntity;

/** .
 * {@link UserEntity} をラップし、Spring Security の認証処理に必要なユーザー情報を提供するクラス。
 * <p>
 * {@link UserDetails} を実装することで、Spring Security がユーザーの認証・認可を行う際に利用されます。
 * ロール情報は {@code ROLE_} プレフィックス付きで {@link GrantedAuthority} として提供されます。
 * </p>
 */
public class CustomUserDetails implements UserDetails {

    /** 認証対象のユーザー情報を保持するエンティティ。 .*/
    private final UserEntity user;

    /** .
     * コンストラクタ。
     *
     * @param usr 認証対象の {@link UserEntity}
     */
    public CustomUserDetails(final UserEntity usr) {
        this.user = usr;
    }

    /** .
     * ユーザー名（ログインID）を返します。
     *
     * @return ログインID
     */
    @Override
    public String getUsername() {
        return user.getLoginId();
    }

    /** .
     * パスワード（ハッシュ化された値）を返します。
     *
     * @return パスワード
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /** .
     * ユーザーの権限（ロール）を返します。
     * <p>
     * {@code ROLE_} プレフィックスを付与した {@link SimpleGrantedAuthority} を返します。
     * </p>
     *
     * @return 権限のコレクション
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
    }

    /** .
     * アカウントの有効期限が切れていないかを返します。
     *
     * @return 常に {@code true}
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /** .
     * アカウントがロックされていないかを返します。
     *
     * @return 常に {@code true}
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /** .
     * 資格情報の有効期限が切れていないかを返します。
     *
     * @return 常に {@code true}
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /** .
     * アカウントが有効かどうかを返します。
     *
     * @return 常に {@code true}
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
