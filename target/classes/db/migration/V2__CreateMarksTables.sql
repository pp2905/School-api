CREATE TABLE IF NOT EXISTS lesson (
    lesson_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    short_name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS student_mark (
    mark_id SERIAL PRIMARY KEY,
    student_id INTEGER NOT NULL REFERENCES student (student_id),
    lesson_id INTEGER NOT NULL REFERENCES lesson (lesson_id),
    mark INTEGER CHECK (mark >= 0 AND mark <= 6) NOT NULL,
    description TEXT NOT NULL
);