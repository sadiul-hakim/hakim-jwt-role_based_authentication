package com.hakim.jwtauthenticationmyself.controller;

import com.hakim.jwtauthenticationmyself.model.User;
import com.hakim.jwtauthenticationmyself.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/list")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUser());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable long userId){
        return ResponseEntity.ok(userService.getUser(userId));
    }
}
