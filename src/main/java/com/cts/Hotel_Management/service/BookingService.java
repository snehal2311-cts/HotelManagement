package com.cts.Hotel_Management.service;

import java.util.List;

import com.cts.Hotel_Management.entity.Booking;

//@Service
public interface BookingService {
	
	List<Booking> getAllBookings();
	void saveBooking(Long roomId, Long userId, Booking bookingRequest);
	boolean roomIsAvailable(Booking bookingRequest, List<Booking> existingBookings);
	void cancelBooking(Long id);
	
	}


