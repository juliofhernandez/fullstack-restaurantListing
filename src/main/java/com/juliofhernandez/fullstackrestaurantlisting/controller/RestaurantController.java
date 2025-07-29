package com.juliofhernandez.fullstackrestaurantlisting.controller;

import com.juliofhernandez.fullstackrestaurantlisting.dto.RestaurantDTO;
import com.juliofhernandez.fullstackrestaurantlisting.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;

    @GetMapping("/getAllRestaurants")
    public ResponseEntity<List<RestaurantDTO>> getAllRestaurants() {
        List<RestaurantDTO> restaurantList = restaurantService.getAllRestaurants();
        if (restaurantList.isEmpty()) {
            return ResponseEntity.noContent().build(); // Return 204 No Content if the list is empty
        }
        return ResponseEntity.ok(restaurantList); // Return 200 OK with the list of restaurants
    }

    @PostMapping("/addRestaurant")
    public ResponseEntity<RestaurantDTO> addRestaurant(@RequestBody RestaurantDTO restaurantDTO) {
        RestaurantDTO createdRestaurant = restaurantService.addRestaurant(restaurantDTO);
        if (createdRestaurant == null) {
            return ResponseEntity.badRequest().build(); // Return 400 Bad Request if the restaurant could not be created
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRestaurant); // Return 201 Created with the created restaurant
    }

    @GetMapping("/getRestaurantById/{id}")
    public ResponseEntity<RestaurantDTO> getRestaurantById(@PathVariable int id) {
        RestaurantDTO restaurantDTO = restaurantService.getRestaurantById(id);
        if (restaurantDTO == null) {
            return ResponseEntity.notFound().build(); // Return 404 Not Found if the restaurant does not exist
        }
        return ResponseEntity.ok(restaurantDTO); // Return 200 OK with the restaurant details
    }
}
