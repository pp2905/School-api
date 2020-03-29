package com.patryk.school.model;

public class Lesson {
    private int lessonId;
    private String name;
    private String shortName;


    public Lesson(int lessonId, String name, String shortName) {
        this.lessonId = lessonId;
        this.name = name;
        this.shortName = shortName;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
}
