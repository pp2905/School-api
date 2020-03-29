package com.patryk.school.model;

import java.time.LocalDate;

public class StudentLesson {
    private int studentLessonId;
    private int student_id;
    private String firstNameStudent;
    private String lastNameStudent;
    private int lesson_id;
    private String lessonName;
    private LocalDate startDate;
    private LocalDate endDate;

    public StudentLesson(int studentLessonId, int student_id, String firstNameStudent, String lastNameStudent, int lesson_id, String lessonName, LocalDate startDate, LocalDate endDate) {
        this.studentLessonId = studentLessonId;
        this.student_id = student_id;
        this.firstNameStudent = firstNameStudent;
        this.lastNameStudent = lastNameStudent;
        this.lesson_id = lesson_id;
        this.lessonName = lessonName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getStudentLessonId() {
        return studentLessonId;
    }

    public void setStudentLessonId(int studentLessonId) {
        this.studentLessonId = studentLessonId;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public String getFirstNameStudent() {
        return firstNameStudent;
    }

    public void setFirstNameStudent(String firstNameStudent) {
        this.firstNameStudent = firstNameStudent;
    }

    public String getLastNameStudent() {
        return lastNameStudent;
    }

    public void setLastNameStudent(String lastNameStudent) {
        this.lastNameStudent = lastNameStudent;
    }

    public int getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(int lesson_id) {
        this.lesson_id = lesson_id;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
