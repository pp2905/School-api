package com.patryk.school.service;

import com.patryk.school.dao.MarkDao;
import com.patryk.school.exception.ApiRequestException;
import com.patryk.school.model.Mark;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarkService {
    
    private final MarkDao markDao;

    public MarkService(@Qualifier("markDao") MarkDao markDao) {
        this.markDao = markDao;
    }

    public List<Mark> getAllMarks() {
        List<Mark> marks = markDao.selectAllMarks();
        if(marks.isEmpty())
            throw new ApiRequestException("Not found marks");
        
        return marks; 
    }

    public Mark getMarkByID(int markId) {
        try {
            return markDao.selectMarkById(markId);
        } catch (Exception e) {
            throw new ApiRequestException(String.format("Not found mark with id: %s", markId));
        }
    }

    public void addNewMark(Mark mark) {
        markDao.insertNewMark(mark);
    }

    public void updateMarkById(int markId, Mark mark) {
        //getMarkById check if the students exist in the database, if not throw ApiRequestException (404 not found)
        Mark markToUpdate = getMarkByID(markId);

        if(mark.getStudentId() != 0)
            markToUpdate.setStudentId(mark.getStudentId());

        if(mark.getLessonId() != 0)
            markToUpdate.setLessonId(mark.getLessonId());

        if(mark.getMark() != 0)
            markToUpdate.setMark(mark.getMark());

        if(mark.getDescription() != null)
            markToUpdate.setDescription(mark.getDescription());
        
        markToUpdate.setMarkId(markId);
        markDao.updateMarkById(markToUpdate);
    }

    public void deleteMarkById(int markId) {
        //getMarkById check if the students exist in the database, if not throw ApiRequestException (404 not found)
        Mark markToDelete = getMarkByID(markId);
        markDao.deleteMarkById(markId);
    }
}
