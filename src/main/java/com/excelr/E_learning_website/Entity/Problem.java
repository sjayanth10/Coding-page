package com.excelr.E_learning_website.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;
import org.springframework.web.bind.annotation.CrossOrigin;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@CrossOrigin(origins = "http://localhost:3000")
public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Language is required.")
    private String language;
    @NotBlank(message = "Topic name is required.")
    private String topicname;
    @NotBlank(message = "Name is required.")
    private String name;
    @NotBlank(message = "LeetCode link is required.")
    @URL(message = "Invalid LeetCode link.")
    private String leetcodeLink;
    @NotBlank(message = "Tutorial link is required.")
    @URL(message = "Invalid tutorial link.")
    private String tutorialLink;
    @NotBlank(message = "Difficulty level is required.")
    @Pattern(regexp = "Easy|Medium|Hard", message = "Invalid difficulty level. Must be Easy, Medium, or Hard.")
    private String difficultyLevel;

    @NotBlank(message = "Steps to approach are required.")
    @Size(min = 0, max = 20000 )
    private String stepsToApproach;



}
