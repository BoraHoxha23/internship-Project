package com.internshipFinal.Project.Internship.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.internshipFinal.Project.Internship.model.entity.Booking;
import com.internshipFinal.Project.Internship.model.entity.Flight;
import com.internshipFinal.Project.Internship.model.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonSerialize
@Getter
@Setter
public class BookingDTO {
    private Long id;
    private boolean cancelled;
    private User user;
    private List<Flight> flights;

    public BookingDTO(Booking booking){
        this.setId(booking.getId());
        this.setUser(booking.getUser());
        this.setCancelled(booking.isCancelled());
        this.setFlights(booking.getFlights());
    }
}
