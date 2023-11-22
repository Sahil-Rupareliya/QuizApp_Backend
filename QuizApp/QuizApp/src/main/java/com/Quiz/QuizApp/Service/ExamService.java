package com.Quiz.QuizApp.Service;

import com.Quiz.QuizApp.Model.Exam;
import com.Quiz.QuizApp.Model.Question;
import com.Quiz.QuizApp.Model.QuestionWrapper;
import com.Quiz.QuizApp.Model.Response;
import com.Quiz.QuizApp.repo.ExamRepo;
import com.Quiz.QuizApp.repo.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExamService {

    @Autowired
    QuestionDao questionDao;

    @Autowired
    ExamRepo examRepo;

    public ResponseEntity<String> createExam(String catagory, Integer numofquestions, String title) {
        List<Question> list= questionDao.findRandomExamByCatagory(catagory, numofquestions);

        Exam exam = new Exam();
        exam.setTitle(title);
        exam.setList(list);

        examRepo.save(exam);


        return new ResponseEntity<>("Success", HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionQuiz(Integer id) {
        Optional<Exam> questionList = examRepo.findById(id);
        List<Question> questions = questionList.get().getList();
        List<QuestionWrapper> questionWrappers = new ArrayList<>();

        for(Question Q : questions){
            QuestionWrapper qw = new QuestionWrapper(Q.getId(),Q.getQuestionTitle(),Q.getOption1(),Q.getOption2(),Q.getOption3(),Q.getOption4());
            questionWrappers.add(qw);
        }

        return new ResponseEntity<>(questionWrappers,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Exam exam = examRepo.findById(id).get();
        List<Question> questions = exam.getList();
        int right = 0;
        int i=0;
        for (Response response : responses){
            if(response.getResponse().equals(questions.get(i).getRightAnswer()))right++;
        }

        return new ResponseEntity<>(right, HttpStatus.OK);
    }


    public ResponseEntity<String> deleteQuiz(Integer id) {
        examRepo.deleteById(id);
        return new ResponseEntity<>("Success",HttpStatus.OK);
    }
}
