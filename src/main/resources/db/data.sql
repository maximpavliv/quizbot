\copy question (question_text) FROM './src/main/resources/db/quizbot_questions.csv' CSV;
\copy snippet (snippet_language, snippet_text, question_id) FROM './src/main/resources/db/quizbot_snippets.csv' CSV;
\copy choice (question_id, choice_text) FROM './src/main/resources/db/quizbot_choices.csv' CSV;
\copy answer (question_id, choice_id) FROM './src/main/resources/db/quizbot_answers.csv' CSV;

