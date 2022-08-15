package com.janchondo.students.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.janchondo.students.entity.Student;
import com.janchondo.students.service.StudentService;

@RestController
public class StudentController {
	private StudentService studentService;
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@GetMapping("/students")
	public ResponseEntity<List<Student>> searchAll() {
		List<Student> studentsList = studentService.searchAllStudents();
		return new ResponseEntity<List<Student>>(studentsList,HttpStatus.OK);
	}
	@PostMapping("/students/save")
	public void saveStudent(@RequestBody Student student) {
		studentService.saveStudent(student);
	}
	@GetMapping("/students/delete/{studentID}")
	@Validated
	public void deleteStudent(@PathVariable("studentID") Long studentID) {
		studentService.deleteStudent(studentID);
	}
	@PostMapping("/students/update/{studentID}")
	public void updateStudent(@PathVariable("studentID") Long studentID,@RequestBody Student student){
		studentService.updateStudent(studentID, student);
	}
	@GetMapping("/students/{studentID}")
	public ResponseEntity<Student> searchStudentById(@PathVariable("studentID") Long studentID) {
		 Student student= studentService.findStudentByID(studentID);
		return new ResponseEntity<Student>(student, HttpStatus.OK);
	}
}