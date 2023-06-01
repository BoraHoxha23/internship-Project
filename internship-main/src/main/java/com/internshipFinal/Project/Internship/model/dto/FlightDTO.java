package com.internshipFinal.Project.Internship.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.internshipFinal.Project.Internship.model.entity.Booking;
import com.internshipFinal.Project.Internship.model.entity.Flight;
import com.internshipFinal.Project.Internship.model.enums.BookingClassEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@JsonSerialize
@Getter
@Setter
public class FlightDTO {
    private Long id;
    private String airlineCode;
    private String flightNumber;
    private String origin;
    private String destination;
    private Date departureDate;
    private Date arrivalDate;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private Integer TotalSeats;
    private BookingClassEnum bookingClasses;
    private List<Booking> bookings;

    public FlightDTO(Flight flight){
        this.setId(flight.getId());
        this.setAirlineCode(flight.getAirlineCode());
        this.setFlightNumber(flight.getFlightNumber());
        this.setOrigin(flight.getOrigin());
        this.setDestination(flight.getDestination());
        this.setDepartureDate(flight.getDepartureDate());
        this.setArrivalDate(flight.getArrivalDate());
        this.setDepartureTime(flight.getDepartureTime());
        this.setArrivalTime(flight.getArrivalTime());
        this.setTotalSeats(flight.getTotalSeats());
        this.setBookingClasses(flight.getBookingClasses());
    }
    public FlightDTO(){}
}
