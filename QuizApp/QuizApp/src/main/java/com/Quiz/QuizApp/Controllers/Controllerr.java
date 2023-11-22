package com.Quiz.QuizApp.Controllers;

import com.Quiz.QuizApp.Model.QuestionWrapper;
import com.Quiz.QuizApp.Model.Response;
import com.Quiz.QuizApp.Service.ExamService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class Controllerr {

    @Autowired
    ExamService examService;

    @PostMapping("/create")
    public ResponseEntity<String> createExam(@RequestParam String catagory, @RequestParam Integer numofquestions, @RequestParam String title){
        return examService.createExam(catagory,numofquestions,title);

    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuestionQuiz(@PathVariable Integer id){
        return examService.getQuestionQuiz(id);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses){
        return examService.calculateResult(id, responses);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteQuiz(@PathVariable Integer id){
        return examService.deleteQuiz(id);
    }



}

