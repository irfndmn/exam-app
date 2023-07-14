package com.dmn.service;

import com.dmn.entity.Teacher;
import com.dmn.entity.User;
import com.dmn.repository.StudentDao;
import com.dmn.repository.TeacherDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final TeacherDao teacherDao;

    private final StudentDao studentDao;


    public User findUserByUsername(String username) {
        Optional<Teacher> teacherUser = teacherDao.findByUsername(username);
        if (teacherUser.isPresent()) {
            return teacherUser.get();
        }
        return studentDao.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User doesn't exist"));
    }

}
