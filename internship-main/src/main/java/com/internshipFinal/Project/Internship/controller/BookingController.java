package com.internshipFinal.Project.Internship.controller;

import com.internshipFinal.Project.Internship.model.dto.BookingDTO;
import com.internshipFinal.Project.Internship.model.dto.UserDTO;
import com.internshipFinal.Project.Internship.model.entity.Booking;
import com.internshipFinal.Project.Internship.service.BookingService;
import com.internshipFinal.Project.Internship.service.FlightService;
import com.internshipFinal.Project.Internship.service.UserService;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/booking")
@Schema
public class BookingController {
    private final BookingService bookingService;
    private final UserService userService;
    private final FlightService flightService;


    public BookingController(BookingService bookingService, UserService userService, FlightService flightService) {
        this.bookingService = bookingService;
        this.userService = userService;
        this.flightService = flightService;
    }
    @PreAuthorize(value = "hasAnyRole('TRAVELLER')")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<BookingDTO>> getAllBookings(){
        return ResponseEntity.ok(bookingService.getBooking());
    }

    @PreAuthorize(value = "hasAnyRole('TRAVELLER')")
    @PostMapping
    public ResponseEntity<BookingDTO> createBooking(@RequestBody BookingDTO bookingDTO, @RequestParam String email) {
        BookingDTO createdBooking = bookingService.createBooking(bookingDTO, email);
        return ResponseEntity.ok(createdBooking);
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @GetMapping("/traveler/{travelerId}")
    public ResponseEntity<List<BookingDTO>> getBookingsByTravelerId(@PathVariable Long travelerId) {
        List<BookingDTO> travelerBookings = bookingService.getBookingsByTravelerId(travelerId);
        return ResponseEntity.ok(travelerBookings);
    }

    //Can get list of all his bookings and the flights for each booking using pagination (page size should
    //be 5 and bookings should be listed from the most recent to the oldest based on the booking
    //date)
    @PreAuthorize(value = "hasAnyRole('TRAVELLER')")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingDTO>> getUserBookings(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page
    ) {
        List<BookingDTO> bookings = flightService.getUserBookings(userId, page);
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }


}
