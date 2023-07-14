package com.dmn.controller;
import com.dmn.entity.Question;
import com.dmn.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;


    @PreAuthorize("hasRole('TEACHER')")
    @GetMapping("/allQuestion")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @PreAuthorize("hasRole('TEACHER')")
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable("category") String category){
        return questionService.getAllQuestionByCategory(category);
    }


    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping("/new")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);

    }


}
