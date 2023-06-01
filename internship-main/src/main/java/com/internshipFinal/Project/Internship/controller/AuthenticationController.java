package com.internshipFinal.Project.Internship.controller;

import com.internshipFinal.Project.Internship.model.dto.AuthenticationRequest;
import com.internshipFinal.Project.Internship.model.dto.AuthenticationResponse;
import com.internshipFinal.Project.Internship.model.dto.UserDTO;
import com.internshipFinal.Project.Internship.service.AuthenticationService;
import com.internshipFinal.Project.Internship.service.UserService;
import com.internshipFinal.Project.Internship.service.impl.AuthenticationServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserService userService;

    public AuthenticationController(AuthenticationService authenticationService, UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.POST,path = "/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest){
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }
    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO user){
        return ResponseEntity.ok(userService.register(user));
    }
}