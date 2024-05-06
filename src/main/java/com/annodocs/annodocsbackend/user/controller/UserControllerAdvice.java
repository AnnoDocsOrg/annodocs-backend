package com.annodocs.annodocsbackend.user.controller;

import com.annodocs.annodocsbackend.core.responsewrapper.Response;
import com.annodocs.annodocsbackend.user.exception.UserExistsException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MissingRequestCookieException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserControllerAdvice
{
    @ExceptionHandler(UserExistsException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Response> handleResourceNotFoundException(UserExistsException e) {
        return new ResponseEntity<Response>(Response.of("User exists already"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleResourceNotFoundException(BadCredentialsException e) {
        return new ResponseEntity<Response>(Response.of("Password wrong"), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(MissingRequestCookieException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleResourceNotFoundException(MissingRequestCookieException e) {
        return new ResponseEntity<Response>(Response.of("No refresh token provided"), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(JWTDecodeException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleResourceNotFoundException(JWTDecodeException e) {
        return new ResponseEntity<Response>(Response.of("Invalid token"), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(JWTVerificationException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleResourceNotFoundException(JWTVerificationException e) {
        return new ResponseEntity<Response>(Response.of("Invalid token"), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Response> handleResourceNotFoundException(Exception e) {
        return new ResponseEntity<Response>(Response.of("Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
