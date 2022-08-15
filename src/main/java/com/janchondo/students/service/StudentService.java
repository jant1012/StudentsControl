package com.janchondo.students.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.janchondo.students.entity.Student;
import com.janchondo.students.exception.NoDataFoundException;

@Service
public class StudentService{
	List<Student> studentsList = new ArrayList<>();
	public List<Student> searchAllStudents() {
		if(!studentsList.isEmpty()) {
			studentsList = studentsList.stream().filter(x -> x.getIsDeleted() == false).collect(Collectors.toList());
		}else {
			throw new NoDataFoundException();
		}
		return studentsList;
	}
	public void saveStudent(Student student) {
		if(findStudentByID(student.getStudentID()) == null){
			studentsList.add(student);
		}
	}
	public void deleteStudent(Long studentID) {
		Student student = findStudentByID(studentID);
		if (!student.equals(null)) {
			student.setIsDeleted(true);
			saveStudent(student);
		}	
	}
	public Student findStudentByID(Long studentID) {
		Long id = studentID <= 0 ? -1 : studentID;
		for (Student student : studentsList) {
			if (student.getStudentID() == id) {
				return student;
			}
		}
		return null;
	}
	public void updateStudent(Long studentID, Student student) {
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

