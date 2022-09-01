package com.janchondo.students.security.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
public class AuthenticationRequest {
    private String username;
    private String password;
}
