package com.Quiz.QuizApp.repo;

import com.Quiz.QuizApp.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {
    List<Question> findByCatagory(String catagory);
    @Query(value = "SELECT * FROM question q WHERE q.catagory = :catagory ORDER BY RAND() LIMIT :numofquestions", nativeQuery = true)
    List<Question> findRandomExamByCatagory(String catagory, Integer numofquestions);

}
