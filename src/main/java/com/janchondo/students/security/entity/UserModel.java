package com.janchondo.students.security.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Users")
public class UserModel {
    @Id
    private String id;
    private String username;
    private String password;
}
