package com.westgate.appointmentservice.exception;

public class AppointmentConflictException extends RuntimeException {

    public AppointmentConflictException() {
        super("Appointment time conflicts with an existing appointment");
    }
}