package com.cts.Hotel_Management.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cts.Hotel_Management.entity.Booking;
import com.cts.Hotel_Management.entity.User;
import com.cts.Hotel_Management.entity.Room;
import com.cts.Hotel_Management.service.BookingService;

public class BookingControllerTest {

    @Mock
    private BookingService bookingService;

    @InjectMocks
    private BookingController bookingController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveBookings() {
        Long roomId = 1L;
        Long userId = 1L;
        Booking bookingRequest = new Booking(userId, null, null, 0, 0, 0, null, null);
        bookingRequest.setId(1L);
        bookingRequest.setCheckInDate(LocalDate.of(2024, 12, 26));
        bookingRequest.setCheckOutDate(LocalDate.of(2024, 12, 30));
        bookingRequest.setNumOfAdults(2);
        bookingRequest.setNumOfChildren(1);
        bookingRequest.setTotalNumOfGuest(3);

        User user = new User();
        user.setId(1L);
        user.setEmail("john.doe@example.com");
        user.setName("John Doe");
        user.setPhoneNumber("1234567890");
        user.setPassword("password");
        bookingRequest.setUser(user);

        Room room = new Room();
        room.setId(1L);
        room.setRoomType("Deluxe Room");
        room.setRoomPrice(BigDecimal.valueOf(200.00));
        room.setBooked(false);
        bookingRequest.setRoom(room);

        doNothing().when(bookingService).saveBooking(roomId, userId, bookingRequest);

        ResponseEntity<Booking> response = bookingController.saveBookings(roomId, userId, bookingRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(bookingRequest, response.getBody());
        verify(bookingService, times(1)).saveBooking(roomId, userId, bookingRequest);
    }

    @Test
    public void testGetAllBookings() {
        Booking booking1 = new Booking(null, null, null, 0, 0, 0, null, null);
        booking1.setId(1L);
        booking1.setCheckInDate(LocalDate.of(2024, 12, 26));
        booking1.setCheckOutDate(LocalDate.of(2024, 12, 30));
        booking1.setNumOfAdults(2);
        booking1.setNumOfChildren(1);
        booking1.setTotalNumOfGuest(3);

        User user1 = new User();
        user1.setId(1L);
        user1.setEmail("john.doe@example.com");
        user1.setName("John Doe");
        user1.setPhoneNumber("1234567890");
        user1.setPassword("password");
        booking1.setUser(user1);

        Room room1 = new Room();
        room1.setId(1L);
        room1.setRoomType("Deluxe Room");
        room1.setRoomPrice(BigDecimal.valueOf(200.00));
        room1.setBooked(false);
        booking1.setRoom(room1);

        Booking booking2 = new Booking(null, null, null, 0, 0, 0, user1, room1);
        booking2.setId(2L);
        booking2.setCheckInDate(LocalDate.of(2024, 12, 27));
        booking2.setCheckOutDate(LocalDate.of(2024, 12, 31));
        booking2.setNumOfAdults(1);
        booking2.setNumOfChildren(0);
        booking2.setTotalNumOfGuest(1);

        User user2 = new User();
        user2.setId(2L);
        user2.setEmail("jane.smith@example.com");
        user2.setName("Jane Smith");
        user2.setPhoneNumber("0987654321");
        user2.setPassword("password");
        booking2.setUser(user2);

        Room room2 = new Room();
        room2.setId(2L);
        room2.setRoomType("Standard Room");
        room2.setRoomPrice(BigDecimal.valueOf(150.00));
        room2.setBooked(false);
        booking2.setRoom(room2);

        List<Booking> bookings = Arrays.asList(booking1, booking2);

        when(bookingService.getAllBookings()).thenReturn(bookings);

        List<Booking> response = bookingController.getAllBookings();

        assertEquals(2, response.size());
        verify(bookingService, times(1)).getAllBookings();
    }

    @Test
    public void testCancelBooking() {
        Long bookingId = 1L;

        doNothing().when(bookingService).cancelBooking(bookingId);

        String response = bookingController.cancelBooking(bookingId);

        assertEquals("Booking deleted successfully!", response);
        verify(bookingService, times(1)).cancelBooking(bookingId);
    }
}