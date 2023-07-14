package com.dmn.repository;

import com.dmn.entity.Teacher;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public interface TeacherDao extends JpaRepository<Teacher,Long> {
    boolean existsByEmail(String email);


    Optional<Teacher> findByUsername(String username) throws UsernameNotFoundException;
}
