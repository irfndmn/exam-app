package com.dmn.repository;
import com.dmn.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;


public interface StudentDao extends JpaRepository<Student,Long> {

    boolean existsByEmail(String email);


    Optional<Student> findByUsername(String username) throws UsernameNotFoundException;
}
