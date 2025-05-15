package com.iq.question.service;

import com.iq.question.dao.QuestionDao;
import com.iq.question.model.Question;
import com.iq.question.model.QuestionWrapper;
import com.iq.question.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try{
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try{
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Question question) {

        try{
            questionDao.save(question);
            return new ResponseEntity<>("Question added successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<List<Integer>> getQuestionForQuiz(String categoryName, Integer numQuestions) {
        List<Integer> questions = questionDao.findRandomQuestionsByCategory(categoryName, numQuestions);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionById(List<Integer> questionIds) {

        List<QuestionWrapper> wrappers = new ArrayList<>();

        List<Question> questions = new ArrayList<>();

        for(Integer questionId : questionIds) {
            questions.add(questionDao.findById(questionId).get());
        }

        for(Question question : questions) {
            QuestionWrapper questionWrapper = new QuestionWrapper();
            questionWrapper.setId(question.getId());
            questionWrapper.setQuestionTitle(question.getQuestionTitle());
            questionWrapper.setOption1(question.getOption1());
            questionWrapper.setOption2(question.getOption2());
            questionWrapper.setOption3(question.getOption3());
            questionWrapper.setOption4(question.getOption4());
            wrappers.add(questionWrapper);
        }
        return new ResponseEntity<>(wrappers, HttpStatus.OK);

    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {
        int right = 0;
        for(Response response : responses) {
            Question question = questionDao.findById(response.getId()).get();
            if(response.getResponse().equals(question.getCorrectAnswer()))
                right++;
        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
