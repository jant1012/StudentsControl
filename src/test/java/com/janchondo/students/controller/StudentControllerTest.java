package com.janchondo.students.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.janchondo.students.entity.Student;
import com.janchondo.students.service.StudentService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTest {
    @MockBean
    private StudentService studentService;
    @Autowired
    private MockMvc mockMvc;
    Student student1 = new Student("1","Jesus","Anchondo","24","65894576957",
        6,9.6,false,"10-05-1997",false);
    Student student2 = new Student("2","David","Anchondo","24","65894576957",
            9,7.0,false,"10-05-1997",false);

    ObjectMapper objectMapper;
    @BeforeEach
    void setup(){
        objectMapper = new ObjectMapper();
    }
    @Test
    void searchAllTest() throws Exception{
        doReturn(Lists.newArrayList(student1,student2)).when(studentService).searchAllStudents();

        mockMvc.perform(get("/students").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[0].id",is("1")))
                .andExpect(jsonPath("$[0].firstName",is("Jesus")))
                .andExpect(jsonPath("$[0].lastName",is("Anchondo")))
                .andExpect(jsonPath("$[0].age",is("24")))
                .andExpect(jsonPath("$[0].phone",is("65894576957")))
                .andExpect(jsonPath("$[0].attendance",is(6)))
                .andExpect(jsonPath("$[0].score",is(9.6)))
                .andExpect(jsonPath("$[0].isScoreImproved",is(false)))
                .andExpect(jsonPath("$[0].birthday",is("10-05-1997")))
                .andExpect(jsonPath("$[0].isDeleted",is(false)))

                .andExpect(jsonPath("$[1].id",is("2")))
                .andExpect(jsonPath("$[1].firstName",is("David")))
                .andExpect(jsonPath("$[1].lastName",is("Anchondo")))
                .andExpect(jsonPath("$[1].age",is("24")))
                .andExpect(jsonPath("$[1].phone",is("65894576957")))
                .andExpect(jsonPath("$[1].attendance",is(9)))
                .andExpect(jsonPath("$[1].score",is(7.0)))
                .andExpect(jsonPath("$[1].isScoreImproved",is(false)))
                .andExpect(jsonPath("$[1].birthday",is("10-05-1997")))
                .andExpect(jsonPath("$[1].isDeleted",is(false)));
    }
    @Test
    void searchStudentByIdTest() throws Exception {
        when(studentService.findStudentByID("1")).thenReturn(student1);

        mockMvc.perform(get("/students/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.firstName").value("Jesus"))
                .andExpect(jsonPath("$.lastName").value("Anchondo"))
                .andExpect(jsonPath("$.age").value("24"))
                .andExpect(jsonPath("$.phone").value("65894576957"))
                .andExpect(jsonPath("$.attendance").value(6))
                .andExpect(jsonPath("$.score").value(9.6))
                .andExpect(jsonPath("$.isScoreImproved").value(false))
                .andExpect(jsonPath("$.birthday").value("10-05-1997"))
                .andExpect(jsonPath("$.isDeleted").value(false));

        verify(studentService).findStudentByID("1");
    }
    @Test
    void saveStudentTest() throws Exception{
        mockMvc.perform(post("/students/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student1)))
                .andExpect(status().isOk());

        verify(studentService).saveStudent(any());
    }
    @Test
    void updateStudentTest() throws Exception{
        student1.setFirstName("Antonio");
        student1.setScore(6.4);
        mockMvc.perform(patch("/students/update/1").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student1)))
                .andExpect(status().isOk());
    }
    @Test
    void deleteStudentTest() throws Exception{
        student1.setIsDeleted(true);
        mockMvc.perform(patch("/students/delete/1").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student1)))
                .andExpect(status().isOk());
    }
    @Test
    void improveQualificationsWithAssistanceTest() throws Exception{
        mockMvc.perform(patch("/students/scores").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student1)))
                .andExpect(status().isOk());
    }
}
