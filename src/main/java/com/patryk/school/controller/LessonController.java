package com.patryk.school.controller;

import com.patryk.school.model.Lesson;
import com.patryk.school.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public List<Lesson> getAllLesson() {
        return lessonService.getAllLesson();
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            path = "{lessonId}"
    )
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Lesson getLesson(@PathVariable("lessonId") int lessonId) {
        return lessonService.getLessonByID(lessonId);
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public void addNewLesson(@RequestBody Lesson lesson) {
        lessonService.addNewLesson(lesson);
    }

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            path = "{lessonI}"
    )
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public void updateLesson(@PathVariable("lessonId") int lessonId, @RequestBody Lesson lesson) {
        lessonService.updateLessonById(lessonId, lesson);
    }

    @DeleteMapping(
            path = "{lessonId}"
    )
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public void deleteLessonById(@PathVariable("lessonId") int lessonId) {
        lessonService.deleteLessonById(lessonId);
    }
}
