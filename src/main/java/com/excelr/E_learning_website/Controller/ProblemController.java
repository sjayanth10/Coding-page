package com.excelr.E_learning_website.Controller;

import com.excelr.E_learning_website.Entity.Problem;
import com.excelr.E_learning_website.Entity.Userinfo;
import com.excelr.E_learning_website.Exception.InvalidCategoryException;
import com.excelr.E_learning_website.Exception.InvalidDifficultyException;
import com.excelr.E_learning_website.Exception.ProblemNotFoundException;
import com.excelr.E_learning_website.Exception.UnauthorizedAccessException;
import com.excelr.E_learning_website.Service.JwtService;
import com.excelr.E_learning_website.Service.ProblemService;
import com.excelr.E_learning_website.Service.Userservices;
import com.excelr.E_learning_website.dto.AuthRequest;
import com.excelr.E_learning_website.dto.Problemdto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@RestController
@RequestMapping("/api")
public class ProblemController {
    @Autowired
    ProblemService problemService;

    @Autowired
    private Userservices service;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;


    private static final Set<String> VALID_CATEGORIES = Set.of(
            "Arrays", "Strings", "2D Arrays", "Searching and sorting ",
            "Backtracking", "Linked List", "Stacks and Queues", "Sorting",
             "Greedy", "Binary Trees", "Binary Search Trees" , "Heaps and Hashing" , "Heaps" , "Hashing " ,
            "Graphs", "Tries" , "Dynamic Programming" , "Bit Manipulation" , "Segment Trees"
    );

    private static final Set<String> VALID_DIFFICULTIES = Set.of("Easy", "Medium", "Hard");

    @PostMapping("/adduser")
    public String addNewUser(@RequestBody Userinfo user){
        return service.addUser(user);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<List<Problem>> getAllProblems(@RequestParam(required = false) String category,
                                                        @RequestParam(required = false) String difficulty) {
        if (category != null && !VALID_CATEGORIES.contains(category)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (difficulty != null && !VALID_DIFFICULTIES.contains(difficulty)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Problem> problems = problemService.getAllProblems(category, difficulty);
        return new ResponseEntity<>(problems, HttpStatus.OK);
    }
    @GetMapping("/{problemId}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Problem> getProblemDetails(@PathVariable Long problemId) {
        Optional<Problem> problem = problemService.getProblemById(problemId);
        return problem.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Problem> createProblem(@RequestBody Problem problem) {

        Problem createdProblem = problemService.createProblem(problem);
        return new ResponseEntity<>(createdProblem, HttpStatus.CREATED);
    }
    @PutMapping("/{problemId}/update")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Problem> updateProblem(@PathVariable Long problemId, @RequestBody Problem problem) {

        try {
            Problem updatedProblem = problemService.updateProblem(problemId, problem);
            return new ResponseEntity<>(updatedProblem, HttpStatus.OK);
        } catch (ProblemNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{problemId}/delete")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteProblem(@PathVariable Long problemId) {

        try {
            problemService.deleteProblem(problemId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ProblemNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{problemId}/tutorial")
    public ResponseEntity<String> getProblemTutorial(@PathVariable Long problemId) {
        try {
            String tutorial = problemService.getTutorialForProblem(problemId);
            return new ResponseEntity<>(tutorial, HttpStatus.OK);
        } catch (ProblemNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{problemId}/solution")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<String> getProblemSolution(@PathVariable Long problemId) {
        try {
            String solution = problemService.getSolutionForProblem(problemId);
            return new ResponseEntity<>(solution, HttpStatus.OK);
        } catch (ProblemNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }


    }
}

