package com.patryk.school.model;

public class StudentMark {
    private final int studentId;
    private final String studentFirstname;
    private final String studentLastname;
    private final int markId;
    private final int mark;
    private final int lessonId;
    private final String lessonName;
    private final String markDescription;

    public StudentMark(int studentId, String firstnameStudent, String lastnameStudent, int markId, int mark, int lessonId, String lessonName, String markDescription) {
        this.studentId = studentId;
        this.studentFirstname = firstnameStudent;
        this.studentLastname = lastnameStudent;
        this.markId = markId;
        this.mark = mark;
        this.lessonId = lessonId;
        this.lessonName = lessonName;
        this.markDescription = markDescription;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getStudentFirstname() {
        return studentFirstname;
    }

    public String getStudentLastname() {
        return studentLastname;
    }

    public int getMarkId() {
        return markId;
    }

    public int getMark() {
        return mark;
    }

    public int getLessonId() {
        return lessonId;
    }

    public String getLessonName() {
        return lessonName;
    }

    public String getMarkDescription() {
        return markDescription;
    }
}
