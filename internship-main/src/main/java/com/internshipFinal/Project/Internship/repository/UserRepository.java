package com.internshipFinal.Project.Internship.repository;

import com.internshipFinal.Project.Internship.model.entity.User;
import com.internshipFinal.Project.Internship.model.enums.UserEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByIdAndRole(Long travelerId, UserEnum traveller);

    Optional<User> findByUsername(String username);

}
