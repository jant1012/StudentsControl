package com.janchondo.students.DTO;

import lombok.Data;

@Data
public class StudentDTO {
    private int attendance;
    private double score;
    private Boolean isScoreImproved = false;
}
