package com.freelance.agent.restapi.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.freelance.agent.restapi.model.UserEntity;
import com.freelance.agent.restapi.repository.UserMapper;
import com.freelance.agent.restapi.security.CustomUserDetails;

/** .
 * Spring Security におけるユーザー認証情報の取得を担うサービスクラス。
 * <p>
 * {@link UserDetailsService} を実装し、ログインIDに基づいてユーザー情報を取得し、
 * {@link CustomUserDetails} として返却することで、認証処理に必要な情報を提供します。
 * </p>
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    /** ユーザー情報をデータベースから取得するためのマッパー。 .*/
    private final UserMapper userMapper;

    /** .
     * コンストラクタインジェクションにより {@link UserMapper} を受け取ります。
     *
     * @param mapper ユーザー情報取得用のマッパー
     */
    public CustomUserDetailsService(final UserMapper mapper) {
        this.userMapper = mapper;
    }

    /** .
     * 指定されたユーザー名（ログインID）に対応するユーザー情報を取得します。
     * <p>
     * ユーザーが存在しない場合は {@link UsernameNotFoundException} をスローします。
     * 正常に取得できた場合は {@link CustomUserDetails} にラップして返します。
     * </p>
     *
     * @param username ログインID
     * @return 認証処理に使用される {@link UserDetails} 実装
     * @throws UsernameNotFoundException ユーザーが存在しない場合
     */
    @Override
    public CustomUserDetails loadUserByUsername(final String username)
            throws UsernameNotFoundException {
        UserEntity user = userMapper.findByLoginId(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        return new CustomUserDetails(user);
    }
}
