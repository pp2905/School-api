package com.patryk.school.dao;

import com.patryk.school.model.Student;
import com.patryk.school.model.StudentLesson;
import com.patryk.school.model.StudentMark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("studentDao")
public class StudentDao implements IStudentDao {
    
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StudentDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Student>selectAllStudents() {
        String sql = "SELECT student_id, first_name, last_name, email, age, gender FROM student ORDER BY student_id";
        return jdbcTemplate.query(sql, mapStudentFromDb());
    }

    @Override
    public Student selectStudentById(int studentId) {
        String sql = "SELECT student_id, first_name, last_name, email, age, gender FROM student where student_id = ?";
        return jdbcTemplate.queryForObject(sql, mapStudentFromDb(), studentId);
    }

    @Override
    public List<StudentMark> selectStudentMarksByStudentId(int studentId) {
        String sql = "SELECT student.student_id, student.first_name, student.last_name, student_mark.mark_id, student_mark.mark, lesson.lesson_id, lesson.name, student_mark.description FROM student JOIN student_mark USING (student_id) JOIN lesson USING (lesson_id) WHERE student_id = ?;";
        return jdbcTemplate.query(sql, mapStudentMarksFromDb(), studentId);
    }

    @Override
    public List<StudentLesson> selectLessonsByStudentId(int studentId) {
        String sql = "SELECT student_lesson.studentlesson_id, student_lesson.student_id, student.first_name, student.last_name, student_lesson.lesson_id, lesson.name, student_lesson.start_date, student_lesson.end_date FROM student JOIN student_lesson USING (student_id) JOIN lesson USING (lesson_id) WHERE student_id = ?;";
        return jdbcTemplate.query(sql, mapStudentLessonsFromDb(), studentId);
    }

    @Override
    public int insertNewStudent(Student student) {
        String sql = "INSERT INTO student (first_name, last_name, email, age, gender) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, student.getFirstName(), student.getLastName(), student.getEmail(), student.getAge(), student.getGender().name().toUpperCase());
    }

    @Override
    public int updateStudentById(Student student) {
        String sql = "UPDATE student SET first_name = ?, last_name = ?, email = ?, age = ?, gender = ? WHERE student_id = ?";
        return jdbcTemplate.update(sql, student.getFirstName(), student.getLastName(), student.getEmail(), student.getAge(), student.getGender().name().toUpperCase(), student.getStudentId());
    }

    @Override
    public int deleteStudentById(int studentId) {
        String sql = "DELETE FROM student WHERE student_id = ?";
        return jdbcTemplate.update(sql, studentId);
    }

    private RowMapper<Student> mapStudentFromDb() {
        return (resultSet, i) -> {
            int student_id = resultSet.getInt("student_id");
            String first_name = resultSet.getString("first_name");
            String last_name = resultSet.getString("last_name");
            String email = resultSet.getString("email");
            int age = resultSet.getInt("age");
            String genderStr = resultSet.getString("gender").toUpperCase();
            Student.Gender gender = Student.Gender.valueOf(genderStr);
            return new Student(student_id, first_name, last_name, email, age, gender);
        };
    }

    private RowMapper<StudentMark> mapStudentMarksFromDb() {
        return (resultSet, i) -> {
            int student_id = resultSet.getInt("student_id");
            String student_first_name = resultSet.getString("first_name");
            String student_last_name = resultSet.getString("last_name");
            int mark_id = resultSet.getInt("mark_id");
            int mark = resultSet.getInt("mark");
            int lesson_id = resultSet.getInt("lesson_id");
            String lesson_name = resultSet.getString("name");
            String mark_description = resultSet.getString("description");
            return new StudentMark(student_id, student_first_name, student_last_name, mark_id, mark, lesson_id, lesson_name, mark_description);
        };
    }

    private RowMapper<StudentLesson> mapStudentLessonsFromDb() {
        return (resultSet, i) ->
            new StudentLesson(
                    resultSet.getInt("studentlesson_id"),
                    resultSet.getInt("student_id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getInt("lesson_id"),
                    resultSet.getString("name"),
                    resultSet.getDate("start_date").toLocalDate(),
                    resultSet.getDate("end_date").toLocalDate()
            );
    }
}
