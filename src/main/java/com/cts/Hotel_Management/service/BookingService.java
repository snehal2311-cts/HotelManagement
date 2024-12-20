package com.cts.Hotel_Management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.Hotel_Management.entity.Booking;
import com.cts.Hotel_Management.entity.Room;
import com.cts.Hotel_Management.repository.BookingRepository;
import com.cts.Hotel_Management.repository.RoomRepository;

@Service
public class BookingService {
	
	
	@Autowired
	private BookingRepository bookingRepository;
	@Autowired
	private RoomRepository roomRepository;
	
	public List<Booking> getAllBookings(){
		return bookingRepository.findAll();
	}
	
	public void cancelBooking(Long id) {
		 Optional<Booking> bookingOpt = bookingRepository.findById(id);
		    
		    if (bookingOpt.isPresent()) {
		        Booking booking = bookingOpt.get();
		        Room room = booking.getRoom();
		        room.setBooked(false);
		        roomRepository.save(room); // Save the updated room status
		        bookingRepository.deleteById(id);
		    } else {
		        // Handle the case where the booking is not found
		        throw new  RuntimeException("Booking with id " + id + " not found");
		    }
		}
		
	}


