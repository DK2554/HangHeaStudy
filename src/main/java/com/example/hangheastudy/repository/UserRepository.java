package com.example.hangheastudy.repository;

import com.example.hangheastudy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserNameAndUserPasswd(String userName, String userPassword);

    Optional<User> findByUserName(String username);
}
