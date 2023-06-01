package com.internshipFinal.Project.Internship.repository;

import com.internshipFinal.Project.Internship.model.dto.BookingDTO;
import com.internshipFinal.Project.Internship.model.dto.UserDTO;
import com.internshipFinal.Project.Internship.model.entity.Booking;
import com.internshipFinal.Project.Internship.model.entity.Flight;
import com.internshipFinal.Project.Internship.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    Arrays findByUser(User traveler);


    List<Booking> findByFlights(Flight flight);

    List<Booking> findByUserId(Long userId, Pageable pageable);
}
