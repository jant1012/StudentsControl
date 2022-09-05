package com.janchondo.students.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Students")
public class Student {
	@Id
	private String id;
	private String firstName;
	private String lastName;
	private String age;
	private String phone;
	private int attendance;
	private double score;
	private Boolean isScoreImproved = false;
	private String birthday;
	private Boolean isDeleted = false;
}


