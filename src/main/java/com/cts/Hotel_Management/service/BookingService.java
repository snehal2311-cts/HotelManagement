package com.cts.Hotel_Management.service;

import java.util.List;

import com.cts.Hotel_Management.dto.BookingDTO;
import com.cts.Hotel_Management.entity.Booking;

import jakarta.validation.Valid;

//@Service
public interface BookingService {
	
	List<BookingDTO> getAllBookings();
	void saveBooking(Long roomId, Long userId, BookingDTO bookingRequest);
	void cancelBooking(Long id);
	
	BookingDTO convertToDto(Booking booking);
	 Booking convertToEntity(BookingDTO bookingDTO);

	}


