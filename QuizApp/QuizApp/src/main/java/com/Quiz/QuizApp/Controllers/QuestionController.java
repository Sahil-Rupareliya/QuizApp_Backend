package com.Quiz.QuizApp.Controllers;

import com.Quiz.QuizApp.Model.Question;
import com.Quiz.QuizApp.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping("questions")
public class QuestionController {

    @Autowired
    QuestionService service;


    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAll(){
        return service.getAll();
    }

    @GetMapping("catagory/{catagory}")
    public ResponseEntity<List<Question>> getQuestionsByCatagory(@PathVariable String catagory){
        return service.getQuestionsByCatagory(catagory);
    }

    @PostMapping("add")
    public ResponseEntity<String>  addQuestion(@RequestBody Question question){
        return service.addQuestion(question);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Integer id){
        return service.deleteQuestion(id);
    }

    @PutMapping("/update")
    public ResponseEntity<Question> updateQuestion(@RequestBody Question question){
        return service.updateQuestion(question);
    }


}
