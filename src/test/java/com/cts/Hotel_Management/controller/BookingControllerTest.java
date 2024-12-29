package com.cts.Hotel_Management.controller;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import org.springframework.security.test.context.support.WithMockUser;

import com.cts.Hotel_Management.dto.BookingDTO;
import com.cts.Hotel_Management.service.BookingService;

public class BookingControllerTest {

    @InjectMocks
    private BookingController bookingController;

    @Mock
    private BookingService bookingService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @WithMockUser(roles = "ROLE_USER")
    public void testSaveBookings() {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setRoomId(1L);
        bookingDTO.setUserId(1L);
        bookingDTO.setCheckInDate(LocalDate.of(2024, 12, 10));
        bookingDTO.setCheckOutDate(LocalDate.of(2024, 12, 12));
        bookingDTO.setNumOfAdults(2);
        bookingDTO.setNumOfChildren(1);

        doNothing().when(bookingService).saveBooking(anyLong(), anyLong(), any(BookingDTO.class));

        ResponseEntity<BookingDTO> response = bookingController.saveBookings(1L, 1L, bookingDTO);

        verify(bookingService).saveBooking(1L, 1L, bookingDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(bookingDTO, response.getBody());
    }

    @Test
    @WithMockUser(roles = "ROLE_ADMIN")
    public void testGetAllBookings() {
        BookingDTO booking1 = new BookingDTO();
        booking1.setId(1L);
        BookingDTO booking2 = new BookingDTO();
        booking2.setId(2L);

        List<BookingDTO> bookings = Arrays.asList(booking1, booking2);
        when(bookingService.getAllBookings()).thenReturn(bookings);

        List<BookingDTO> response = bookingController.getAllBookings();

        verify(bookingService).getAllBookings();
        assertEquals(2, response.size());
        assertEquals(1L, response.get(0).getId());
        assertEquals(2L, response.get(1).getId());
    }

    @Test
    @WithMockUser(roles = "ROLE_ADMIN")
    public void testCancelBooking() {
        doNothing().when(bookingService).cancelBooking(anyLong());

        String response = bookingController.cancelBooking(1L);

        verify(bookingService).cancelBooking(1L);
        assertEquals("Booking deleted successfully!", response);
    }
}