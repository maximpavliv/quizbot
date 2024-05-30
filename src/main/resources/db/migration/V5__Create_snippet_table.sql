-- Create snippet table
CREATE TABLE snippet (
    snippet_id INT PRIMARY KEY,
    snippet_language VARCHAR(30),
    snippet_text text NOT NULL,
    question_id INT NOT NULL UNIQUE,
    FOREIGN KEY (question_id) REFERENCES question(question_id) ON DELETE CASCADE
);

UPDATE question
SET question_text = 'In Java, Arrays are \_\_\_.'
WHERE question_id = 7;

