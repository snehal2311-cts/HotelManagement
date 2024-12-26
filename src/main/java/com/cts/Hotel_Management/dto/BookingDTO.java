package com.cts.Hotel_Management.dto;

import java.time.LocalDate;

import com.cts.Hotel_Management.entity.Room;
import com.cts.Hotel_Management.entity.User;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {

//    public BookingDTO(Long roomId, Long userId, LocalDate checkInDate2, LocalDate checkOutDate2, int numOfAdults2,
//			int numOfChildren2) {
//		// TODO Auto-generated constructor stub
//	}
	private Long id;
    @NotNull(message = "check in date is required")
    private LocalDate checkInDate;

    @NotNull(message = "check out date must be in the future")
    private LocalDate checkOutDate;

    @Min(value = 1, message = "Number of adults must not be less that 1")
    private int numOfAdults;

    @Min(value = 0, message = "Number of children must not be less that 0")
    private int numOfChildren;

    private int totalNumOfGuest;
    private Long userId;
    private Long roomId;
    
}