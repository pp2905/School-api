package com.patryk.school.dao;

import com.patryk.school.model.Mark;

import java.util.List;

public interface IMarkDao {

    List<Mark> selectAllMarks();

    Mark selectMarkById(int markId);

    int insertNewMark(Mark mark);

    int updateMarkById(Mark mark);

    int deleteMarkById(int markId);
}
