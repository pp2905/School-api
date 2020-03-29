package com.patryk.school.controller;

import com.patryk.school.model.Student;
import com.patryk.school.model.StudentLesson;
import com.patryk.school.model.StudentMark;
import com.patryk.school.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "{studentId}"
    )
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STUDENT')")
    public Student getStudent(@PathVariable("studentId") int studentID) {
        return studentService.getStudentByID(studentID);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            path = "{studentId}/marks"
    )
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STUDENT')")
    public List<StudentMark> getStudentMarks(@PathVariable("studentId") int studentId) {
        return studentService.getStudentMarksByStudentId(studentId);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            path = "{studentId}/lessons"
    )
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STUDENT')")
    public List<StudentLesson> getStudentLessons(@PathVariable("studentId") int studentId) {
        return studentService.getStudentLessonsByStudentId(studentId);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public void addNewStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            path = "{studentId}"
    )
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public void updateStudent(@PathVariable("studentId") int studentId, @RequestBody Student student) {
        studentService.updateStudentByID(studentId, student);
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            path = "{studentId}"
    )
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public void deleteStudent(@PathVariable("studentId") int studentId) {
        studentService.deleteStudentById(studentId);
    }
}
