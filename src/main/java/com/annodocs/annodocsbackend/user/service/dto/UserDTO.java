package com.annodocs.annodocsbackend.user.service.dto;

import com.annodocs.annodocsbackend.user.UserEntity;

public record UserDTO (String email, String name, String surname, String password, String state, UserEntity.Role role) {
}
