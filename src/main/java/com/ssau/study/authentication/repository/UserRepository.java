package com.ssau.study.authentication.repository;

import com.ssau.study.authentication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Boolean existsUserByLoginAndPassword(String login, String password);

    Optional<User> findUserByLogin(String login);

}
