package com.freelance.agent.restapi.repository;

import org.apache.ibatis.annotations.Mapper;

import com.freelance.agent.restapi.model.UserEntity;

@Mapper
public interface UserMapper {
    UserEntity findByLoginId(String loginId);
}
