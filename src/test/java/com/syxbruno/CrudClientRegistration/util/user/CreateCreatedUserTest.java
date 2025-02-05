package com.syxbruno.CrudClientRegistration.util.user;

import com.syxbruno.CrudClientRegistration.dto.user.UserCreateDTO;

public class CreateCreatedUserTest {

    public static UserCreateDTO createValidCreatedUser() {

        return UserCreateDTO.builder()
                .name(CreateUserTest.createValidUser().getName())
                .login(CreateUserTest.createValidUser().getLogin())
                .password(CreateUserTest.createValidUser().getPassword())
                .role(CreateUserTest.createValidUser().getRole())
                .build();
    }
}
