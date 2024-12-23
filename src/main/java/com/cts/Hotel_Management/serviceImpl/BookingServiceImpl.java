package com.cts.Hotel_Management.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.Hotel_Management.entity.Booking;
import com.cts.Hotel_Management.entity.Room;
import com.cts.Hotel_Management.entity.User;
import com.cts.Hotel_Management.exception.OurException;
import com.cts.Hotel_Management.repository.BookingRepository;
import com.cts.Hotel_Management.repository.RoomRepository;
import com.cts.Hotel_Management.repository.UserRepository;
import com.cts.Hotel_Management.service.BookingService;


@Service
public class BookingServiceImpl implements BookingService {
	@Autowired
	private BookingRepository bookingRepository;
	@Autowired
	private RoomRepository roomRepository;
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public List<Booking> getAllBookings(){
		return bookingRepository.findAll();
	}
	@Override
	public void saveBooking(Long roomId, Long userId, Booking bookingRequest) {
	    

	    
	        // Validate dates
	        if (bookingRequest.getCheckOutDate().isBefore(bookingRequest.getCheckInDate())) {
	            throw new IllegalArgumentException("Check-out date must come after check-in date");
	        }

	        // Fetch room and user
	        Room room = roomRepository.findById(roomId)
	                .orElseThrow(() -> new OurException("Room Not Found"));
	        User user = userRepository.findById(userId)
	                .orElseThrow(() -> new OurException("User Not Found"));

	        // Check room availability
	        if (!roomIsAvailable(bookingRequest, room.getBookings())) {
	            throw new OurException("Room not available for selected date range");
	        }

	        // Set booking details
	        bookingRequest.setRoom(room);
	        bookingRequest.setUser(user);
	        // Save booking
	        bookingRepository.save(bookingRequest);
		
	    
	
	}
	@Override
	public boolean roomIsAvailable(Booking bookingRequest, List<Booking> existingBookings) {
	    return existingBookings.stream()
	            .noneMatch(existingBooking ->
	                    bookingRequest.getCheckInDate().isBefore(existingBooking.getCheckOutDate()) &&
	                    bookingRequest.getCheckOutDate().isAfter(existingBooking.getCheckInDate())
	            );
	}
	
	@Override	    
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
