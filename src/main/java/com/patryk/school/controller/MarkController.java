package com.patryk.school.controller;

import com.patryk.school.exception.ApiRequestException;
import com.patryk.school.model.Mark;
import com.patryk.school.service.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/marks")
public class MarkController {
    
    private final MarkService markService;

    @Autowired
    public MarkController(MarkService markService) {
        this.markService = markService;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Mark> getAllMark() {
        return markService.getAllMarks();
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            path = "{markId}"
    )
    public Mark getMarkById(@PathVariable("markId") int markId) {
        return markService.getMarkByID(markId);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public void addNewMark(@RequestBody Mark mark) {
        markService.addNewMark(mark);
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            path = "{markId}"
    )
    public void updateMarkById(@PathVariable("markId") int markId, @RequestBody Mark mark) {
        
        markService.updateMarkById(markId, mark);
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            path = "{markId}"
    )
    public void deleteMarkById(@PathVariable("markId") int markId) {
        markService.deleteMarkById(markId);
    }
    
}
