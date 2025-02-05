package com.syxbruno.CrudClientRegistration.util.user;

import com.syxbruno.CrudClientRegistration.dto.user.UserResponseDTO;

public class CreateResponseUserTest {

    public static UserResponseDTO createValidResponseUser() {

        return UserResponseDTO.builder()
                .name(CreateUserTest.createValidUser().getName())
                .login(CreateUserTest.createValidUser().getLogin())
                .build();
    }
}
