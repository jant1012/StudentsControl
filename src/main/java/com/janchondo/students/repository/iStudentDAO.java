package com.janchondo.students.repository;

import com.janchondo.students.entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iStudentDAO extends MongoRepository<Student, String> {

}
