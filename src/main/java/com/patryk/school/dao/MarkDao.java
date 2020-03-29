package com.patryk.school.dao;

import com.patryk.school.model.Mark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("markDao")
public class MarkDao implements IMarkDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MarkDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Mark> selectAllMarks() {
        String sql = "SELECT mark_id, student_id, lesson_id, mark, description FROM student_mark ORDER BY mark_id";
        return jdbcTemplate.query(sql, mapMarkFromDb());
    }

    @Override
    public Mark selectMarkById(int markId) {
        String sql = "SELECT mark_id, student_id, lesson_id, mark, description FROM student_mark WHERE mark_id = ?";
        return jdbcTemplate.queryForObject(sql, mapMarkFromDb(), markId);
    }

    @Override
    public int insertNewMark(Mark mark) {
        String sql = "INSERT INTO student_mark (student_id, lesson_id, mark, description) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, mark.getStudentId(), mark.getLessonId(), mark.getMark(), mark.getDescription());
    }

    @Override
    public int updateMarkById(Mark mark) {
        String sql = "UPDATE student_mark SET student_id = ?, lesson_id = ?, mark = ?, description = ? WHERE mark_id = ?";
        return jdbcTemplate.update(sql, mark.getStudentId(), mark.getLessonId(), mark.getMark(), mark.getDescription(), mark.getMarkId());
    }

    @Override
    public int deleteMarkById(int markId) {
        String sql = "DELETE FROM student_mark WHERE mark_id = ?";
        return jdbcTemplate.update(sql, markId);
    }

    private RowMapper<Mark> mapMarkFromDb() {
        return (resultSet, i) -> {
            int mark_id = resultSet.getInt("mark_id");
            int student_id = resultSet.getInt("student_id");
            int lesson_id = resultSet.getInt("lesson_id");
            int mark = resultSet.getInt("mark");
            String description = resultSet.getString("description");
            return new Mark(mark_id, student_id, lesson_id, mark, description);
        };
    }
}
