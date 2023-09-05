package br.com.eduardominarelli.restaurantsearch.controller;

import br.com.eduardominarelli.restaurantsearch.dto.RestaurantDTO;
import br.com.eduardominarelli.restaurantsearch.filter.RestaurantSearchFilters;
import br.com.eduardominarelli.restaurantsearch.service.RestaurantDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController(value = RestaurantSearchController.RESTAURANTSEARCH)
class RestaurantSearchController {

    public static final String RESTAURANTSEARCH = "restaurantsearch";
    public static final String SEARCH = "search";

    @Autowired
    private RestaurantDomainService restaurantDomainService;

    @PostMapping(SEARCH)
    List<RestaurantDTO> search(@RequestBody RestaurantSearchFilters restaurantSearchFilters) throws IOException {
        return restaurantDomainService.searchBasedOnFilters(restaurantSearchFilters);
    }

}

