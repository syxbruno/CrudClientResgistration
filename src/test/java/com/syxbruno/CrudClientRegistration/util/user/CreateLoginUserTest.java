package com.syxbruno.CrudClientRegistration.util.user;

import com.syxbruno.CrudClientRegistration.dto.user.UserLoginDTO;

public class CreateLoginUserTest {

    public static UserLoginDTO createUserToLogin() {

        return UserLoginDTO.builder()
                .login(CreateUserTest.createValidUser().getLogin())
                .password(CreateUserTest.createValidUser().getPassword())
                .build();
    }
}
