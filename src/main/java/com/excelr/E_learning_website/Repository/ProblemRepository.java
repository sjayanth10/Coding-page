package com.excelr.E_learning_website.Repository;

import com.excelr.E_learning_website.Entity.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProblemRepository  extends JpaRepository<Problem, Long> {
    List<Problem> findByTopicname(String topicname);
    List<Problem> findByDifficultyLevel(String difficultyLevel);
    List<Problem> findByTopicnameAndDifficultyLevel(String topicname, String difficultyLevel);

}
