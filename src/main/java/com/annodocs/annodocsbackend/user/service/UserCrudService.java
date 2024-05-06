package com.annodocs.annodocsbackend.user.service;

import com.annodocs.annodocsbackend.core.security.TokenDTO;
import com.annodocs.annodocsbackend.user.UserEntity;
import com.annodocs.annodocsbackend.user.exception.UserExistsException;
import com.annodocs.annodocsbackend.user.repository.UserRepository;
import com.annodocs.annodocsbackend.user.service.dto.UserDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserCrudService
{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final AuthenticationService authenticationService;


    @Transactional
    public TokenDTO createUser(UserDTO userDTO) {

        if(doesUserExist(userDTO.email())) {
            throw new UserExistsException();
        }

        String encoded = passwordEncoder.encode(userDTO.password());

        UserEntity userEntity = UserEntity.builder()
                .email(userDTO.email())
                .name(userDTO.name())
                .surname(userDTO.surname())
                .password(encoded)
                .state(userDTO.state())
                .role(userDTO.role())
                .build();

        UserEntity savedUser = userRepository.save(userEntity);

        return authenticationService.authenticate(userDTO.email(), userDTO.password());
    }

    public boolean doesUserExist(String email) {
        Optional<UserEntity> byEmail = userRepository.findByEmail(email);
        return byEmail.isPresent();
    }
}
