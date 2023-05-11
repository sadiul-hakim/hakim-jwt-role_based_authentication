package com.hakim.jwtauthenticationmyself.controller;

import com.hakim.jwtauthenticationmyself.model.User;
import com.hakim.jwtauthenticationmyself.payload.LoginRequest;
import com.hakim.jwtauthenticationmyself.payload.LoginResponse;
import com.hakim.jwtauthenticationmyself.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user){
        User savedUser = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        String token = userService.authenticate(loginRequest);

        return ResponseEntity.ok(new LoginResponse(token));
    }

}
