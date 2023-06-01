package com.internshipFinal.Project.Internship.service;

import com.internshipFinal.Project.Internship.model.dto.BookingDTO;
import com.internshipFinal.Project.Internship.model.dto.UserDTO;

import java.util.List;

public interface BookingService {
    BookingDTO createBooking(BookingDTO bookingDTO, String email);
    List<BookingDTO> getBooking();

    List<BookingDTO> getBookingsByTravelerId(Long travelerId);
    List<UserDTO> getTravelersByFlightId(Long flightId);

}
