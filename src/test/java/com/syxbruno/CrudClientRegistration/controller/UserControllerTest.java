package com.syxbruno.CrudClientRegistration.controller;

import com.syxbruno.CrudClientRegistration.service.UserService;
import com.syxbruno.CrudClientRegistration.util.user.CreateCreatedUserTest;
import com.syxbruno.CrudClientRegistration.util.user.CreateDeleteUserTest;
import com.syxbruno.CrudClientRegistration.util.user.CreateLoginUserTest;
import com.syxbruno.CrudClientRegistration.util.user.CreateResponseUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;



    @BeforeEach
    void setUp() {

        BDDMockito.when(userService.register(CreateCreatedUserTest.createValidCreatedUser()))
                .thenReturn(CreateResponseUserTest.createValidResponseUser());


        BDDMockito.when(userService.login(CreateLoginUserTest.createUserToLogin()))
                .thenReturn(ResponseEntity.ok().body("return 'token' or 'Incorrect login or password'"));


        BDDMockito.when(userService.delete(CreateDeleteUserTest.createUserToDelete()))
                .thenReturn(ResponseEntity.ok().body("User bruno successfully deleted"));
    }



    @Test
    @DisplayName("register and returns a UserResponseDTO when successful")
    void Register_ReturnUserResponse_WhenSuccessful() {

        ResponseEntity<String> register = userController.register(CreateCreatedUserTest.createValidCreatedUser());
        ResponseEntity<String> compare = ResponseEntity.ok().body("Registered user, about: " + CreateResponseUserTest.createValidResponseUser());

        Assertions.assertThat(register)
                .isNotNull()
                .isEqualTo(compare);
    }


    @Test
    @DisplayName("login and returns a 'Token' or 'Incorrect login or password'")
    void Login_ReturnTokenOrIncorrectLoginOrPassword_WhenSuccessful() {

        ResponseEntity<String> login = userController.login(CreateLoginUserTest.createUserToLogin());
        ResponseEntity<String> compare = ResponseEntity.ok().body("return 'token' or 'Incorrect login or password'");

        Assertions.assertThat(login)
                .isNotNull()
                .isEqualTo(compare);
    }


    @Test
    @DisplayName("delete and returns a 'Username successfully deleted'")
    void DeleteUser_ReturnUsernameSuccessfullyDeleted_WhenSuccessful() {

        ResponseEntity<String> delete = userController.delete(CreateDeleteUserTest.createUserToDelete());
        ResponseEntity<String> compare = ResponseEntity.ok().body("User bruno successfully deleted");

        Assertions.assertThat(delete)
                .isNotNull()
                .isEqualTo(compare);
    }
}