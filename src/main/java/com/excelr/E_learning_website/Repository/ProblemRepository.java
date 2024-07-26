package com.excelr.E_learning_website.Repository;

import com.excelr.E_learning_website.Entity.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemRepository  extends JpaRepository<Problem, Long> {
}
