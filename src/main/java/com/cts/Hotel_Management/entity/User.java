package com.cts.Hotel_Management.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.cts.Hotel_Management.dto.BookingDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class User {
	
	

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	    private String email;
	    private String name;
	    private String phoneNumber;
	    private String password;
//	    private String role;

	    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	    private List<Booking> bookings = new ArrayList<>();
	    
	    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	    @JoinTable(joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
	    inverseJoinColumns = @JoinColumn(name = "roles_id",referencedColumnName = "id"))
	    private Set<Role> role;
//	    private List<BookingDTO> bookings = new ArrayList<>();

}
