package com.janchondo.students.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
//@Table(name="Students")
public class Student {
	@Id
	private Long studentID;
	private String firstName;
	private String lastName;
	private String age;
	private String phone;
	private String birthday;
	private Boolean isDeleted = false;
}

