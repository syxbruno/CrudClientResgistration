package com.syxbruno.CrudClientRegistration.controller;

import com.syxbruno.CrudClientRegistration.dto.user.CreateUserDTO;
import com.syxbruno.CrudClientRegistration.dto.user.DeleteUserDTO;
import com.syxbruno.CrudClientRegistration.dto.user.LoginUserDTO;
import com.syxbruno.CrudClientRegistration.dto.user.ResponseUserDTO;
import com.syxbruno.CrudClientRegistration.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/admin/register")
    private ResponseEntity<String> register(@RequestBody @Valid CreateUserDTO userToSave) {

        ResponseUserDTO register = userService.register(userToSave);
        return ResponseEntity.ok().body("Registered user, about: " + register);
    }

    @PostMapping(path = "/public/login")
    private ResponseEntity<String> login(@RequestBody @Valid LoginUserDTO loginUserDTO) {

        return userService.login(loginUserDTO);
    }

    @DeleteMapping(path = "/admin/delete")
    private ResponseEntity<String> login(@RequestBody @Valid DeleteUserDTO deleteUserDTO) {

        return userService.delete(deleteUserDTO);
    }
}
