package com.internshipFinal.Project.Internship.model.dto;

import lombok.Data;

@Data
public class AuthenticationRequest {

    private String email;
    private String password;
}