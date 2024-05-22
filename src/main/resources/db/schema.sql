-- Create question table
CREATE TABLE question (
    question_id SERIAL PRIMARY KEY,
    question_text TEXT NOT NULL
);
-- Create snippet table
CREATE TABLE snippet (
    snippet_id SERIAL PRIMARY KEY,
    snippet_language VARCHAR(30),
    snippet_text text NOT NULL,
    question_id INT NOT NULL UNIQUE,
    FOREIGN KEY (question_id) REFERENCES question(question_id) ON DELETE CASCADE
);
-- Create choice table
CREATE TABLE choice (
    choice_id SERIAL PRIMARY KEY,
    question_id INT NOT NULL,
    choice_text TEXT NOT NULL,
    FOREIGN KEY (question_id) REFERENCES question(question_id) ON DELETE CASCADE
);
-- Create answer table
CREATE TABLE answer (
    answer_id SERIAL PRIMARY KEY,
    question_id INT NOT NULL UNIQUE,
    choice_id INT NOT NULL UNIQUE,
    FOREIGN KEY (question_id) REFERENCES question(question_id) ON DELETE CASCADE,
    FOREIGN KEY (choice_id) REFERENCES choice(choice_id) ON DELETE CASCADE
);
-- Create user_session table
CREATE TABLE user_session (
    session_id SERIAL PRIMARY KEY,
    chat_id BIGINT,
    last_sent_message_id INTEGER,
    last_activity_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    started BOOLEAN
);
-- Create quiz table
CREATE TABLE quiz (
    quiz_id SERIAL PRIMARY key,
    session_id BIGINT UNIQUE,
    started BOOLEAN,
    current_exercise_idx INT,
    FOREIGN KEY (session_id) REFERENCES user_session(session_id) ON DELETE CASCADE
);
-- Create quiz_exercise table
CREATE TABLE quiz_exercise (
    quiz_exercise_id SERIAL PRIMARY KEY,
    quiz_id BIGINT,
    question_id BIGINT,
    idx INT,
    FOREIGN KEY (quiz_id) REFERENCES quiz(quiz_id) ON DELETE CASCADE,
    FOREIGN KEY (question_id) REFERENCES question(question_id) ON DELETE CASCADE
);
-- Create quiz_exercise_choice table
CREATE TABLE quiz_exercise_choice (
    quiz_exercise_choice_id SERIAL PRIMARY KEY,
    quiz_exercise_id BIGINT,
    choice_id BIGINT,
    idx INTEGER,
    FOREIGN KEY (quiz_exercise_id) REFERENCES quiz_exercise(quiz_exercise_id) ON DELETE CASCADE,
    FOREIGN KEY (choice_id) REFERENCES choice(choice_id) ON DELETE CASCADE
);
-- Add reference to quiz_exercise_choice in quiz_exercise
ALTER TABLE quiz_exercise
ADD COLUMN user_quiz_exercise_choice_id BIGINT;
ALTER TABLE quiz_exercise
ADD CONSTRAINT fk_user_quiz_exercise_choice_id
FOREIGN KEY (user_quiz_exercise_choice_id)
REFERENCES quiz_exercise_choice(quiz_exercise_choice_id)
ON DELETE CASCADE;

