package com.cts.Hotel_Management.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.cts.Hotel_Management.dto.BookingDTO;
import com.cts.Hotel_Management.entity.Booking;
import com.cts.Hotel_Management.entity.Room;
import com.cts.Hotel_Management.entity.User;
import com.cts.Hotel_Management.exception.OurException;
import com.cts.Hotel_Management.repository.BookingRepository;
import com.cts.Hotel_Management.repository.RoomRepository;
import com.cts.Hotel_Management.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class BookingServiceImplTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private BookingServiceImpl bookingService;

    private BookingDTO bookingDTO;
    private Room room;
    private User user;

    @BeforeEach
    void setUp() {
        bookingDTO = new BookingDTO();
        bookingDTO.setCheckInDate(LocalDate.of(2025, 1, 10));
        bookingDTO.setCheckOutDate(LocalDate.of(2025, 1, 15));
        bookingDTO.setNumOfAdults(2);
        bookingDTO.setNumOfChildren(1);

        room = new Room();
        room.setId(1L);
        room.setBookings(Arrays.asList());

        user = new User();
        user.setId(1L);
    }

    @Test
    void testSaveBooking() {
    	  when(roomRepository.findById(1L)).thenReturn(Optional.empty());

    	    OurException exception = assertThrows(OurException.class, () -> {
    	        bookingService.saveBooking(1L, 1L, bookingDTO);
    	    });

    	    assertEquals("Room Not Found", exception.getMessage());
    }

    @Test
    void testSaveBookingRoomNotFound() {
        when(roomRepository.findById(1L)).thenReturn(Optional.empty());

        OurException exception = assertThrows(OurException.class, () -> {
            bookingService.saveBooking(1L, 1L, bookingDTO);
        });

        assertEquals("Room Not Found", exception.getMessage());
    }

    @Test
    void testSaveBookingUserNotFound() {
        when(roomRepository.findById(1L)).thenReturn(Optional.of(room));
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        OurException exception = assertThrows(OurException.class, () -> {
            bookingService.saveBooking(1L, 1L, bookingDTO);
        });

        assertEquals("User Not Found", exception.getMessage());
    }

    @Test
    void testCancelBooking() {
        Booking booking = new Booking();
        booking.setId(1L);
        booking.setRoom(room);

        when(bookingRepository.findById(1L)).thenReturn(Optional.of(booking));

        bookingService.cancelBooking(1L);

        verify(roomRepository, times(1)).save(room);
        verify(bookingRepository, times(1)).deleteById(1L);
    }

    @Test
    void testCancelBookingNotFound() {
        when(bookingRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            bookingService.cancelBooking(1L);
        });

        assertEquals("Booking with id 1 not found", exception.getMessage());
    }
}