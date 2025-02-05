package com.syxbruno.CrudClientRegistration.util.user;

import com.syxbruno.CrudClientRegistration.domain.User;
import com.syxbruno.CrudClientRegistration.enums.Role;

public class CreateUserTest {

    public static User createValidUser() {

        return User.builder()
                .id(1L)
                .name("bruno oliveira")
                .login("syxbruno")
                .password("12345678")
                .role(Role.ADMIN)
                .build();
    }
}
