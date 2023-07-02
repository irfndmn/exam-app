package com.dmn.repository;

import com.dmn.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question,Integer> {
    List<Question> findByCategory(String category);


    @Query(value = "SELECT * FROM question q WHERE q.category=:category ORDER BY RANDOM() LIMIT :numOfQuestion", nativeQuery = true)
   // @Query("SELECT q FROM Question q WHERE q.category=?1 LIMIT()")
    List<Question> findRandomQuestionByCategory(String category, Integer numOfQuestion);
}
