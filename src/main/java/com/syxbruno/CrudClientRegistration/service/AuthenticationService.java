package com.syxbruno.CrudClientRegistration.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.syxbruno.CrudClientRegistration.domain.User;
import com.syxbruno.CrudClientRegistration.dto.user.UserLoginDTO;
import com.syxbruno.CrudClientRegistration.exception.BadRequestException;
import com.syxbruno.CrudClientRegistration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class  AuthenticationService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        User userByLogin = userRepository.findByLogin(login);

        if (userByLogin == null) {

            throw new UsernameNotFoundException("User not found");
        }

        return userByLogin;
    }

    public String getToken(UserLoginDTO loginUserDTO) {

        User user = userRepository.findByLogin(loginUserDTO.getLogin());

        if (user.getLogin() == null) {

            throw new BadRequestException("User not found");
        }

        return generateTokenJWT(user);
    }

    public String generateTokenJWT(User user) {

        try {
            Algorithm algorithm = Algorithm.HMAC256("SecretKey");

            return JWT.create()
                    .withIssuer("service/AuthenticationService")
                    .withSubject(user.getLogin())
                    .withExpiresAt(tokenExpiration())
                    .sign(algorithm);

        } catch (JWTCreationException e) {

            throw new RuntimeException("Error trying to generate token, about: " + e.getMessage());
        }
    }

    public String validTokenJwt(String token) {

        try {
            Algorithm algorithm = Algorithm.HMAC256("SecretKey");

            return JWT.require(algorithm)
                    .withIssuer("service/AuthenticationService")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException e) {

            return "";
        }
    }

    public Instant tokenExpiration() {

        return LocalDateTime
                .now()
                .plusHours(24)
                .toInstant(ZoneOffset.of("-03:00"));
    }
}
