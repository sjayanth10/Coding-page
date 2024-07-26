package com.excelr.E_learning_website.Service;

import com.excelr.E_learning_website.Entity.Problem;
import com.excelr.E_learning_website.Repository.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ProblemService {
    @Autowired
    private ProblemRepository problemRepository;

    public Optional<Problem> getProblemBYId(Long problemId) {
        return problemRepository.findById(problemId);
    }

    public Problem createProblem(Problem problem) {
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
