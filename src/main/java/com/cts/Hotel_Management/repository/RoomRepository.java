package com.cts.Hotel_Management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.Hotel_Management.entity.Room;


@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

}
