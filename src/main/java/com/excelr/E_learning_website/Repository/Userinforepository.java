package com.excelr.E_learning_website.Repository;



import com.excelr.E_learning_website.Entity.Userinfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Userinforepository extends JpaRepository<Userinfo, Integer> {
    Optional<Userinfo> findByName(String username);
}
