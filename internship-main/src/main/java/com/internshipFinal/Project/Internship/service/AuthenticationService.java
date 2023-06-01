package com.internshipFinal.Project.Internship.service;

import com.internshipFinal.Project.Internship.model.dto.AuthenticationRequest;
import com.internshipFinal.Project.Internship.model.dto.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);

}