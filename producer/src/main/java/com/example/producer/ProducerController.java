package com.example.producer;

import java.util.HashMap;
import javax.annotation.PostConstruct;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {
	HashMap<String, String> avengersMap = new HashMap<>();

	@PostConstruct
	public void initialize() {
		avengersMap.put("Thor", "Chris Hemsworth");
		avengersMap.put("Hulk", "Mark Ruffalo");
		avengersMap.put("Iron Man", "Robert Downey Jr.");
		avengersMap.put("Captain America", "Chris Evans");
		avengersMap.put("Black Widow", "Scarlet Johansson");
		avengersMap.put("Hawkeye", "Jeremy Renner");
	}

	@GetMapping(value = "/avengers") 
	@ResponseBody
	public HashMap<String, String> readAvengers() {
		return avengersMap;
	}

	@GetMapping(value = "/avengers/{id}")
	@ResponseBody
	public String readAvenger(@PathVariable String id) {
		return avengersMap.get(id);
	}

	@PutMapping(value = "/avengers/{id}")
	public void updateAvenger(@PathVariable String id, @RequestParam(value = "name") String name) {
		avengersMap.put(id, name);
	}

	@DeleteMapping(value = "/avengers/{id}")
	public void deleteAvenger(@PathVariable String id) {
		avengersMap.remove(id);
	}

	@PostMapping(value = "/avengers")
	public void createAvengers(@RequestBody HashMap<String, String> requestMap) {
		avengersMap = requestMap;
	}
}
