package com.cts.Hotel_Management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.Hotel_Management.entity.Booking;


@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

}
