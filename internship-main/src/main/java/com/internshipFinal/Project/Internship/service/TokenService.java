package com.internshipFinal.Project.Internship.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface TokenService {
    String generateToken(UserDetails userDetails);

    Boolean validateToken(String token, UserDetails user);

    String extractEmail(String token);
}