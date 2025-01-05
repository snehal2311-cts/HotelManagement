package com.cts.Hotel_Management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cts.Hotel_Management.dto.RoomDTO;
import com.cts.Hotel_Management.entity.Room;
import com.cts.Hotel_Management.service.RoomService;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@RestController
@RequestMapping("/api")
public class RoomController {
    private static final Logger logger = LoggerFactory.getLogger(RoomController.class);

    @Autowired
    private RoomService roomService;

    // Admin 
    // Add rooms
    @PostMapping("/admin/add-room")
    public String addRoom(@RequestBody @Valid RoomDTO room) {
        logger.info("Add room request received");
        roomService.addRoom(room);
        logger.info("Room added successfully: {}", room);
        return "Room added successfully";
    }

    // Delete Room
    @DeleteMapping("/admin/delete-room/{id}")
    public String deleteRoom(@PathVariable Long id) {
        logger.info("Delete room request received for roomId: {}", id);
        roomService.deleteRoom(id);
        logger.info("Room deleted successfully for roomId: {}", id);
        return "Room deleted successfully";
    }

    // Update room
    @PutMapping("/admin/update-room/{id}")
    public String updateRoom(@PathVariable Long id, @RequestBody @Valid Room room) {
        logger.info("Update room request received for roomId: {}", id);
        roomService.updateRoom(id, room);
        logger.info("Room updated successfully for roomId: {}", id);
        return "Room updated successfully";
    }

    // User or admin
    // Get all rooms
    @GetMapping("rooms/get-rooms")
    public List<RoomDTO> getAllRooms() {
        logger.info("Fetching all rooms");
        return roomService.getAllRooms();
    }

    // Get room by room type
    @GetMapping("/rooms/filterBy")
    public ResponseEntity<List<RoomDTO>> getRoomsByType(@RequestParam String roomType) {
        logger.info("Fetching rooms by type: {}", roomType);
        List<RoomDTO> rooms = roomService.getRoomsByroomType(roomType);
        if (rooms.isEmpty()) {
            logger.info("No rooms found for type: {}. Fetching all rooms.", roomType);
            rooms = roomService.getAllRooms();
        }
        return ResponseEntity.ok(rooms);
    }
    @GetMapping("/rooms/roomsPrice/high-low")
    public ResponseEntity<List<RoomDTO>> findByRoomPriceOrderByDesc() {
        logger.info("Fetching rooms by type: {}");
        List<RoomDTO> rooms = roomService.getRoomsOrderByRoomPriceDesc();
        if (rooms.isEmpty()) {
            logger.info("No rooms found for type: {}. Fetching all rooms.");
            rooms = roomService.getAllRooms();
        }
        return ResponseEntity.ok(rooms);
    }
}
