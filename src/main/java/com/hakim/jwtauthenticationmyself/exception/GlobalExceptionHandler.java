package com.hakim.jwtauthenticationmyself.exception;

import com.hakim.jwtauthenticationmyself.payload.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundException(ResourceNotFoundException ex){
        return ResponseEntity.ok(new ApiResponse(ex.getMessage(),true));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiResponse> authenticationException(AuthenticationException ex){
        return ResponseEntity.ok(new ApiResponse(ex.getMessage(),true));
    }

}
