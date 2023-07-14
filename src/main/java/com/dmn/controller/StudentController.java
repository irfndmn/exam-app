package com.dmn.controller;

import com.dmn.dto.requests.StudentRequest;
import com.dmn.dto.response.StudentResponse;
import com.dmn.service.StudentService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<StudentResponse>> getAllStudents(/*@RequestParam(value = "page",required = false, defaultValue = "0") int page,
                                                                @RequestParam(value = "size",required = false, defaultValue = "10") int size,
                                                                @RequestParam(value = "sort",required = false,defaultValue = "name") String prop,
                                                                @RequestParam(value = "direction",required = false, defaultValue = "ASC")Sort.Direction direction*/){

        List<StudentResponse> studentResponseList= studentService.getAllStudents(/*page,size,prop,direction*/);

         return new ResponseEntity<>(studentResponseList,HttpStatus.OK);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/new")
    public ResponseEntity<String> saveStudent(@Valid @RequestBody StudentRequest studentRequest){

        studentService.saveStudent(studentRequest);
        return new ResponseEntity<>("Student has saved successfully...", HttpStatus.CREATED);

    }


    @GetMapping("/welcome")
    public String welcome(){
        return "WELCOME TO MY PAGE";
    }


}
