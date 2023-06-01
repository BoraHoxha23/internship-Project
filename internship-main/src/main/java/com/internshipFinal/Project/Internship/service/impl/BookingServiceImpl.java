package com.internshipFinal.Project.Internship.service.impl;

import com.internshipFinal.Project.Internship.model.dto.BookingDTO;
import com.internshipFinal.Project.Internship.model.dto.UserDTO;
import com.internshipFinal.Project.Internship.model.entity.Booking;
import com.internshipFinal.Project.Internship.model.entity.Flight;
import com.internshipFinal.Project.Internship.model.entity.User;
import com.internshipFinal.Project.Internship.model.enums.UserEnum;
import com.internshipFinal.Project.Internship.repository.BookingRepository;
import com.internshipFinal.Project.Internship.repository.FlightRepository;
import com.internshipFinal.Project.Internship.repository.UserRepository;
import com.internshipFinal.Project.Internship.service.BookingService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final FlightRepository flightRepository;

    public BookingServiceImpl(BookingRepository bookingRepository, UserRepository userRepository, FlightRepository flightRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.flightRepository = flightRepository;
    }

    /*@Override
    public BookingDTO createBooking(BookingDTO bookingDTO) {
        Booking booking = new Booking();
        booking.setId(bookingDTO.getId());
        booking.setCancelled(bookingDTO.isCancelled());
        booking.setUser(bookingDTO.getUser());
        booking.setFlights(bookingDTO.getFlights());

        return new BookingDTO(bookingRepository.save(booking));
    } */

    @Override
    public BookingDTO createBooking(BookingDTO bookingDTO, String email) {
        // Extract the necessary information from the bookingDTO and email parameters
        Long bookingId = bookingDTO.getId();
        User user = bookingDTO.getUser();
        List<Flight> flights = bookingDTO.getFlights();

        // Create a new Booking entity and set its properties
        Booking booking = new Booking();
        booking.setId(bookingId);
        booking.setUser(user);
        booking.setFlights(flights);
        booking.setCancelled(false);


        BookingDTO createdBookingDTO = new BookingDTO(booking);

        return createdBookingDTO;
    }


    @Override
    public List<BookingDTO> getBooking() {
        return bookingRepository
                .findAll()
                .stream()
                .map(BookingDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookingDTO> getBookingsByTravelerId(Long travelerId) {
        List<BookingDTO> bookings = new ArrayList<>();

        // Retrieve the user with the specified travelerId and role TRAVELLER
        Optional<User> optionalTraveler = userRepository.findByIdAndRole(travelerId, UserEnum.TRAVELLER);
        if (optionalTraveler.isPresent()) {
            User traveler = optionalTraveler.get();

            // Fetch all bookings associated with the traveler
            List<Booking> travelerBookings = (List<Booking>) bookingRepository.findByUser(traveler);
            for (Booking booking : travelerBookings) {
                BookingDTO bookingDTO = new BookingDTO(booking);
                bookings.add(bookingDTO);
            }
        }

        return bookings;
    }

    @Override
    public List<UserDTO> getTravelersByFlightId(Long flightId) {
        List<UserDTO> travelers = new ArrayList<>();

        // Retrieve the flight with the specified flightId
        Optional<Flight> optionalFlight = flightRepository.findById(flightId);
        if (optionalFlight.isPresent()) {
            Flight flight = optionalFlight.get();

            // Fetch all bookings associated with the flight
            List<Booking> bookings = bookingRepository.findByFlights(flight);
            for (Booking booking : bookings) {
                User user = booking.getUser();
                if (user.getRole() == UserEnum.TRAVELLER) {
                    UserDTO userDTO = new UserDTO(user);
                    travelers.add(userDTO);
                }
            }
        }

        return travelers;
    }

}







