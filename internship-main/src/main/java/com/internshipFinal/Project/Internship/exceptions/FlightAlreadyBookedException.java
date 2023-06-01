package com.internshipFinal.Project.Internship.exceptions;

public class FlightAlreadyBookedException extends Exception {
    public FlightAlreadyBookedException() {
        super("Flight has already been booked and cannot be updated.");
    }
}
