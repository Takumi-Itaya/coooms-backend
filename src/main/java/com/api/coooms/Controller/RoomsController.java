package com.api.coooms.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.coooms.Model.Rooms;
import com.api.coooms.Repository.RoomsRepository;


@RestController
@CrossOrigin(origins = "https://coooms.com")
public class RoomsController {
	
	@Autowired
    RoomsRepository roomsRepository;
	
	@GetMapping("/rooms")
	public List<Rooms> getUsers() {
		List<Rooms> rooms = roomsRepository.findAll();
		return rooms;
	}
	
	@GetMapping(path = "/room", params = "name")
	public Rooms getUserByEmail(@RequestParam("name") String name) {
		Rooms room = roomsRepository.findByName(name);
		return room;
	}
}
