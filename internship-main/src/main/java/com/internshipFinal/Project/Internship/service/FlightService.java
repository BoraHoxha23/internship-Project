package com.internshipFinal.Project.Internship.service;

import com.internshipFinal.Project.Internship.exceptions.FlightAlreadyBookedException;
import com.internshipFinal.Project.Internship.exceptions.FlightNotFoundException;
import com.internshipFinal.Project.Internship.model.dto.BookingDTO;
import com.internshipFinal.Project.Internship.model.dto.FlightDTO;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.time.LocalDate;
import java.util.List;

public interface FlightService {
    FlightDTO createFlight(FlightDTO flightDTO);
    List<FlightDTO> getAllFlights();

    FlightDTO updateFlight(Long id, FlightDTO flightDTO) throws ChangeSetPersister.NotFoundException;

    void deleteFlight(Long id) throws FlightNotFoundException, FlightAlreadyBookedException;

    List<FlightDTO> searchFlights(String origin, String destination, LocalDate flightDate, String airlineCode);

    List<BookingDTO> getUserBookings(Long userId, int page);


}
