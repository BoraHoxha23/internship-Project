package com.internshipFinal.Project.Internship.repository;

import com.internshipFinal.Project.Internship.model.dto.FlightDTO;
import com.internshipFinal.Project.Internship.model.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByOriginAndDestinationAndDepartureDateAndAirlineCode(String origin, String destination, LocalDate flightDate, String airlineCode);


}

