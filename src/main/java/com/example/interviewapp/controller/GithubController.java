package com.example.interviewapp.controller;

import com.example.interviewapp.service.GithubService;
import com.example.interviewapp.model.Repository;
import com.example.interviewapp.exceptions.UserNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class GithubController {
    private final GithubService githubService;

    public GithubController(GithubService githubService) {
        this.githubService = githubService;
    }

    @GetMapping(value = "/{username}", produces = "application/json")
    public List<Repository> getRepositories(@PathVariable String username) throws UserNotFoundException {
        return githubService.getRepositoriesForUser(username);
    }
}
