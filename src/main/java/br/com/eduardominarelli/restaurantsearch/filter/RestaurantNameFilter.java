package br.com.eduardominarelli.restaurantsearch.filter;

import br.com.eduardominarelli.restaurantsearch.dto.RestaurantDTO;

public class RestaurantNameFilter extends AbstractFieldFilter {

    public RestaurantNameFilter(String restaurantName) {
        super(restaurantName);
    }

    public boolean filter(RestaurantDTO restaurantDTO) {
        return restaurantDTO.getName().toLowerCase().contains(this.getValue().toString().toLowerCase());
    }
}
