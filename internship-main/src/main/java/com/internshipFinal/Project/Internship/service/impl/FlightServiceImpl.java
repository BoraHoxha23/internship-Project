package com.internshipFinal.Project.Internship.service.impl;

import com.internshipFinal.Project.Internship.exceptions.FlightAlreadyBookedException;
import com.internshipFinal.Project.Internship.exceptions.FlightNotFoundException;
import com.internshipFinal.Project.Internship.model.dto.BookingDTO;
import com.internshipFinal.Project.Internship.model.dto.FlightDTO;
import com.internshipFinal.Project.Internship.model.entity.Booking;
import com.internshipFinal.Project.Internship.model.entity.Flight;
import com.internshipFinal.Project.Internship.repository.BookingRepository;
import com.internshipFinal.Project.Internship.repository.FlightRepository;
import com.internshipFinal.Project.Internship.service.FlightService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightServiceImpl implements FlightService {

    private static final int PAGE_SIZE = 5;
    private final FlightRepository flightRepository;
    private final BookingRepository bookingRepository;

    public FlightServiceImpl(FlightRepository flightRepository, BookingRepository bookingRepository) {
        this.flightRepository = flightRepository;
        this.bookingRepository = bookingRepository;
    }


    @Override
    public FlightDTO createFlight(FlightDTO flightDTO) {
        Flight flight = new Flight();
        flight.setId(flightDTO.getId());
        flight.setAirlineCode(flightDTO.getAirlineCode());
        flight.setFlightNumber(flightDTO.getFlightNumber());
        flight.setOrigin(flightDTO.getOrigin());
        flight.setDestination(flightDTO.getDestination());
        flight.setDepartureDate(flightDTO.getDepartureDate());
        flight.setArrivalDate(flightDTO.getArrivalDate());
        flight.setDepartureTime(flightDTO.getDepartureTime());
        flight.setArrivalTime(flightDTO.getArrivalTime());
        flight.setTotalSeats(flightDTO.getTotalSeats());
        flight.setBookingClasses(flightDTO.getBookingClasses());
        flight.setBookings(flightDTO.getBookings());
        return new FlightDTO(flightRepository.save(flight));

    }

    @Override
    public List<FlightDTO> getAllFlights() {
        return flightRepository
                .findAll()
                .stream()
                .map(FlightDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public FlightDTO updateFlight(Long id, FlightDTO flightDTO) throws ChangeSetPersister.NotFoundException {
        Flight flight = flightRepository
                .findById(id)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);

        List<Booking> bookings = flight.getBookings();

        if (bookings.isEmpty()) {
            // updateojm kur nuk ka booking
            flight.setAirlineCode(flightDTO.getAirlineCode());
            flight.setFlightNumber(flightDTO.getFlightNumber());
            flight.setOrigin(flightDTO.getOrigin());
            flight.setDestination(flightDTO.getDestination());
            flight.setDepartureDate(flightDTO.getDepartureDate());
            flight.setArrivalDate(flightDTO.getArrivalDate());
            flight.setDepartureTime(flightDTO.getDepartureTime());
            flight.setArrivalTime(flightDTO.getArrivalTime());
            flight.setTotalSeats(flightDTO.getTotalSeats());
            flight.setBookingClasses(flightDTO.getBookingClasses());
            flight.setBookings(flightDTO.getBookings());
        } else {
            // kur ekziston booking bejme update vetem departureTime
            flight.setDepartureTime(flightDTO.getDepartureTime());
        }
        return new FlightDTO(flightRepository.save(flight));
    }

    @Override
    public void deleteFlight(Long id) throws FlightNotFoundException, FlightAlreadyBookedException {
        Flight flight = flightRepository.findById(id)
                .orElseThrow();

        List<Booking> bookings = flight.getBookings();
        if (!bookings.isEmpty()) {
            throw new FlightAlreadyBookedException();
        }

        flightRepository.delete(flight);

    }

    @Override
    public List<FlightDTO> searchFlights(String origin, String destination, LocalDate flightDate, String airlineCode) {
        List<Flight> flights = flightRepository.findByOriginAndDestinationAndDepartureDateAndAirlineCode(origin, destination, flightDate, airlineCode);
        return flights.stream().map(FlightDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<BookingDTO> getUserBookings(Long userId, int page) {
        PageRequest pageRequest = PageRequest.of(page, PAGE_SIZE, Sort.by("bookingDate").descending());
        List<Booking> bookings = bookingRepository.findByUserId(userId, pageRequest);
        return bookings.stream().map(BookingDTO::new).collect(Collectors.toList());
    }
    }




