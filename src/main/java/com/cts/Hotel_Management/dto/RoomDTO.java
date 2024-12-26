package com.cts.Hotel_Management.dto;


import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoomDTO {

    private Long id;

    @NotEmpty(message = "Room type is required")
    private String roomType;

    @NotNull(message = "Room price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Room price must be greater than 0")
    private BigDecimal roomPrice;
    
    private List<BookingDTO> bookings;
    
    @Size(max = 500, message = "Description must be less than 500 characters")
    private String description;

    @NotEmpty(message = "Amenities list cannot be empty")
    private List<String> amenities;

    @Min(value = 1, message = "Capacity must be at least 1")
    private int capacity;
}