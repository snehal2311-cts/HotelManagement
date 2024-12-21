package com.cts.Hotel_Management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.Hotel_Management.entity.Room;
import com.cts.Hotel_Management.service.RoomService;

@RestController
@RequestMapping("/api")
public class RoomController {
@Autowired
private RoomService roomService;
//Admin 
//add rooms
    @PostMapping("/admin/add-room")
    public String addRoom(@RequestBody Room room) {
	roomService.addRoom(room);
	return "Room added successfully";
}
    
    //DeleteRoom
    @DeleteMapping("/admin/delete-room/{id}")
    public String deleteRoom(@PathVariable Long id) {
    	roomService.deleteRoom(id);
    	return "Room deleted successfully";
    }
    
 //update room
    @PutMapping("/admin/update-room/{id}")
    public String updateRoom(@PathVariable Long id,@RequestBody Room room) {
	roomService.updateRoom(id,room);
	return "Room updated successfully";
	
}

	//user or admin
	//to get all rooms

    @GetMapping("/get-rooms")
    public List<Room> getAllRooms(){
    	return roomService.getAllRooms();
    }
}
