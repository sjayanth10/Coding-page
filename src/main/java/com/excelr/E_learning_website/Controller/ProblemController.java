package com.excelr.E_learning_website.Controller;

import com.excelr.E_learning_website.Entity.Problem;
import com.excelr.E_learning_website.Service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/problems")
public class ProblemController {
    @Autowired
    private ProblemService problemService;

    @GetMapping("/{problemId}")
    public Optional<Problem> getProblemId(@PathVariable Long problemId){
        return problemService.getProblemBYId(problemId);
    }

    @PostMapping("/create")
    public Problem createProblem( @RequestBody Problem problem){
        return problemService.createProblem(problem);

    }

    @PutMapping("/{problemId}")
    public Problem updateProblem(@PathVariable Long problemId, @RequestBody Problem problemDetails) {
        return problemService.updateProblem(problemId, problemDetails);
    }

    @DeleteMapping("/{problemId}")
    public void deleteProblem(@PathVariable Long problemId) {
        problemService.deleteProblem(problemId);
    }

    @GetMapping("/{problemId}/tutorial")
    public String getTutorialForProblem(@PathVariable Long problemId) {
        return problemService.getTutorialForProblem(problemId);
    }


    @GetMapping("/{problemId}/solution")
    public String getSolutionForProblem(@PathVariable Long problemId) {
        return problemService.getSolutionForProblem(problemId);
    }
}
