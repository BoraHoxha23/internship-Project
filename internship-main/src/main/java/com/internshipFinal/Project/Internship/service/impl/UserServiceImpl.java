package com.internshipFinal.Project.Internship.service.impl;

import com.internshipFinal.Project.Internship.exceptions.GeneralException;
import com.internshipFinal.Project.Internship.model.dto.UserDTO;
import com.internshipFinal.Project.Internship.model.entity.User;
import com.internshipFinal.Project.Internship.model.enums.UserEnum;
import com.internshipFinal.Project.Internship.repository.BookingRepository;
import com.internshipFinal.Project.Internship.repository.FlightRepository;
import com.internshipFinal.Project.Internship.repository.UserRepository;
import com.internshipFinal.Project.Internship.service.UserService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;



@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final FlightRepository flightRepository;
    private final BookingRepository bookingRepository;
    private final BCryptPasswordEncoder encoder;

    public UserServiceImpl(UserRepository userRepository, FlightRepository flightRepository, BookingRepository bookingRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.flightRepository = flightRepository;
        this.bookingRepository = bookingRepository;
        this.encoder = encoder;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setAddress(userDTO.getAddress());
        user.setRole(userDTO.getRole());
        return new UserDTO(userRepository.save(user));
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getByEmail(String email) throws ChangeSetPersister.NotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());

        return new UserDTO(user);
    }

    @Override
    public void deleteByID(Long id) throws ChangeSetPersister.NotFoundException {
        userRepository.deleteById(id);
    }

    @Override
    public void updateUser(UserDTO userDTO) throws ChangeSetPersister.NotFoundException {
        User user = userRepository.findById(userDTO.getId())
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        user.setUsername(userDTO.getUsername());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setAddress(userDTO.getAddress());
        user.setRole(userDTO.getRole());

        userRepository.save(user);
    }

    @Override
    public UserDTO register(UserDTO userDTO) {
        // Check if Username or Email exist
        if (userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            throw new GeneralException("Username already exists", null);
        }
        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new GeneralException("Email already exists", null);
        }

        // Create a new User entity
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(encoder.encode(userDTO.getPassword()));
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setAddress(userDTO.getAddress());
        user.setRole(userDTO.getRole());

        User savedUser = userRepository.save(user);

        return new UserDTO(savedUser);
    }
}
