package com.annodocs.annodocsbackend.user.service;

import com.annodocs.annodocsbackend.user.UserEntity;
import com.annodocs.annodocsbackend.user.exception.UserExistsException;
import com.annodocs.annodocsbackend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService
{

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserEntity save(UserEntity user) {
        if (userRepo.existsByEmail(user.getEmail()))
            throw new UserExistsException();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public List<UserEntity> find() {
        return userRepo.findAll();
    }

    @Override
    public UserEntity loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

}