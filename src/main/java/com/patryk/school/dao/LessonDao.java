package com.patryk.school.dao;

import com.patryk.school.model.Lesson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("lessonDao")
public class LessonDao implements ILessonDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LessonDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Lesson> selectAllLessons() {
        String sql = "SELECT lesson_id, name, short_name FROM lesson ORDER BY lesson_id";
        return jdbcTemplate.query(sql, mapLessonFromDb());
    }

    @Override
    public Lesson selectLessonById(int lessonId) {
        String sql = "SELECT lesson_id, name, short_name FROM lesson WHERE lesson_id = ? ORDER BY lesson_id";
        return jdbcTemplate.queryForObject(sql, mapLessonFromDb(), lessonId);
    }

    @Override
    public int insertNewLesson(Lesson lesson) {
        String sql = "INSERT INTO lesson (name, short_name) VALUES (?, ?)";
        return jdbcTemplate.update(sql, lesson.getName(), lesson.getShortName());
    }

    @Override
    public int updateLessonById(Lesson lesson) {
        String sql = "UPDATE lesson SET name = ?, short_name = ? WHERE lesson_id = ?";
        return jdbcTemplate.update(sql, lesson.getName(), lesson.getShortName(), lesson.getLessonId());
    }

    @Override
    public int deleteLessonById(int lessonId) {
        String sql = "DELETE FROM lesson WHERE lesson_id = ?";
        return jdbcTemplate.update(sql, lessonId);
    }
    
    RowMapper<Lesson> mapLessonFromDb() {
        return (resultSet, i) -> {
            int student_id = resultSet.getInt("lesson_id");
            String name = resultSet.getString("name");
            String short_name = resultSet.getString("short_name");
            return new Lesson(student_id, name, short_name);
        };
    }
}
