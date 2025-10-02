package com.freelance.agent.restapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.freelance.agent.restapi.security.CustomAccessDeniedHandler;
import com.freelance.agent.restapi.service.CustomUserDetailsService;

/** .
 * Spring Security のセキュリティ設定クラス。
 * <p>
 * {@link EnableWebSecurity} により Web セキュリティが有効化され、
 * 認証・認可・パスワードエンコーディング・ユーザー詳細サービスの構成を提供します。
 * </p>
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /** ユーザー認証情報を提供するカスタムサービス。 .*/
    private final CustomUserDetailsService userDetailsService;

    /** .
     * コンストラクタインジェクションにより {@link CustomUserDetailsService} を受け取ります。
     *
     * @param service ユーザー情報を取得するサービス
     */
    public SecurityConfig(final CustomUserDetailsService service) {
        this.userDetailsService = service;
    }

    /** .
     * HTTPセキュリティのフィルターチェーンを構成します。
     * <ul>
     *   <li>CSRF保護を無効化</li>
     *   <li>{@code /products/**} のパスのGETに対して
     *       {@code admin, user} ロールのいずれかを要求</li>
     *   <li>{@code /products/**} パスに対して {@code admin} ロールを要求</li>
     *   <li>その他のリクエストは認証を要求</li>
     *   <li>HTTP Basic 認証を使用</li>
     * </ul>
     *
     * @param http {@link HttpSecurity} の設定オブジェクト
     * @param accessDeniedHandler {@link CustomAccessDeniedHandler} の例外ハンドラー
     * @return 構成済みの {@link SecurityFilterChain}
     * @throws Exception セキュリティ構成中の例外
     */
    @Bean
    public SecurityFilterChain filterChain(
            final HttpSecurity http,
            final CustomAccessDeniedHandler accessDeniedHandler)
                    throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.GET, "/products/**")
                    .hasAnyRole("admin", "user")
                .requestMatchers("/products/**").hasRole("admin")
                .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults())
            .exceptionHandling(
                    ex -> ex.accessDeniedHandler(accessDeniedHandler));

        return http.build();
    }

    /** .
     * {@link AuthenticationManager} を構成します。
     * <p>
     * {@link DaoAuthenticationProvider} を使用し、ユーザー情報とパスワードエンコーダーを設定します。
     * </p>
     *
     * @param encoder パスワードのハッシュ化に使用するエンコーダー
     * @return 構成済みの {@link AuthenticationManager}
     */
    @Bean
    public AuthenticationManager authenticationManager(
            final PasswordEncoder encoder) {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(encoder);
        return new ProviderManager(provider);
    }

    /** .
     * {@link UserDetailsService} を Bean として提供します。
     *
     * @return カスタムユーザー詳細サービス
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return userDetailsService;
    }

    /** .
     * パスワードエンコーダーとして {@link BCryptPasswordEncoder} を提供します。
     *
     * @return BCrypt によるパスワードエンコーダー
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

