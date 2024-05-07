package com.annodocs.annodocsbackend.user.controller;

import com.annodocs.annodocsbackend.core.responsewrapper.Response;
import com.annodocs.annodocsbackend.core.security.JwtService;
import com.annodocs.annodocsbackend.core.security.TokenDTO;
import com.annodocs.annodocsbackend.user.UserEntity;
import com.annodocs.annodocsbackend.user.controller.wsto.LoginWsTo;
import com.annodocs.annodocsbackend.user.controller.wsto.RegisterWsTo;
import com.annodocs.annodocsbackend.user.service.AuthenticationService;
import com.annodocs.annodocsbackend.user.service.UserCrudService;
import com.annodocs.annodocsbackend.user.service.dto.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.Cookie;
import lombok.*;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
@Validated
public class UserController
{
    private final UserCrudService userCrudService;
    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    @Operation(summary = "Create a new user")
    @PostMapping("/create")
    public ResponseEntity<Response> createUser(@RequestBody RegisterWsTo registerRequest)
    {
        UserDTO userDTO = new UserDTO(
                registerRequest.email(),
                registerRequest.name(),
                registerRequest.surname(),
                registerRequest.password(),
                registerRequest.state(),
                UserEntity.Role.USER
        );

        TokenDTO tokens = userCrudService.createUser(userDTO);

        //set http only cookie
        ResponseCookie cookie = ResponseCookie.from("refreshToken", tokens.refreshToken())
                .httpOnly(true)
                .path("/api/v1/user/refresh")
                .maxAge(60 * 60 * 24 * 30) //30 days
                .build();

        if(registerRequest.rememberMe())
            return ResponseEntity
                    .ok()
                    .header("Set-Cookie", cookie.toString())
                    .body(Response.of(tokens.accessToken(), true));
        else
            return ResponseEntity
                    .ok()
                    .body(Response.of(tokens.accessToken(), true));
    }

    @Operation(summary = "Login user and return token")
    @PostMapping("/login")
    public ResponseEntity<Response> loginUser(@RequestBody LoginWsTo loginRequest)
    {
        TokenDTO tokens = authenticationService.authenticate(loginRequest.email(), loginRequest.password());

        ResponseCookie cookie = ResponseCookie.from("refreshToken", tokens.refreshToken())
                .httpOnly(true)
                .path("/api/v1/user/refresh")
                .maxAge(60 * 60 * 24 * 30) //30 days
                .build();

        //create response
        if(loginRequest.rememberMe())
            return ResponseEntity
                    .ok()
                    .header("Set-Cookie", cookie.toString())
                    .body(Response.of(tokens.accessToken(), true));
        else
            return ResponseEntity
                    .ok()
                    .body(Response.of(tokens.accessToken(), true));
    }

    @Operation(summary = "get new access token with refresh token")
    @PostMapping("/refresh")
    public ResponseEntity<Response> refreshToken(@CookieValue(name = "refreshToken") String refreshToken)
    {
        TokenDTO tokens = authenticationService.authenticate(refreshToken);

        ResponseCookie cookie = ResponseCookie.from("refreshToken", tokens.refreshToken())
                .httpOnly(true)
                .path("/api/v1/user/refresh")
                .maxAge(60 * 60 * 24 * 30) //30 days
                .build();

        return ResponseEntity
                .ok()
                .header("Set-Cookie", cookie.toString())
                .body(Response.of(tokens.accessToken(), true));
    }

    @Operation(summary = "Logout user")
    @PostMapping("/logout")
    public ResponseEntity<Response> logoutUser(@CookieValue(name = "refreshToken") String refreshToken)
    {
        jwtService.invalidateRefreshTokens(refreshToken);

        ResponseCookie cookie = ResponseCookie.from("refreshToken", "")
                .httpOnly(true)
                .path("/api/v1/user/refresh")
                .maxAge(0)
                .build();

        return ResponseEntity
                .ok()
                .header("Set-Cookie", cookie.toString())
                .body(Response.of("Logged out", true));
    }

    @GetMapping("/mockfunc")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @Operation(
            security = {@SecurityRequirement(name = "Authorization")},
            description = "Returns the users email"
    )
    public ResponseEntity<Response> mockfunc()
    {
        return ResponseEntity.ok(Response.of(authenticationService.getCurrentUser().getEmail(), true));
    }


}
