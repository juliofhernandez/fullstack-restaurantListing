package com.juliofhernandez.fullstackrestaurantlisting.service;

import com.juliofhernandez.fullstackrestaurantlisting.dto.RestaurantDTO;
import com.juliofhernandez.fullstackrestaurantlisting.entity.Restaurant;
import com.juliofhernandez.fullstackrestaurantlisting.mapper.RestaurantMapper;
import com.juliofhernandez.fullstackrestaurantlisting.repo.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    private RestaurantRepo restaurantRepo;

    public RestaurantService(RestaurantRepo restaurantRepo) {
        this.restaurantRepo = restaurantRepo;
    }

    public List<RestaurantDTO> getAllRestaurants() {
        List<Restaurant> restaurantList = restaurantRepo.findAll();
        // Return an empty list if no restaurants are found
        if (restaurantList.isEmpty()) {
            return  List.of(); // Return an empty list if no restaurants are found
        }
        // Map the list of Restaurant entities to a list of RestaurantDTOs
        List<RestaurantDTO> restaurantDTOList = restaurantList.stream().map(restaurant -> RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(restaurant)).collect(Collectors.toList());
        return restaurantDTOList;
    }

    public RestaurantDTO addRestaurant(RestaurantDTO restaurantDTO) {
        // Map the RestaurantDTO to a Restaurant entity
        Restaurant restaurant = RestaurantMapper.INSTANCE.mapRestaurantDTOToRestaurant(restaurantDTO);
        // Save the restaurant entity to the database
        Restaurant savedRestaurant = restaurantRepo.save(restaurant);
        // Map the saved Restaurant entity back to a RestaurantDTO
        return RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(savedRestaurant);
    }

    public RestaurantDTO getRestaurantById(int id) {
        // Find the restaurant by ID
        Restaurant restaurant = restaurantRepo.findById(id).orElse(null);
        // If the restaurant is not found, return null
        if (restaurant == null) {
            return null;
        }
        // Map the found Restaurant entity to a RestaurantDTO
        return RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(restaurant);
    }

    public boolean deleteRestaurantById(int id) {
        // Check if the restaurant exists by ID
        if (!restaurantRepo.existsById(id)) {
            return false; // Return false if the restaurant does not exist
        }
        // Delete the restaurant by ID
        restaurantRepo.deleteById(id);
        return true; // Return true if the restaurant was successfully deleted
    }
}
