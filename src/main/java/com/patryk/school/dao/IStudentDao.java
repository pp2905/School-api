package com.patryk.school.dao;

import com.patryk.school.model.Student;
import com.patryk.school.model.StudentLesson;
import com.patryk.school.model.StudentMark;

import java.util.List;

public interface IStudentDao {

    List<Student> selectAllStudents();
    
    Student selectStudentById(int studentId);

    List<StudentMark> selectStudentMarksByStudentId(int studentId);

    List<StudentLesson> selectLessonsByStudentId(int studentId);
    
    int insertNewStudent(Student student);
    
    int updateStudentById(Student student);
    
    int deleteStudentById(int studentId);
}
