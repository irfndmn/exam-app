package com.dmn.controller;

import com.dmn.dto.QuestionResponse;
import com.dmn.entity.Answer;
import com.dmn.entity.Quiz;
import com.dmn.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuize(@RequestParam("category") String category,
                                              @RequestParam("numOfQuestion")Integer numOfQuestion,
                                              @RequestParam("title") String title){
        return quizService.createQuiz(category,numOfQuestion,title);

    }


    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionResponse>> getQuizQueById(@PathVariable("id") Integer id){
        return quizService.getQuizQuestionById(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Map<String,Integer>> submitGetTheResult(@PathVariable("id") Integer id, @RequestBody List<Answer> answers){
        return quizService.getResultToUser(id,answers);
    }



}
