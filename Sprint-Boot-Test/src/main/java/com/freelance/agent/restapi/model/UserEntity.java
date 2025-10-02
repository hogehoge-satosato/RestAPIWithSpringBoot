package com.freelance.agent.restapi.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserEntity {
    private String loginId;
    private String password;
    private String role;
}

