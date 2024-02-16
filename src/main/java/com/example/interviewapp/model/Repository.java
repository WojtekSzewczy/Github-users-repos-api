package com.example.interviewapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Repository {
    private String name;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Boolean fork;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String owner;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Branch> branches;
}
