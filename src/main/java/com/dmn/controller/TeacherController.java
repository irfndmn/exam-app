package com.dmn.controller;
import com.dmn.dto.requests.TeacherRequest;
import com.dmn.dto.response.TeacherResponse;
import com.dmn.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController {


    private final TeacherService teacherService;


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<TeacherResponse>> getAllTeachers(){

        List<TeacherResponse> teacherResponses= teacherService.getAllTeachers();

        return new ResponseEntity<>(teacherResponses,HttpStatus.OK);

    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/new")
    public ResponseEntity<String> saveTeacher(@Valid @RequestBody TeacherRequest teacherRequest){

        teacherService.saveTeacher(teacherRequest);
        return new ResponseEntity<>("Teacher has saved successfully...", HttpStatus.CREATED);

    }


}
