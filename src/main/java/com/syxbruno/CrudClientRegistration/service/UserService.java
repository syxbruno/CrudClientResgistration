package com.syxbruno.CrudClientRegistration.service;

import com.syxbruno.CrudClientRegistration.config.SecurityConfiguration;
import com.syxbruno.CrudClientRegistration.domain.User;
import com.syxbruno.CrudClientRegistration.dto.user.CreateUserDTO;
import com.syxbruno.CrudClientRegistration.dto.user.DeleteUserDTO;
import com.syxbruno.CrudClientRegistration.dto.user.LoginUserDTO;
import com.syxbruno.CrudClientRegistration.dto.user.ResponseUserDTO;
import com.syxbruno.CrudClientRegistration.exception.BadRequestException;
import com.syxbruno.CrudClientRegistration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    private SecurityConfiguration securityConfiguration;

    public ResponseUserDTO register(CreateUserDTO createUserDTO) {

        String passwordEncoder = securityConfiguration.passwordEncoder().encode(createUserDTO.getPassword());

        User user = User.builder()
                .name(createUserDTO.getName())
                .login(createUserDTO.getLogin())
                .password(passwordEncoder)
                .role(createUserDTO.getRole())
                .build();

        ResponseUserDTO responseUserDTO = ResponseUserDTO.builder()
                .name(createUserDTO.getName())
                .login(createUserDTO.getLogin())
                .build();

        User userByName = userRepository.findByLogin(createUserDTO.getLogin());

        if (userByName != null) {

            throw new BadRequestException("This user is already registered");
        }

        userRepository.save(user);
        return responseUserDTO;
    }

    public ResponseEntity<String> login(LoginUserDTO loginUserDTO) {

        User findByLogin = userRepository.findByLogin(loginUserDTO.getLogin());

        if (findByLogin == null) {

            throw new BadRequestException("User not found");
        }

        if (securityConfiguration.passwordEncoder().matches(loginUserDTO.getPassword(), findByLogin.getPassword())) {

            String token = authenticationService.getToken(loginUserDTO);
            return ResponseEntity.ok().body(token);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect login or password");
    }

    public ResponseEntity<String> delete(DeleteUserDTO deleteUserDTO) {

        User user = userRepository.findByLogin(deleteUserDTO.getLogin());
        userRepository.delete(user);

        return ResponseEntity.ok().body("User " + user.getLogin() + " successfully deleted");
    }
}
