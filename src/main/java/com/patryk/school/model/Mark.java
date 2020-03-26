package com.patryk.school.model;

public class Mark {
    private int markId;
    private int studentId;
    private int lessonId;
    private int mark;
    private String description;

    public Mark(int markId, int studentId, int lessonId, int mark, String description) {
        this.markId = markId;
        this.studentId = studentId;
        this.lessonId = lessonId;
        this.mark = mark;
        this.description = description;
        
    }

    public int getMarkId() {
        return markId;
    }

    public void setMarkId(int markId) {
        this.markId = markId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
