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

    @Autowired
    RestaurantRepo restaurantRepo;

    public List<RestaurantDTO> getAllRestaurants() {
        List<Restaurant> restaurantList = restaurantRepo.findAll();
        // Return an empty list if no restaurants are found
        if (restaurantList.isEmpty()) {
            return new ArrayList<>();
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
}
