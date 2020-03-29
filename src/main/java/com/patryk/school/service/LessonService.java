package com.patryk.school.service;

import com.patryk.school.dao.LessonDao;
import com.patryk.school.exception.ApiRequestException;
import com.patryk.school.model.Lesson;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonService {
    
    private final LessonDao lessonDao;

    public LessonService(@Qualifier("lessonDao") LessonDao lessonDao) {
        this.lessonDao = lessonDao;
    }

    public List<Lesson> getAllLesson() {
        List<Lesson> lessons = lessonDao.selectAllLessons();;
        if(lessons.isEmpty())
            throw new ApiRequestException("Not found lessons");

        return lessons;
    }

    public Lesson getLessonByID(int lessonId) {
        try {
            return lessonDao.selectLessonById(lessonId);
        } catch (ApiRequestException e) {
            throw new ApiRequestException(String.format("Not found Lesson with id: %s", lessonId));
        }
    }
    
    public void addNewLesson(Lesson lesson) {
        lessonDao.insertNewLesson(lesson);
    }

    public void updateLessonById(int lessonId, Lesson lesson) {
        //getLessonId check if the students exist in the database, if not throw ApiRequestException (404 not found)
        Lesson lessonToUpdate = getLessonByID(lessonId);
        
        if(lesson.getName() != null)
            lessonToUpdate.setName(lesson.getName());

        if(lesson.getShortName() != null)
            lessonToUpdate.setShortName(lesson.getShortName());
        
        lessonToUpdate.setLessonId(lessonId);
        lessonDao.updateLessonById(lessonToUpdate);
    }

    public void deleteLessonById(int lessonId) {
        //getLessonId check if the students exist in the database, if not throw ApiRequestException (404 not found)
        Lesson lessonToDelete = getLessonByID(lessonId);
        lessonDao.deleteLessonById(lessonId);
    }
}
