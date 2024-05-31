-- Create user_session table
CREATE TABLE user_session (
    session_id SERIAL PRIMARY KEY,
    chat_id BIGINT,
    last_sent_message_id INTEGER,
    last_activity_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE quiz
ADD COLUMN session_id BIGINT UNIQUE;
ALTER TABLE quiz
ADD CONSTRAINT fk_session_id
FOREIGN KEY (session_id)
REFERENCES user_session(session_id)
ON DELETE CASCADE;

