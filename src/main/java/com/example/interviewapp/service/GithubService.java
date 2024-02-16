package com.example.interviewapp.service;

import com.example.interviewapp.model.Branch;
import com.example.interviewapp.model.Repository;
import com.example.interviewapp.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class GithubService {
    private final RestTemplate restTemplate;

    @Autowired
    public GithubService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private List<Repository> filterRepositories(Repository[] repositories, String username) {
        return Arrays.stream(repositories)
                .filter(repository -> !repository.getFork())
                .peek(repository -> repository.setOwner(username))
                .toList();
    }

    public List<Repository> getRepositoriesForUser(String username) throws UserNotFoundException {
        if(checkIfUserExists(username)){
            final String reposURL = "https://api.github.com/users/" + username + "/repos";
            Repository[] repositories = restTemplate.getForObject(reposURL, Repository[].class);
            List<Repository> repositoryList = filterRepositories(repositories, username);
            getBranches(repositoryList);
            return repositoryList;
        }else{
            throw new UserNotFoundException("user " + username+ " not found");
        }


    }
    private void getBranches(List<Repository> repositoryList) {
        repositoryList.forEach(repository -> {
            final String branchesURL = "https://api.github.com/repos/" + repository.getOwner() + "/" + repository.getName() + "/branches";
            Branch[] branches = restTemplate.getForObject(branchesURL, Branch[].class);
            repository.setBranches(Arrays.asList(branches));
        });
    }
    private boolean checkIfUserExists(String username) {
        final String url = "https://api.github.com/users/" + username;
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            return response.getStatusCode().is2xxSuccessful();
        } catch (HttpClientErrorException.NotFound e) {
            return false;
        }
    }
}
