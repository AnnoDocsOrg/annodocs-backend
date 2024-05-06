package com.annodocs.annodocsbackend.core.security;

public record TokenDTO(String accessToken, String refreshToken)
{
}