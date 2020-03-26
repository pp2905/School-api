CREATE TABLE IF NOT EXISTS student_lesson (
    studentlesson_id SERIAL PRIMARY KEY,
    student_id INTEGER NOT NULL REFERENCES student (student_id),
    lesson_id INTEGER NOT NULL REFERENCES lesson (lesson_id),
    start_date DATE NOT NULL,
    end_date DATE NOT NULL
);