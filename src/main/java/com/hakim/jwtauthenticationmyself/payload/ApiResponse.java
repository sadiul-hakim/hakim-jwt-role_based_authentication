package com.hakim.jwtauthenticationmyself.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse {
    private String msg;
    private boolean success;
}
