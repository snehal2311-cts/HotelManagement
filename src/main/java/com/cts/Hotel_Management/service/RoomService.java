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
	 
	 public Room addRoom(Room room) {
		 return roomRepository.save(room);
	 }
	 
	 public void deleteRoom(Long roomId) {
		 roomRepository.deleteById(roomId);
	 }
	 public void updateRoom(Long id,Room room) {
		 Room roomOld=roomRepository.findById(id).orElseThrow(()-> new RuntimeException("Room not found"));
		 roomOld.setBooked(room.isBooked());
		 roomOld.setRoomPrice(room.getRoomPrice());
		 roomOld.setRoomType(room.getRoomType());
		 
		 
	 }
	 
	 public Room bookRoom(Long id) {
		 Room room=roomRepository.findById(id).orElseThrow(()->new RuntimeException("Room not found"));
		 if(room.isBooked()) {
			 throw new RuntimeException("Room is already booked");
		 }
		 room.setBooked(true);
		 return roomRepository.save(room);
	 }
}
