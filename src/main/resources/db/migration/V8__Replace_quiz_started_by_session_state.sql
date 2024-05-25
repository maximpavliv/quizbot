ALTER TABLE user_session
ADD COLUMN state VARCHAR(30);

UPDATE user_session
SET state = CASE
    WHEN q.started = true THEN 'QUIZ_STARTED'
    WHEN q.started = false THEN 'QUIZ_NOT_STARTED'
END
FROM quiz q
WHERE user_session.session_id = q.session_id;

ALTER TABLE quiz
DROP COLUMN started;
