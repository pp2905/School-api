package com.patryk.school.service;

import com.patryk.school.dao.StudentDao;
import com.patryk.school.exception.ApiRequestException;
import com.patryk.school.model.Student;
import com.patryk.school.model.StudentLesson;
import com.patryk.school.model.StudentMark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    
    private final StudentDao studentDao;

    @Autowired
    public StudentService(@Qualifier("studentDao") StudentDao studentDao) {
        this.studentDao = studentDao;
    }
    
    public List<Student> getAllStudents() {
        List<Student> students = studentDao.selectAllStudents();
        if(students.isEmpty())
            throw new ApiRequestException("Not found students");
        
        return students;
    }
    
    public Student getStudentByID(int studentId) {
        try {
            return studentDao.selectStudentById(studentId);
        } catch (Exception e) {
            throw new ApiRequestException(String.format("Not found student with id: %s", studentId));
        }
    }

    public List<StudentMark> getStudentMarksByStudentId(int studentId) {
        List<StudentMark> studentMarks = studentDao.selectStudentMarksByStudentId(studentId);
        if(studentMarks.isEmpty())
            throw new ApiRequestException(String.format("Not found marks with student id: %s", studentId));
        
        return studentMarks;
    }

    public List<StudentLesson> getStudentLessonsByStudentId(int studentId) {
        List<StudentLesson> studentLessons = studentDao.selectLessonsByStudentId(studentId);
        if(studentLessons.isEmpty())
            throw new ApiRequestException(String.format("Not found lessons with student id: %s", studentId));

        return studentLessons;
    }
    
    public void addNewStudent(Student student) {
        studentDao.insertNewStudent(student);
    }
    
    public void updateStudentByID(int studentId, Student student) {
        //getStudentById check if the students exist in the database, if not throw ApiRequestException (404 not found)
        Student studentToUpdate = getStudentByID(studentId);
        
        if(student.getFirstName() != null)
            studentToUpdate.setFirstName(student.getFirstName());

        if(student.getLastName() != null)
            studentToUpdate.setLastName(student.getLastName());

        if(student.getAge() != 0)
            studentToUpdate.setAge(student.getAge());

        if(student.getEmail() != null)
            studentToUpdate.setEmail(student.getEmail());

        if(student.getGender() != null)
            studentToUpdate.setGender(Student.Gender.valueOf(student.getGender().name().toUpperCase()));

        studentDao.updateStudentById(studentToUpdate);
    }
    
    public void deleteStudentById(int studentId) {
        //getStudentById check if the students exist in the database, if not throw ApiRequestException (404 not found)
        Student studentToDelete = getStudentByID(studentId);
        studentDao.deleteStudentById(studentId);
    }
}
