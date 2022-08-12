package com.janchondo.students.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.janchondo.students.entity.Student;

@Repository
public interface iStudentDAO extends JpaRepository<Student, Long>{

	
}
