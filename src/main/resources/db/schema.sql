-- Create Question table
CREATE TABLE Question (
    question_id SERIAL PRIMARY KEY,
    question_text TEXT NOT NULL
);
-- Create Choice table
CREATE TABLE Choice (
    choice_id SERIAL PRIMARY KEY,
    question_id INT NOT NULL,
    choice_text TEXT NOT NULL,
    FOREIGN KEY (question_id) REFERENCES Question(question_id) ON DELETE CASCADE
);
-- Create Answer table
CREATE TABLE Answer (
    answer_id SERIAL PRIMARY KEY,
    question_id INT NOT NULL,
    choice_id INT NOT NULL,
    FOREIGN KEY (question_id) REFERENCES Question(question_id) ON DELETE CASCADE,
    FOREIGN KEY (choice_id) REFERENCES Choice(choice_id) ON DELETE CASCADE
);
-- Create Session table
CREATE TABLE user_session (
    session_id SERIAL PRIMARY KEY,
    chat_id BIGINT,
    last_sent_message_id INTEGER,
    last_activity_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    started BOOLEAN
);
-- Create Quiz table
create table quiz (
    quiz_id SERIAL PRIMARY key,
    session_id BIGINT UNIQUE,
    started BOOLEAN,
    current_exercise_idx INT,
    foreign key (session_id) references user_session(session_id) on delete CASCADE
);
-- Create QuizExercise table
CREATE TABLE quiz_exercise (
    quiz_exercise_id SERIAL PRIMARY KEY,
    quiz_id BIGINT,
    question_id BIGINT,
    idx INT,
    FOREIGN KEY (quiz_id) REFERENCES quiz(quiz_id) ON DELETE CASCADE,
    FOREIGN KEY (question_id) REFERENCES question(question_id) ON DELETE CASCADE
);
-- Create QuizExerciseChoice table
CREATE TABLE quiz_exercise_choice (
    quiz_exercise_choice_id SERIAL PRIMARY KEY,
    quiz_exercise_id BIGINT,
    choice_id BIGINT,
    idx INTEGER,
    FOREIGN KEY (quiz_exercise_id) REFERENCES quiz_exercise(quiz_exercise_id) ON DELETE CASCADE,
    FOREIGN KEY (choice_id) REFERENCES choice(choice_id) ON DELETE CASCADE
);
-- Add reference to QuizExerciseChoice in QuizExercise
ALTER TABLE quiz_exercise
ADD COLUMN user_quiz_exercise_choice_id BIGINT;
ALTER TABLE quiz_exercise
ADD CONSTRAINT fk_user_quiz_exercise_choice_id
FOREIGN KEY (user_quiz_exercise_choice_id)
REFERENCES quiz_exercise_choice(quiz_exercise_choice_id)
ON DELETE CASCADE;

