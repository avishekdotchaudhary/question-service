package com.iq.question.controller;


import com.iq.question.dao.QuestionDao;
import com.iq.question.model.Question;
import com.iq.question.model.QuestionWrapper;
import com.iq.question.model.Response;
import com.iq.question.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;
    @Autowired
    private QuestionDao questionDao;

    @GetMapping("allQuestions/")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category) {
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }

    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionForQuiZ(@RequestParam String categoryName, @RequestParam Integer numQuestions) {
        return questionService.getQuestionForQuiz(categoryName, numQuestions);
    }

    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsById(@RequestBody List<Integer> questionIds) {
        return questionService.getQuestionById(questionIds);
    }

    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses) {
        return questionService.getScore(responses);
    }

//    @DeleteMapping("delete/{id}")
//    public ResponseEntity<String> deleteQuestion(@PathVariable int id) {
//        return "Question deleted successfully";
//    }
//
//    @PutMapping("update")
//    public ResponseEntity<String> updateQuestion(@RequestBody Question question) {
//        return "Question updated successfully";
//    }
}
