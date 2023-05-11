package com.hakim.jwtauthenticationmyself.service;

import com.hakim.jwtauthenticationmyself.exception.ResourceNotFoundException;
import com.hakim.jwtauthenticationmyself.model.User;
import com.hakim.jwtauthenticationmyself.payload.LoginRequest;
import com.hakim.jwtauthenticationmyself.repository.UserRepository;
import com.hakim.jwtauthenticationmyself.security.Role;
import com.hakim.jwtauthenticationmyself.utility.JwtHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtHelper jwtHelper;
    public User saveUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER.getRole());
        return userRepository.save(user);
    }
    public void deleteUser(long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        userRepository.delete(user);
    }
    public User getUser(long userId){
        return userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User not found with id: "+userId));
    }
    public List<User> getAllUser(){
        return userRepository.findAll();
    }
    public String authenticate(LoginRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword())
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + request.getEmail()));

        return jwtHelper.generateToken(user);
    }
}
