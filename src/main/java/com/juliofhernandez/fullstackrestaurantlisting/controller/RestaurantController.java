package com.juliofhernandez.fullstackrestaurantlisting.controller;

import com.juliofhernandez.fullstackrestaurantlisting.dto.RestaurantDTO;
import com.juliofhernandez.fullstackrestaurantlisting.service.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
@Slf4j
@CrossOrigin // Allow cross-origin requests for this controller, e.g. from the frontend Angular application
public class RestaurantController {

    private RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/getAllRestaurants")
    public ResponseEntity<List<RestaurantDTO>> getAllRestaurants() {
        log.info("Fetching all restaurants");
        List<RestaurantDTO> restaurantList = restaurantService.getAllRestaurants();
        if (restaurantList.isEmpty()) {
            return ResponseEntity.noContent().build(); // Return 204 No Content if the list is empty
        }
        return ResponseEntity.ok(restaurantList); // Return 200 OK with the list of restaurants
    }

    @PostMapping("/addRestaurant")
    public ResponseEntity<RestaurantDTO> addRestaurant(@RequestBody RestaurantDTO restaurantDTO) {
        log.info("Adding new restaurant: {}", restaurantDTO);
        RestaurantDTO createdRestaurant = restaurantService.addRestaurant(restaurantDTO);
        if (createdRestaurant == null) {
            return ResponseEntity.badRequest().build(); // Return 400 Bad Request if the restaurant could not be created
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRestaurant); // Return 201 Created with the created restaurant
    }

    @GetMapping("/getRestaurantById/{id}")
    public ResponseEntity<RestaurantDTO> getRestaurantById(@PathVariable int id) {
        log.info("Fetching restaurant with ID: {}", id);
        RestaurantDTO restaurantDTO = restaurantService.getRestaurantById(id);
        if (restaurantDTO == null) {
            return ResponseEntity.notFound().build(); // Return 404 Not Found if the restaurant does not exist
        }
        return ResponseEntity.ok(restaurantDTO); // Return 200 OK with the restaurant details
    }

    @DeleteMapping("/deleteRestaurantById/{id}")
    public ResponseEntity<Void> deleteRestaurantById(@PathVariable int id) {
        log.info("Deleting restaurant with ID: {}", id);
        boolean isDeleted = restaurantService.deleteRestaurantById(id);
        if (!isDeleted) {
            return ResponseEntity.notFound().build(); // Return 404 Not Found if the restaurant does not exist
        }
        return ResponseEntity.noContent().build(); // Return 204 No Content if the restaurant was successfully deleted
    }
}
