package com.janchondo.students.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class ExceptionDTO {
    private Date time;
    private String message;
    private String details;

    public ExceptionDTO(Date time, String message, String details) {
        this.time = time;
        this.message = message;
        this.details = details;
    }
}
