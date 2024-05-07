package com.annodocs.annodocsbackend.user.controller.wsto;

public record LoginWsTo(String email, String password, boolean rememberMe)
{
}
