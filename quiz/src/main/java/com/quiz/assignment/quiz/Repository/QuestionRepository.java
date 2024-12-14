package com.quiz.assignment.quiz.Repository;

import com.quiz.assignment.quiz.entity.Questions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Questions, Long> {}
