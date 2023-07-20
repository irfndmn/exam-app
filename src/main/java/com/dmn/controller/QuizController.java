package com.dmn.controller;
import com.dmn.dto.response.QuestionResponse;
import com.dmn.entity.Answer;
import com.dmn.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("quiz")
@RequiredArgsConstructor
public class QuizController {


    private final QuizService quizService;

    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping("create")
    public ResponseEntity<String> createQuize(@RequestParam("category") String category,
                                              @RequestParam("numOfQuestion")Integer numOfQuestion,
                                              @RequestParam("title") String title){
        return quizService.createQuiz(category,numOfQuestion,title);
    }


    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionResponse>> getQuizQueById(@PathVariable("id") Integer id){
        return quizService.getQuizQuestionById(id);
    }

    @PreAuthorize("hasRole('STUDENT')")
    @PostMapping("submit/{id}")
    public ResponseEntity<Map<String,Integer>> submitGetTheResult(@PathVariable("id") Integer id, @RequestBody List<Answer> answers){
        return quizService.getResultToUser(id,answers);
    }



}
