package com.syxbruno.CrudClientRegistration.util.user;

import com.syxbruno.CrudClientRegistration.dto.user.UserDeleteDTO;

public class CreateDeleteUserTest {

    public static UserDeleteDTO createUserToDelete() {

        return UserDeleteDTO.builder()
                .login(CreateUserTest.createValidUser().getName())
                .build();
    }
}
