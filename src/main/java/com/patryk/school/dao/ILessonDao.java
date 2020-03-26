package com.patryk.school.dao;

import com.patryk.school.model.Lesson;
import com.patryk.school.model.Student;

import java.util.List;

public interface ILessonDao {

    List<Lesson> selectAllLessons();

    Lesson selectLessonById(int lessonId);

    int insertNewLesson(Lesson lesson);

    int updateLessonById(Lesson lesson);

    int deleteLessonById(int lessonId);
}
