package com.cts.Hotel_Management.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.Hotel_Management.dto.BookingDTO;
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
	
	 @Autowired
	 private ModelMapper modelMapper;

	@Override
	public List<BookingDTO> getAllBookings(){
		List<Booking> bookings=bookingRepository.findAll();
		  return bookings.stream()
                  .map(this::convertToDto)
                  .collect(Collectors.toList());
		
	}
	@Override
	public void saveBooking(Long roomId, Long userId, BookingDTO bookingRequest) {
	    
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
	    bookingRequest.setRoomId(roomId);
	    bookingRequest.setUserId(userId);
	    int total = bookingRequest.getNumOfAdults() + bookingRequest.getNumOfChildren();
	    bookingRequest.setTotalNumOfGuest(total);

	    // Convert DTO to entity
	    Booking booking = convertToEntity(bookingRequest);
	    booking.setRoom(room);
	    booking.setUser(user);

	    // Add booking to room's booking list
	    room.getBookings().add(booking);

	    // Save room and booking
	    roomRepository.save(room); // CascadeType.ALL will save the booking as well
	        bookingRepository.save(booking);
		
	    
	
	}
	public boolean roomIsAvailable(BookingDTO bookingRequest, List<Booking> existingBookings) {
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
		        // Handled the case where the booking is not found
		        throw new  RuntimeException("Booking with id " + id + " not found");
		    }
		}
	@Override
	public BookingDTO convertToDto(Booking booking) {
		return modelMapper.map(booking, BookingDTO.class);
	}
	@Override
	public Booking convertToEntity(BookingDTO bookingDTO) {
		return modelMapper.map(bookingDTO, Booking.class);
	}
}
	
	  
