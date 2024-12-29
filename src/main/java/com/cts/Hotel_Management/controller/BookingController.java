package com.cts.Hotel_Management.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.Hotel_Management.dto.BookingDTO;
import com.cts.Hotel_Management.entity.Booking;
import com.cts.Hotel_Management.service.BookingService;

import jakarta.validation.Valid;


@RequestMapping("/api")
@RestController
public class BookingController {
	private static final Logger logger = LoggerFactory.getLogger(BookingController.class);

    @Autowired
    private BookingService bookingService;

    @PostMapping("rooms/book-room/{roomId}/{userId}")
    public ResponseEntity<BookingDTO> saveBookings(@PathVariable Long roomId,
                                                 @PathVariable Long userId,
                                                 @RequestBody @Valid BookingDTO bookingRequest) {
    
        logger.info("Booking request received for roomId: {} and userId: {}", roomId, userId);
        bookingService.saveBooking(roomId, userId, bookingRequest);
        logger.info("Booking saved successfully for roomId: {} and userId: {}", roomId, userId);
        return new ResponseEntity<>(bookingRequest, HttpStatus.OK);
    }

    @GetMapping("/admin/all-bookings")
    public List<BookingDTO> getAllBookings() {
        logger.info("Fetching all bookings");
        return bookingService.getAllBookings();
    }

    @DeleteMapping("admin/cancel-booking/{bookingId}")
    public String cancelBooking(@PathVariable Long bookingId) {
        logger.info("Cancel booking request received for bookingId: {}", bookingId);
        bookingService.cancelBooking(bookingId);
        logger.info("Booking cancelled successfully for bookingId: {}", bookingId);
        return "Booking deleted successfully!";
    }
}
