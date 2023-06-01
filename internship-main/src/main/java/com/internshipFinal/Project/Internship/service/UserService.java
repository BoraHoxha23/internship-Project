package com.internshipFinal.Project.Internship.service;

import com.internshipFinal.Project.Internship.model.dto.UserDTO;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface UserService {

    UserDTO createUser (UserDTO userDTO);
    List<UserDTO> getAllUsers();
    UserDTO getByEmail(String email) throws ChangeSetPersister.NotFoundException;

    void deleteByID(Long id) throws ChangeSetPersister.NotFoundException;

    void updateUser(UserDTO userDTO) throws ChangeSetPersister.NotFoundException;
    UserDTO register(UserDTO userDTO);


}
