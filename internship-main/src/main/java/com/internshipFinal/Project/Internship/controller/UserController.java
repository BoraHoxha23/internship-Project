package com.internshipFinal.Project.Internship.controller;

import com.internshipFinal.Project.Internship.model.dto.UserDTO;
import com.internshipFinal.Project.Internship.model.entity.User;
import com.internshipFinal.Project.Internship.service.UserService;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@Schema
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //FUNKSIONET E ADMINIT PER USERIN
    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @RequestMapping(method = RequestMethod.GET, value = "/allUsers")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
        return ResponseEntity.status(201).body(userService.createUser(userDTO));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{email}")
    public ResponseEntity<UserDTO> getByEmail(@PathVariable String email) {
        try {
            UserDTO userDTO = (UserDTO) userService.getByEmail(email);
            return ResponseEntity.ok(userDTO);
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) throws ChangeSetPersister.NotFoundException {
        userDTO.setId(id); // Set the ID from the path variable into the UserDTO
        userService.updateUser(userDTO);
        // Retrieve the updated user
        UserDTO updatedUser = userService.getByEmail(userDTO.getEmail());

        return ResponseEntity.ok(updatedUser);
    }


    @RequestMapping(method = RequestMethod.DELETE, path ="/{id}" )
    public void deleteUser(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        userService.deleteByID(id);
    }

    //FUNKISONET E ADMINIT PER FLIGHT-IN

}
