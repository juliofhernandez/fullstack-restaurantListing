package com.juliofhernandez.fullstackrestaurantlisting.mapper;

import com.juliofhernandez.fullstackrestaurantlisting.dto.RestaurantDTO;
import com.juliofhernandez.fullstackrestaurantlisting.entity.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RestaurantMapper {

    // Create an instance of the mapper
    RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);

    /*
        The methods below will be automatically implemented by MapStruct.
        You do not need to provide the implementation.
        MapStruct will generate the code to map between Restaurant and RestaurantDTO.
    */
    Restaurant mapRestaurantDTOToRestaurant(RestaurantDTO restaurantDTO);
    RestaurantDTO mapRestaurantToRestaurantDTO(Restaurant restaurant);
}
