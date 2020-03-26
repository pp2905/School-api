package com.patryk.school.controller;

import com.patryk.school.exception.ApiRequestException;
import com.patryk.school.model.Lesson;
import com.patryk.school.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lessons")
public class LessonController {
    
    private final LessonService lessonService;

    @Autowired
    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Lesson> getAllLesson() {
        return lessonService.getAllLesson();
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            path = "{lessonId}"
    )
    public Lesson getLesson(@PathVariable("lessonId") int lessonId) {
        return lessonService.getLessonByID(lessonId);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public void addNewLesson(@RequestBody Lesson lesson) {
        lessonService.addNewLesson(lesson);
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            path = "{lessonId}"
    )
    public void updateLesson(@PathVariable("lessonId") int lessonId, @RequestBody Lesson lesson) {
        lessonService.updateLessonById(lessonId, lesson);
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            path = "{lessonId}"
    )
    public void deleteLessonById(@PathVariable("lessonId") int lessonId) {
        lessonService.deleteLessonById(lessonId);
    }
}
