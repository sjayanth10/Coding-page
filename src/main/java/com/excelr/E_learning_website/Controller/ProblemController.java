package com.excelr.E_learning_website.Controller;

import com.excelr.E_learning_website.Entity.Problem;
import com.excelr.E_learning_website.Service.ProblemService;
import com.excelr.E_learning_website.dto.Problemdto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Problem> createProblem(@RequestBody Problemdto problem) {
        System.out.println("problem uploded ");
        return new ResponseEntity<>(problemService.createProblem(problem), HttpStatus.CREATED);
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
