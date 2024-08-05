package com.excelr.E_learning_website.Service;

import com.excelr.E_learning_website.Entity.Problem;
import com.excelr.E_learning_website.Exception.ProblemNotFoundException;
import com.excelr.E_learning_website.Repository.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProblemService {
    @Autowired
    private ProblemRepository problemRepository;

    public List<Problem> getAllProblems(String topicname, String difficultyLevel) {
        if (topicname != null && difficultyLevel != null) {
            return problemRepository. findByTopicnameAndDifficultyLevel(topicname, difficultyLevel);
        } else if (topicname != null) {
            return problemRepository.findByTopicname(topicname);
        } else if (difficultyLevel!= null) {
            return problemRepository.findByDifficultyLevel(difficultyLevel);
        } else {
            return problemRepository.findAll();
        }
    }

    public Problem createProblem(Problem problemDto) {
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
        Problem problem = problemRepository.findById(problemId).orElseThrow(() -> new ProblemNotFoundException("Problem not found"));
        problem.setLanguage(problemDetails.getLanguage());
        problem.setName(problemDetails.getName());
        problem.setLeetcodeLink(problemDetails.getLeetcodeLink());
        problem.setTutorialLink(problemDetails.getTutorialLink());
        problem.setDifficultyLevel(problemDetails.getDifficultyLevel());
        problem.setStepsToApproach(problemDetails.getStepsToApproach());
        return problemRepository.save(problem);
    }

    public void deleteProblem(Long problemId) {
        Problem problem = problemRepository.findById(problemId)
                .orElseThrow(() -> new ProblemNotFoundException("Problem not found with id: " + problemId));
        problemRepository.delete(problem);
    }

    public String getTutorialForProblem(Long problemId) {
        Problem problem = problemRepository.findById(problemId)
                .orElseThrow(() -> new ProblemNotFoundException("Problem not found with id: " + problemId));
        return problem.getTutorialLink();
    }

    public String getSolutionForProblem(Long problemId) {
        Problem problem = problemRepository.findById(problemId)
                .orElseThrow(() -> new ProblemNotFoundException("Problem not found with id: " + problemId));
        return problem.getStepsToApproach();
    }


    public Optional<Problem> getProblemById(Long problemId) {
        return problemRepository.findById(problemId);
    }



}
