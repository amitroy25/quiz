package com.quiz.assignment.quiz.controller;

import com.quiz.assignment.quiz.Repository.QuestionRepository;
import com.quiz.assignment.quiz.Repository.QuizSessionRepository;
import com.quiz.assignment.quiz.entity.Questions;
import com.quiz.assignment.quiz.entity.QuizSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuizSessionRepository quizSessionRepository;

    // Start a new quiz session
    @PostMapping("/start")
    public ResponseEntity<QuizSession> startNewQuiz(@RequestParam Long userId) {
        QuizSession session = new QuizSession();
        session.setUserId(userId);
        session.setCorrectAnswers(0);
        session.setIncorrectAnswers(0);
        session.setQuestionsAnswered(0);
        quizSessionRepository.save(session);
        return ResponseEntity.ok(session);
    }

    // Get a random multiple-choice question
    @GetMapping("/question")
    public ResponseEntity<Questions> getRandomQuestion() {
        List<Questions> questions = questionRepository.findAll();
        if (questions.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Random random = new Random();
        Questions question = questions.get(random.nextInt(questions.size()));
        return ResponseEntity.ok(question);
    }

    // Submit an answer
    @PostMapping("/submit")
    public ResponseEntity<String> submitAnswer(@RequestParam Long sessionId, @RequestParam Long questionId, @RequestParam String userAnswer) {
        Optional<QuizSession> sessionOpt = quizSessionRepository.findById(sessionId);
        if (sessionOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid session ID.");
        }

        QuizSession session = sessionOpt.get();
        Optional<Questions> questionOpt = questionRepository.findById(questionId);
        if (questionOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid question ID.");
        }

        Questions question = questionOpt.get();
        session.setQuestionsAnswered(session.getQuestionsAnswered() + 1);

        if (question.getCorrectAnswer().equalsIgnoreCase(userAnswer)) {
            session.setCorrectAnswers(session.getCorrectAnswers() + 1);
        } else {
            session.setIncorrectAnswers(session.getIncorrectAnswers() + 1);
        }

        quizSessionRepository.save(session);
        return ResponseEntity.ok("Answer submitted successfully.");
    }

    // Get quiz session statistics
    @GetMapping("/stats")
    public ResponseEntity<QuizSession> getSessionStats(@RequestParam Long sessionId) {
        Optional<QuizSession> sessionOpt = quizSessionRepository.findById(sessionId);
        if (sessionOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(sessionOpt.get());
    }
}