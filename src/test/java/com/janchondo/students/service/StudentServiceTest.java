package com.janchondo.students.service;

import com.janchondo.students.entity.Student;
import com.janchondo.students.exception.NoStudentsFoundException;
import com.janchondo.students.repository.iStudentDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
    @Mock
    private iStudentDAO studentDAO;
    @InjectMocks
    private StudentService studentService;
    private Student student;
    Student student1 = new Student("1","Jesus","Anchondo","24","65894576957",
            6,9.6,false,"10-05-1997",false);
    Student student2 = new Student("2","David","Anchondo","24","65894576957",
            9,7.0,false,"10-05-1997",false);
    Student student3 = new Student("3","Jose","Anchondo","24","65894576957",
            3,7.0,false,"10-05-1997",false);
    Student student4 = new Student("3","Jose","Anchondo","24","65894576957",
            2,7.0,false,"10-05-1997",false);
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void searchAllStudentsTest() {
        List<Student> list = new ArrayList<>();
        list.add(student1);
        list.add(student2);
        list.add(student3);

        when(studentDAO.findAll()).thenReturn(list);

        List<Student> studentList = studentService.searchAllStudents();
        assertEquals(3,studentList.size());
        verify(studentDAO,times(1)).findAll();
    }
    @Test
    void searchStudentsThatImprovedScoresTest(){
        List<Student> list = new ArrayList<>();
        list.add(student1);
        list.add(student2);
        list.add(student3);
        list.add(student4);

        when(studentDAO.findAll()).thenReturn(list);
        int avgAttendance = studentService.getAttendanceAverage();

        List<Student> improvedList = new ArrayList<>();
        Student studentImp = new Student("1","Jesus","Anchondo","24","65894576957",
                6,9.7,false,"10-05-1997",false);
        Student student2Imp = new Student("2","David","Anchondo","24","65894576957",
                9,7.0,false,"10-05-1997",false);
        improvedList.add(studentImp);
        improvedList.add(student2Imp);

        when(studentDAO.findStudentsGtThanMinimumAttendance(avgAttendance)).thenReturn(improvedList);

        List<Student> studentList = studentService.searchStudentsThatImprovedScores();
        assertEquals(2,studentList.size());

        verify(studentDAO,times(1)).findStudentsGtThanMinimumAttendance(avgAttendance);
    }
    @Test
    void saveStudentTest(){
        studentService.saveStudent(student1);
        verify(studentDAO,times(1)).save(student1);
    }
    @Test
    void saveStudentWhenScoreAndAttendanceOutOfRangeTest(){
        Student student = new Student("1","Jesus","Anchondo","24","65894576957",
                -5,12,false,"10-05-1997",false);
        studentService.saveStudent(student);

        verify(studentDAO,times(1)).save(student);
    }
    @Test
    void saveStudentWhenAttendanceIsGreaterThanMaxTest(){
        Student student = new Student("1","Jesus","Anchondo","24","65894576957",
                25,7.5,false,"10-05-1997",false);
        studentService.saveStudent(student);

        verify(studentDAO,times(1)).save(student);
    }
    @Test
    void updateStudentTest() {
        when(studentDAO.findById("1")).thenReturn(Optional.of(student1));

        student1.setFirstName("David");
        student1.setFirstName("Perez");
        when(studentDAO.save(student1)).thenReturn(student1);

        assertThat(studentService.updateStudent(student1.getId(),student1)).isEqualTo(student1);
        verify(studentDAO,times(1)).findById("1");
    }
    @Test
    public void deleteStudentTest() {
        when(studentDAO.findById("1")).thenReturn(Optional.of(student1));

        student1.setIsDeleted(true);
        when(studentDAO.save(student1)).thenReturn(student1);

        assertThat(studentService.deleteStudent(student1.getId())).isEqualTo(student1);
        verify(studentDAO,times(1)).save(student1);
    }
    @Test
    void findStudentByIDTest(){
        when(studentDAO.findById("1")).thenReturn(Optional.of(student1));
        Student student = studentService.findStudentByID("1");

        assertEquals("Jesus", student.getFirstName());
        assertEquals("Anchondo", student.getLastName());
        assertEquals("24", student.getAge());

        verify(studentDAO,times(1)).findById("1");
    }
    @Test
    void getAttendanceAverageTest(){
        List<Student> list = new ArrayList<>();
        list.add(student1);
        list.add(student2);
        list.add(student3);

        when(studentDAO.findAll()).thenReturn(list);

        int average = studentService.getAttendanceAverage();
        assertEquals(6,average);
    }
    @Test
    void searchAllStudentsWithExceptionTest(){
        Exception e = assertThrows(NoStudentsFoundException.class, () -> {
            studentService.searchAllStudents();
        });

        assertEquals("Students not found!", e.getMessage());
    }
    @Test
    void searchStudentsThatImprovedScoresWithException(){
        Exception e = assertThrows(NoStudentsFoundException.class, () -> {
            studentService.searchStudentsThatImprovedScores();
        });

        assertEquals("Students not found!", e.getMessage());
    }
}
