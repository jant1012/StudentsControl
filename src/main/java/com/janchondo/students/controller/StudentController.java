package com.janchondo.students.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
	public void deleteStudent(@PathVariable("studentID") String studentID) {
		studentService.deleteStudent(studentID);
	}
	@PatchMapping("/students/update/{studentID}")
	public void updateStudent(@PathVariable("studentID") String studentID,@RequestBody Student student){
		studentService.updateStudent(studentID, student);
	}
	@GetMapping("/students/{studentID}")
	public ResponseEntity<Student> searchStudentById(@PathVariable("studentID") String studentID) {
		 Student student= studentService.findStudentByID(studentID);
		return new ResponseEntity<Student>(student, HttpStatus.OK);
	}
}