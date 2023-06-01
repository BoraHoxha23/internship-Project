package com.internshipFinal.Project.Internship.controller;

import com.internshipFinal.Project.Internship.exceptions.FlightAlreadyBookedException;
import com.internshipFinal.Project.Internship.exceptions.FlightNotFoundException;
import com.internshipFinal.Project.Internship.model.dto.FlightDTO;
import com.internshipFinal.Project.Internship.service.FlightService;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/flights")
@Schema


public class FlightController {
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<FlightDTO>> getAllFlights(String flights){
        return ResponseEntity.ok(flightService.getAllFlights());
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<FlightDTO> createFlight(@RequestBody FlightDTO flightDTO){
        return ResponseEntity.status(201).body(flightService.createFlight(flightDTO));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<FlightDTO> updateFlight(@PathVariable Long id, @RequestBody FlightDTO flightDTO) {
        try {
            FlightDTO updatedFlight = flightService.updateFlight(id, flightDTO);
            return ResponseEntity.ok(updatedFlight);
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFlight(@PathVariable Long id) {
        try {
            flightService.deleteFlight(id);
            return ResponseEntity.noContent().build();
        } catch (FlightNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (FlightAlreadyBookedException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Flight has already been booked and cannot be deleted.");
        }
    }

    @PreAuthorize(value = "hasAnyRole('TRAVELLER')")
    @RequestMapping(method = RequestMethod.GET, value = "/search")
    public ResponseEntity<List<FlightDTO>> searchFlights(
            @RequestParam String origin,
            @RequestParam String destination,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate flightDate,
            @RequestParam(required = false) String airlineCode
    ) {
        List<FlightDTO> flights = flightService.searchFlights(origin, destination, flightDate, airlineCode);
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

}
