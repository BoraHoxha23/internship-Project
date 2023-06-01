package com.internshipFinal.Project.Internship.exceptions;

public class FlightNotFoundException extends Exception {
    public FlightNotFoundException(Long flightId) {
        super("Flight with ID " + flightId + " not found.");
    }
}