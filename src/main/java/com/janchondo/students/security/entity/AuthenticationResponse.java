package com.janchondo.students.security.entity;

import lombok.Data;
@Data
public class AuthenticationResponse {
    private String response;
    public AuthenticationResponse() {
    }
    public AuthenticationResponse(String response) {
        this.response = response;
    }
}
