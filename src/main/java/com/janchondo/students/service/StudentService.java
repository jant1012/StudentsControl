package com.janchondo.students.service;

import java.util.List;
import java.util.stream.Collectors;

import com.janchondo.students.DTO.StudentDTO;
import com.janchondo.students.exception.StudentNotFoundException;
import com.janchondo.students.repository.iStudentDAO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.janchondo.students.entity.Student;
import com.janchondo.students.exception.NoStudentsFoundException;

@Service
public class StudentService{
	private iStudentDAO studentDAO;
	private int maxAttendance = 20;
	public StudentService(iStudentDAO studentDAO) {
    	this.studentDAO = studentDAO;
 	}
	public List<Student> searchAllStudents() {
		List<Student> studentsList = studentDAO.findAll();
		if(!studentsList.isEmpty()) {
			studentsList = studentsList.stream().filter(x -> x.getIsDeleted() == false).collect(Collectors.toList());
		}else {
			throw new NoStudentsFoundException(HttpStatus.NOT_FOUND, "Students not found!");
		}
		return studentsList;
	}
	public void saveStudent(Student student) {
		if(student.getScore() < 0 || student.getScore() > 10){
			student.setScore(0);
		}
		if(student.getAttendance() < 0) {
			student.setAttendance(0);
		}else if(student.getAttendance() > maxAttendance){
			student.setAttendance(maxAttendance);
		}
		studentDAO.save(student);
	}
	public void deleteStudent(String studentID) {
		Student student = findStudentByID(studentID);
		if (!student.equals(null)) {
			student.setIsDeleted(true);
			saveStudent(student);
		}	
	}
	public Student findStudentByID(String studentID) {
      return studentDAO.findById(studentID).orElseThrow(() -> new StudentNotFoundException(HttpStatus.NOT_FOUND,"Student with ID: " + studentID + " not found!"));
   	}
	public void updateStudent(String studentID, Student student) {
		Student studentOld = findStudentByID(studentID);
		if (!studentOld.equals(null)) {
			studentOld.setFirstName(student.getFirstName());
			studentOld.setLastName(student.getLastName());
			studentOld.setAge(student.getAge());
			studentOld.setPhone(student.getPhone());
			studentOld.setAttendance(student.getAttendance());
			studentOld.setScore(student.getScore());
			studentOld.setBirthday(student.getBirthday());

			saveStudent(studentOld);
		}
	}
	public void updateStudentAttendanceScores(String studentID, StudentDTO student){
		Student studentOld = findStudentByID(studentID);
		if(!studentOld.equals(null)){
			studentOld.setAttendance(student.getAttendance());
			studentOld.setScore(student.getScore());
			saveStudent(studentOld);
		}
	}
	public List<Student> searchStudentsThatImprovedScores() {
		int attendance = getAttendanceAverage();
		List<Student> studentsList = studentDAO.findStudentsGtThanMinimumAttendance(attendance);
		if(!studentsList.isEmpty()) {
			for(Student student : studentsList){
				if(!student.getIsScoreImproved() && !student.getIsDeleted() && student.getScore() > 0) {
					if(student.getScore() < 9.5) {
						student.setScore(student.getScore() + 0.5);
					}else{
						student.setScore(10);
					}
					student.setIsScoreImproved(true);
				}
				saveStudent(student);
			}
		}else{
			throw new NoStudentsFoundException(HttpStatus.NOT_FOUND,"Students not found!");
		}
		return studentsList;
	}
	public int getAttendanceAverage(){
		int average = 0;
		for(Student student : searchAllStudents()) {
			average += student.getAttendance();
		}
		return average/searchAllStudents().size();
	}
}