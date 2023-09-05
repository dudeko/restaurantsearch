package br.com.eduardominarelli.restaurantsearch.service;

import br.com.eduardominarelli.restaurantsearch.dto.RestaurantDTO;
import br.com.eduardominarelli.restaurantsearch.filter.RestaurantSearchFilters;

import java.io.IOException;
import java.util.List;

public interface RestaurantDomainService {

    List<RestaurantDTO> searchBasedOnFilters(RestaurantSearchFilters restaurantSearchFilters) throws IOException;
}
