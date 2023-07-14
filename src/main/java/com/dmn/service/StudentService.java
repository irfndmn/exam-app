package com.dmn.service;

import com.dmn.dto.requests.StudentRequest;
import com.dmn.dto.response.StudentResponse;
import com.dmn.entity.Role;
import com.dmn.entity.Student;
import com.dmn.entity.enums.RoleType;
import com.dmn.exception.ResourceAlreadyFoundException;
import com.dmn.repository.StudentDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentDao studentDao;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;


    public void saveStudent(StudentRequest studentRequest) {

        if (studentDao.existsByEmail(studentRequest.getEmail())) {
            throw new ResourceAlreadyFoundException("Email is already exists");
        }

        Student newStd = new Student();

        newStd.setName(studentRequest.getName());
        newStd.setSurname(studentRequest.getSurname());
        newStd.setPhoneNumber(studentRequest.getPhoneNumber());
        newStd.setUsername(studentRequest.getUsername());
        newStd.setEmail(studentRequest.getEmail());
        String encodedPass=passwordEncoder.encode(studentRequest.getPassword());
        newStd.setPassword(encodedPass);/// you have to encode pass
        Role role1 = roleService.findRoleByType(RoleType.ROLE_STUDENT);
        newStd.setRole(role1);

        studentDao.save(newStd);

    }


    public List<StudentResponse> getAllStudents() {
        return studentDao.findAll().stream().map(StudentResponse::new).collect(Collectors.toList());
    }
}
