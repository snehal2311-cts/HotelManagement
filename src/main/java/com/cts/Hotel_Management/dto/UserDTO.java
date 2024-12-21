package com.cts.Hotel_Management.dto;


import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
   
   
    private Long id;

    @NotBlank(message = "Email is required")
    @Email(message="Invalid email")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Phone Number is required")
    @Pattern(regexp="^\\d{10}$",message="invalid phn number")
    private String phoneNumber;

    @NotBlank(message = "Password is required")
    @Size(min=4,message="Minimum 4 characters should be there")
    private String password;

    private String role;
    private List<BookingDTO> bookings = new ArrayList<>();

}