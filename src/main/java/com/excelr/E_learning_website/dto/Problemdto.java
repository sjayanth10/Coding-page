package com.excelr.E_learning_website.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Problemdto {

    private String language;
    private String topicname;
    private String name;
    private String leetcodeLink;
    private String tutorialLink;
    private String difficultyLevel;
    private String stepsToApproach;
}
