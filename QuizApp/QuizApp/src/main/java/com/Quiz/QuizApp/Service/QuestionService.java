package com.Quiz.QuizApp.Service;

import com.Quiz.QuizApp.Model.Exam;
import com.Quiz.QuizApp.Model.Question;
import com.Quiz.QuizApp.repo.ExamRepo;
import com.Quiz.QuizApp.repo.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao repo;

    @Autowired
    ExamRepo examRepo;

    public ResponseEntity<List<Question>> getAll() {
        try {
            return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionsByCatagory(String catagory) {
        try {
            return new ResponseEntity<>(repo.findByCatagory(catagory), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        try {
            repo.save(question);
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<String> deleteQuestion(Integer id) {
        for(Exam e1 : examRepo.findAll()){
            for(Question q: e1.getList()){
                if(q.getId() == id){
                    examRepo.delete(e1);
                    break;
                }
            }
        }
        repo.deleteById(id);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    public ResponseEntity<Question> updateQuestion(Question question) {
        return new ResponseEntity<>(repo.save(question),HttpStatus.OK);
    }
}
