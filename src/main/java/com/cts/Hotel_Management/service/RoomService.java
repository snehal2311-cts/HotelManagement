package com.cts.Hotel_Management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.Hotel_Management.entity.Room;
import com.cts.Hotel_Management.repository.RoomRepository;

@Service
public class RoomService {

	
	 @Autowired
	 private RoomRepository roomRepository;
	 
	 public List<Room> getAllRooms(){
		 return roomRepository.findAll();
		 
	 }
}
