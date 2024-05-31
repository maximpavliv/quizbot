-- Create quiz table
CREATE TABLE quiz (
    quiz_id SERIAL PRIMARY key,
    started BOOLEAN,
    current_exercise_idx INT
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


