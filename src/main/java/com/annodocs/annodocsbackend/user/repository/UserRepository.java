package com.annodocs.annodocsbackend.user.repository;

import com.annodocs.annodocsbackend.user.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByEmail(String email);
    boolean existsByEmail(String email);



}
