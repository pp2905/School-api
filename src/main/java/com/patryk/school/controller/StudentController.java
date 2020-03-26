package com.patryk.school.controller;

import com.patryk.school.model.Mark;
import com.patryk.school.model.Student;
import com.patryk.school.model.StudentLesson;
import com.patryk.school.model.StudentMark;
import com.patryk.school.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/students")
public class StudentController {
    
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    
    @RequestMapping(
            method = RequestMethod.GET
    )
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "{studentId}"
    )
    public Student getStudent(@PathVariable("studentId") int studentID) {
        return studentService.getStudentByID(studentID);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            path = "{studentId}/marks"
    )
    public List<StudentMark> getStudentMarks(@PathVariable("studentId") int studentId) {
        return studentService.getStudentMarksByStudentId(studentId);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            path = "{studentId}/lessons"
    )
    public List<StudentLesson> getStudentLessons(@PathVariable("studentId") int studentId) {
        return studentService.getStudentLessonsByStudentId(studentId);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public void addNewStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            path = "{studentId}"
    )
    public void updateStudent(@PathVariable("studentId") int studentId, @RequestBody Student student) {
        studentService.updateStudentByID(studentId, student);
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            path = "{studentId}"
    )
    public void deleteStudent(@PathVariable("studentId") int studentId) {
        studentService.deleteStudentById(studentId);
    }
}
