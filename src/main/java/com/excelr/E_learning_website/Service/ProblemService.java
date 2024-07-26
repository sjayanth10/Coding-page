package com.excelr.E_learning_website.Service;

import com.excelr.E_learning_website.Entity.Problem;
import com.excelr.E_learning_website.Repository.ProblemRepository;
import com.excelr.E_learning_website.dto.Problemdto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ProblemService {
    @Autowired
    private ProblemRepository problemRepository;

    public Optional<Problem> getProblemBYId(Long problemId) {
        return problemRepository.findById(problemId);
    }

    public Problem createProblem(Problemdto problemDto) {
        Problem problem = new Problem();
        problem.setId(problem.getId());
        problem.setLanguage(problemDto.getLanguage());
        problem.setTopicname(problemDto.getTopicname());
        problem.setName(problemDto.getName());
        problem.setLeetcodeLink(problemDto.getLeetcodeLink());
        problem.setTutorialLink(problemDto.getTutorialLink());
        problem.setDifficultyLevel(problemDto.getDifficultyLevel());
        problem.setStepsToApproach(problemDto.getStepsToApproach());
        return problemRepository.save(problem);
    }

    public Problem updateProblem(Long problemId, Problem problemDetails) {
        Problem problem = problemRepository.findById(problemId).orElseThrow(() -> new RuntimeException("Problem not found"));
        problem.setLanguage(problemDetails.getLanguage());
        problem.setName(problemDetails.getName());
        problem.setLeetcodeLink(problemDetails.getLeetcodeLink());
        problem.setTutorialLink(problemDetails.getTutorialLink());
        problem.setDifficultyLevel(problemDetails.getDifficultyLevel());
        problem.setStepsToApproach(problemDetails.getStepsToApproach());
        return problemRepository.save(problem);
    }

    public void deleteProblem(Long problemId) {
        Problem problem = problemRepository.findById(problemId).orElseThrow(() -> new RuntimeException("Problem not found"));
        problemRepository.delete(problem);

    }

    public String getTutorialForProblem(Long problemId) {
        Problem problem = problemRepository.findById(problemId).orElseThrow(() -> new RuntimeException("Problem not found"));
        return problem.getTutorialLink();

    }

    public String getSolutionForProblem(Long problemId) {
        Problem problem = problemRepository.findById(problemId).orElseThrow(() -> new RuntimeException("Problem not found"));
        return problem.getStepsToApproach();
    }
}
