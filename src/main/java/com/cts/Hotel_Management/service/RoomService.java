package com.cts.Hotel_Management.service;

import java.util.List;

import com.cts.Hotel_Management.dto.RoomDTO;
import com.cts.Hotel_Management.entity.Room;

//@Service
public interface RoomService {

	
	List<RoomDTO> getAllRooms();
	 Room addRoom(RoomDTO room);
	 void deleteRoom(Long roomId);
	 void updateRoom(Long id,Room room);
	 Room bookRoom(Long id);
	 List<RoomDTO>getRoomsByroomType(String roomType);
	 RoomDTO convertToDto(Room room);
	 Room convertToEntity(RoomDTO roomDTO);
	 
}
