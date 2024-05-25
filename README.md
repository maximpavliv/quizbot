# QuizBot
QuizBot is a Telegram bot that tests your Java skills. You can try it out [here](https://telegram.me/kwizzzzbot).

The bot asks multiple choice questions, saves your answer, and gives you your score when the quiz is finished

### Project description
This project demonstrates basic Back-End and Deployment features:
- The project is based on Spring Boot.
- Each user has its own session, sessions are deleted if inactive for a certain amount of time.
- The project is deployed to Heroku.
- The project includes some integration tests.
- The project is automatically tested using Github Actions before being deployed (CI/CD), the tests need to pass in order to allow deployment.
- The application is backed by a PostgreSQL database, that contains all questions, choices, and correct answers.
- The database also stores the sessions with their respective quizes, so that users are not affected after new deployments.
- Quizes are created by randomly picking 5 questions from the database, possible choices are shuffled.
- No pushes to the remote main branch are allowed, new features must be merged via pull requests. The pull requests are tested, merging is impossible if tests fail.
- Questions can contain code snippets.
- ...
