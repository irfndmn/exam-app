package com.dmn.service;

import com.dmn.dto.requests.TeacherRequest;
import com.dmn.dto.response.TeacherResponse;
import com.dmn.entity.Role;
import com.dmn.entity.Student;
import com.dmn.entity.Teacher;
import com.dmn.entity.enums.RoleType;
import com.dmn.exception.ResourceAlreadyFoundException;
import com.dmn.repository.TeacherDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherDao teacherDao;

    private final RoleService roleService;


    private final PasswordEncoder passwordEncoder;

    public void saveTeacher(TeacherRequest teacherRequest) {
        if (teacherDao.existsByEmail(teacherRequest.getEmail())) {
            throw new ResourceAlreadyFoundException("Email is already exists");
        }

        Teacher newT = new Teacher();

        newT.setName(teacherRequest.getName());
        newT.setSurname(teacherRequest.getSurname());
        newT.setPhoneNumber(teacherRequest.getPhoneNumber());
        newT.setUsername(teacherRequest.getUsername());
        newT.setEmail(teacherRequest.getEmail());
        String encodePsw=passwordEncoder.encode(teacherRequest.getPassword());
        newT.setPassword(encodePsw);/// you have to encode pass
        Role role1 = roleService.findRoleByType(RoleType.ROLE_TEACHER);
        Role role2=roleService.findRoleByType(RoleType.ROLE_ADMIN);
        Set<Role> roles=new HashSet<>();
        roles.add(role1);
        roles.add(role2);
        newT.setUserRole(roles);
        teacherDao.save(newT);
    }

    public List<TeacherResponse> getAllTeachers() {
        return teacherDao.findAll().stream().map(TeacherResponse::new).collect(Collectors.toList());
    }
}
