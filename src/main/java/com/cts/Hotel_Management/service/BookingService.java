package com.cts.Hotel_Management.service;

import java.util.List;

import com.cts.Hotel_Management.dto.BookingDTO;
import com.cts.Hotel_Management.entity.Booking;

//@Service
public interface BookingService {
	
	List<Booking> getAllBookings();
	void saveBooking(Long roomId, Long userId, Booking bookingRequest);
	void cancelBooking(Long id);
	
	}


