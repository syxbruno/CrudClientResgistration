package com.syxbruno.CrudClientRegistration.repository;

import com.syxbruno.CrudClientRegistration.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

    User findByLogin(String login);
}
