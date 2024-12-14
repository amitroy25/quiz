package com.quiz.assignment.quiz;

import com.quiz.assignment.quiz.Repository.QuestionRepository;
import com.quiz.assignment.quiz.entity.Questions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class QuizApplication implements CommandLineRunner {

	@Autowired
	private QuestionRepository questionRepository;

	public static void main(String[] args) {
		SpringApplication.run(QuizApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Seed some sample questions
		List<Questions> questions = new ArrayList<>();
		questions.add(new Questions(null, "What is the capital of France?", "Paris", "London", "Rome", "Berlin", "A"));
		questions.add(new Questions(null, "What is 2 + 2?", "3", "4", "5", "6", "B"));
		questions.add(new Questions(null, "Which programming language is used for Android apps?", "Java", "Python", "C++", "Ruby", "A"));
		questionRepository.saveAll(questions);
	}
}
