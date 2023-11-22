package com.Quiz.QuizApp.repo;

import com.Quiz.QuizApp.Model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepo extends JpaRepository<Exam, Integer> {
}
