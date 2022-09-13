package com.janchondo.students.repository;

import com.janchondo.students.entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;

@Repository
public interface iStudentDAO extends MongoRepository<Student, String> {
    @Query("{attendance : {$gte: ?0}}")
    List<Student> findStudentsGtThanMinimumAttendance(int attendance);
}
