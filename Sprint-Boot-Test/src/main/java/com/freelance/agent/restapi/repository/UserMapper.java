package com.freelance.agent.restapi.repository;

import org.apache.ibatis.annotations.Mapper;

import com.freelance.agent.restapi.model.UserEntity;

/** .
 * ユーザー情報を取得するための MyBatis マッパーインターフェース。
 * <p>
 * 認証処理において、ログインIDに基づいてユーザー情報を検索します。
 * 実装は {@code UserMapper.xml} またはアノテーションベースで提供されます。
 * </p>
 */
@Mapper
public interface UserMapper {

    /** .
     * 指定されたログインIDに対応するユーザー情報を取得します。
     *
     * @param loginId ユーザーのログインID（認証時の識別子）
     * @return 該当する {@link UserEntity} オブジェクト。存在しない場合は {@code null}
     */
    UserEntity findByLoginId(String loginId);
}

