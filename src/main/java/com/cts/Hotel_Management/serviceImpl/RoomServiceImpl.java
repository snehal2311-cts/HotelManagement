package com.cts.Hotel_Management.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.Hotel_Management.dto.RoomDTO;
import com.cts.Hotel_Management.entity.Booking;
import com.cts.Hotel_Management.entity.Room;
import com.cts.Hotel_Management.repository.RoomRepository;
import com.cts.Hotel_Management.service.RoomService;

@Service
public class RoomServiceImpl implements RoomService {
	@Autowired
	 private RoomRepository roomRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	 
	@Override
	 public List<RoomDTO> getAllRooms(){
		List<Room> rooms=roomRepository.findAll();
		  return rooms.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
		 
	 }
	 
	@Override
	 public Room addRoom(RoomDTO roomDTO) {
		Room room=convertToEntity(roomDTO);
		 return roomRepository.save(room);
	 }
	 
	@Override
	 public void deleteRoom(Long roomId) {
		 roomRepository.deleteById(roomId);
	 }
	
	@Override
	 public void updateRoom(Long id,Room room) {
		 Room roomOld=roomRepository.findById(id).orElseThrow(()-> new RuntimeException("Room not found"));
		 roomOld.setBooked(room.isBooked());
		 roomOld.setRoomPrice(room.getRoomPrice());
		// roomOld.setRoomType(room.getRoomType());
		 roomRepository.save(roomOld);
		 
		 
	 }
	
	
	 @Override
	 public Room bookRoom(Long id) {
		 Room room=roomRepository.findById(id).orElseThrow(()->new RuntimeException("Room not found"));
		 if(room.isBooked()) {
			 throw new RuntimeException("Room is already booked");
		 }
		 room.setBooked(true);
		 return roomRepository.save(room);
	 }

	@Override
	public List<RoomDTO> getRoomsByroomType(String roomType) {
		// TODO Auto-generated method stub	
		List<Room> rooms=roomRepository.findByRoomType(roomType);
		 return rooms.stream()
	                .map(this::convertToDto)
	                .collect(Collectors.toList());
	}
	
	@Override
	public RoomDTO convertToDto(Room room) {
		return modelMapper.map(room, RoomDTO.class);
	}
	@Override
	public Room convertToEntity(RoomDTO roomDTO) {
		return modelMapper.map(roomDTO, Room.class);
	}

	@Override
	public List<RoomDTO> getRoomsOrderByRoomPriceDesc() {
		List<Room> rooms=roomRepository.findAllByOrderByRoomPriceDesc();
		 return rooms.stream()
	                .map(this::convertToDto)
	                .collect(Collectors.toList());
	}
}
