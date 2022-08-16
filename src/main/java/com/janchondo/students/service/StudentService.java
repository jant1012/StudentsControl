package com.janchondo.students.service;

import java.util.List;
import java.util.stream.Collectors;
import com.janchondo.students.exception.UserNotFoundException;
import com.janchondo.students.repository.iStudentDAO;
import org.springframework.stereotype.Service;
import com.janchondo.students.entity.Student;
import com.janchondo.students.exception.NoDataFoundException;

@Service
public class StudentService{
	private iStudentDAO studentDAO;
	public StudentService(iStudentDAO studentDAO) {
    	this.studentDAO = studentDAO;
 	}
	public List<Student> searchAllStudents() {
		List<Student> studentsList = studentDAO.findAll();
		if(!studentsList.isEmpty()) {
			studentsList = studentsList.stream().filter(x -> x.getIsDeleted() == false).collect(Collectors.toList());
		}else {
			throw new NoDataFoundException();
		}
		return studentsList;
	}
	public void saveStudent(Student student) {
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
      return studentDAO.findById(studentID).orElseThrow(() -> new UserNotFoundException(studentID));
   	}
	public void updateStudent(String studentID, Student student) {
		Student studentOld = findStudentByID(studentID);
		if (!studentOld.equals(null)) {
			studentOld.setAge(student.getAge());
			studentOld.setBirthday(student.getBirthday());
			studentOld.setFirstName(student.getFirstName());
			studentOld.setLastName(student.getLastName());
			studentOld.setPhone(student.getPhone());
			saveStudent(studentOld);
		}
	}
}



