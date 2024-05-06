package com.annodocs.annodocsbackend.user.service;

import com.annodocs.annodocsbackend.core.security.JwtService;
import com.annodocs.annodocsbackend.core.security.TokenDTO;
import com.annodocs.annodocsbackend.user.UserEntity;
import com.annodocs.annodocsbackend.user.exception.AuthenticationFailedException;
import com.annodocs.annodocsbackend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService
{

    private final AuthenticationManager authenticationManager;
    private final JwtService tokenService;
    private final UserService userService;

    public TokenDTO authenticate(String email, String password)
    {
        // The authentication manager provides secure authentication and throws exception if it fails
        var authToken = new UsernamePasswordAuthenticationToken(email, password);
        Authentication authenticate = authenticationManager.authenticate(authToken);
        var user = (UserEntity) authenticate.getPrincipal();
        return new TokenDTO(tokenService.generateToken(user), tokenService.generateRefreshToken(user));
    }

    public TokenDTO authenticate(String refreshToken)
    {
        String email = tokenService.verifyRefreshToken(refreshToken);
        UserEntity user = userService.loadUserByUsername(email);
        return new TokenDTO(tokenService.generateToken(user), tokenService.generateRefreshToken(user));
    }

    public UserEntity getUserByEmail(String email){
        return userService.loadUserByUsername(email);
    }

    public UserEntity getCurrentUser(){
        String emailOfCurrentUser = SecurityContextHolder.getContext().getAuthentication().getName();

        return getUserByEmail(emailOfCurrentUser);
    }

}
