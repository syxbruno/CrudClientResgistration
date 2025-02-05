package com.syxbruno.CrudClientRegistration.controller;

import com.syxbruno.CrudClientRegistration.dto.user.UserCreateDTO;
import com.syxbruno.CrudClientRegistration.dto.user.UserDeleteDTO;
import com.syxbruno.CrudClientRegistration.dto.user.UserLoginDTO;
import com.syxbruno.CrudClientRegistration.dto.user.UserResponseDTO;
import com.syxbruno.CrudClientRegistration.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User Controller", description = "user related operations")

@RestController
@RequestMapping("auth")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(description = "Register a new user, this endpoint can only be used by a user with admin role", method = "POST", security = @SecurityRequirement(name = "BearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "400", description = "User is already registered", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "403", description = "Does not have the necessary authorization", content = @Content(schema = @Schema(hidden = true)))
    })
    @PostMapping(path = "/admin/register")
    public ResponseEntity<String> register(@RequestBody @Valid UserCreateDTO userToSave) {

        UserResponseDTO register = userService.register(userToSave);
        return ResponseEntity.ok().body("Registered user, about: " + register);
    }

    @Operation(description = "Anyone can access this endpoint and it is responsible for logging in a user", method = "POST")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "400", description = "User not found", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "403", description = "Does not have the necessary authorization", content = @Content(schema = @Schema(hidden = true)))
    })
    @PostMapping(path = "/public/login")
    public ResponseEntity<String> login(@RequestBody @Valid UserLoginDTO loginUserDTO) {

        return userService.login(loginUserDTO);
    }


    @Operation(description = "Delete existing users in the database, this endpoint can only be used by a user with admin role", method = "DELETE")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "403", description = "Does not have the necessary authorization", content = @Content(schema = @Schema(hidden = true)))
    })
    @DeleteMapping(path = "/admin/delete")
    public ResponseEntity<String> delete(@RequestBody @Valid UserDeleteDTO deleteUserDTO) {

        return userService.delete(deleteUserDTO);
    }
}
