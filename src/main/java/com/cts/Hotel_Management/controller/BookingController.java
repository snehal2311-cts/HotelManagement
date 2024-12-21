package com.cts.Hotel_Management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.Hotel_Management.entity.Booking;
import com.cts.Hotel_Management.service.BookingService;


@RequestMapping
@RestController
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping("/book-room/{roomId}/{userId}")
//    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<Booking> saveBookings(@PathVariable Long roomId,
                                                 @PathVariable Long userId,
                                                 @RequestBody Booking bookingRequest) {


         bookingService.saveBooking(roomId, userId, bookingRequest);
        return new ResponseEntity<>(bookingRequest,HttpStatus.OK);

    }
    @GetMapping("/all-bookings")
    //@PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<Booking>> getAllBookings() {
       
        return new ResponseEntity<>( bookingService.getAllBookings(),HttpStatus.OK);
    }
    @DeleteMapping("/cancel/{bookingId}")
    //@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public String cancelBooking(@PathVariable Long bookingId) {
        bookingService.cancelBooking(bookingId);
        return "Booking deleted successfully !";
        
    }
    
}
