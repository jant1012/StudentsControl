package com.janchondo.students.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data
@Table(name="Students")
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long studentID;
	
	@Column(name="FirstName")
	@NotEmpty
	private String firstName;
	
	@Column(name="LastName")
	@NotEmpty
	private String lastName;
	
	@Column(name="Age")
	@NotEmpty
	private String age;
	
	@Column(name="Phone")
	private String phone;
	
	@Column(name="Birthday")
	private String birthday;
	
	@Column(name="IsDeleted")
	private Boolean isDeleted = false;
}
