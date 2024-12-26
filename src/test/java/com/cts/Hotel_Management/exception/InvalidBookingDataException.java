package com.cts.Hotel_Management.exception;



public class InvalidBookingDataException extends RuntimeException {

    public InvalidBookingDataException(String message) {
        super(message);
    }
}