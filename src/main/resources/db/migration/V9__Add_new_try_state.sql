UPDATE user_session
SET state = CASE
    WHEN state = 'QUIZ_NOT_STARTED' THEN 'NEW_TRY'
    WHEN state = 'QUIZ_STARTED' THEN 'SOLVING_EXERCISES'
    ELSE state  -- Keep the current state if it does not match any condition
END;
