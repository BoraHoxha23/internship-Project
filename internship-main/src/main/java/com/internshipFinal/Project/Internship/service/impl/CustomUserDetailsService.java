package com.internshipFinal.Project.Internship.service.impl;


import com.internshipFinal.Project.Internship.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .map(this::toUserDetails)
                .orElseThrow(()-> new UsernameNotFoundException("Email not found"));
    }

    /*private UserDetails toUserDetails(User user){
        return User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();
         .roles(employee.getRoles().stream().map(role -> role.getRole().name()).toArray(String[]::new))

    }*/

    private UserDetails toUserDetails(com.internshipFinal.Project.Internship.model.entity.User user){
        return User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole().name()).build();
    }


}