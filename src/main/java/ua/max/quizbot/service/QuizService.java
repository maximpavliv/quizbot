package ua.max.quizbot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.max.quizbot.model.Question;
import ua.max.quizbot.model.Quiz;
import ua.max.quizbot.model.QuizExercise;
import ua.max.quizbot.model.Session;
import ua.max.quizbot.record.Exercise;
import ua.max.quizbot.record.QuizResults;
import ua.max.quizbot.repository.QuestionRepository;
import ua.max.quizbot.repository.QuizRepository;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.SequencedMap;

@Service
public class QuizService {
    public final static int MAX_QUIZ_LENGTH = 50;

    private final QuizRepository quizRepository;

    private final QuestionRepository questionRepository;

    private final QuizExerciseService quizExerciseService;


    @Autowired
    public QuizService(QuizRepository quizRepository,
                       QuestionRepository questionRepository,
                       QuizExerciseService quizExerciseService) {
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
        this.quizExerciseService = quizExerciseService;
    }

    @Transactional
    public Quiz createQuiz(Session session, int quizLength) {
        List<Long> allQuestionsIds = questionRepository.findAllIds();

        if (quizLength < 1 || quizLength > allQuestionsIds.size()){
            throw new RuntimeException("Invalid quiz length");
        }

        Quiz newQuiz = new Quiz();
        quizRepository.save(newQuiz);

        newQuiz.setSession(session);

        Collections.shuffle(allQuestionsIds);
        List<Question> questions = allQuestionsIds.subList(0, quizLength).stream()
                .map(questionRepository::getReferenceById).toList();

        List<QuizExercise> newQuizExercises = questions.stream()
                .map(question -> quizExerciseService.createQuizExercise(newQuiz, question, questions.indexOf(question)))
                .toList();

        newQuiz.setQuizExercises(newQuizExercises);
        newQuiz.setCurrentExerciseIdx(0);

        return newQuiz;
    }

    @Transactional
    public void deleteQuiz(Long quizId) {
        quizRepository.deleteById(quizId);
    }

    @Transactional
    public Exercise getCurrentExercise(Long quizId) {
        QuizExercise currentQuizExercise = getCurrentQuizExercise(quizId);
        return quizExerciseService.getExercise(currentQuizExercise.getQuizExerciseId());
    }

    @Transactional
    public void saveAnswerAndSwitchToNextExercise(Long quizId, int userCallbackData) {
        QuizExercise currentQuizExercise = getCurrentQuizExercise(quizId);
        quizExerciseService.saveUserAnswer(currentQuizExercise.getQuizExerciseId(), userCallbackData);

        Quiz quiz = quizRepository.getReferenceById(quizId);
        quiz.setCurrentExerciseIdx(quiz.getCurrentExerciseIdx() + 1);
    }

    @Transactional
    public boolean quizIsFinished(Long quizId) {
        Quiz quiz = quizRepository.getReferenceById(quizId);
        return (quiz.getCurrentExerciseIdx() >= quiz.getQuizExercises().size());
    }

    @Transactional
    public QuizResults getResults(Long quizId) {

        if (!quizIsFinished(quizId))
            return null;

        Quiz quiz = quizRepository.getReferenceById(quizId);
        List<QuizExercise> quizExercises = quiz.getQuizExercises().stream()
                .map(QuizExercise::getQuizExerciseId)
                .map(quizExerciseService::findQuizExerciseById)
                .toList();

        int numberQuestions = quizExercises.size();
        long correctAnswersCount = quizExercises.stream()
                .filter(quizExercise -> quizExerciseService.getUserChoice(quizExercise.getQuizExerciseId())
                        .equals(quizExerciseService.getCorrectChoice(quizExercise.getQuizExerciseId())))
                .count();
        String score = correctAnswersCount + "/" + numberQuestions;

        SequencedMap<Integer, String> corrections = new LinkedHashMap<>();
        quizExercises.stream()
                .filter(quizExercise -> !quizExerciseService.getUserChoice(quizExercise.getQuizExerciseId())
                        .equals(quizExerciseService.getCorrectChoice(quizExercise.getQuizExerciseId())))
                .forEach(quizExercise -> {
                            corrections.put(quizExercise.getIdx() + 1,
                                    quizExercise.getQuestion().getAnswer().getChoice().getChoiceText());
                        });

        return new QuizResults(score, corrections);

    }

    @Transactional
    public Quiz findQuizById(Long quizId) {
        return quizRepository.getReferenceById(quizId);
    }

    @Transactional
    private QuizExercise getCurrentQuizExercise(Long quizId) {
        Quiz quiz = quizRepository.getReferenceById(quizId);
        return quiz.getQuizExercises().get(quiz.getCurrentExerciseIdx());
    }
}
