package br.com.eduardominarelli.restaurantsearch.filter;

import br.com.eduardominarelli.restaurantsearch.dto.RestaurantDTO;

public class RestaurantCuisineFilter extends AbstractFieldFilter {

    public RestaurantCuisineFilter(String value) {
        super(value);
    }

    @Override
    public boolean filter(RestaurantDTO restaurantDTO) {
        return restaurantDTO.getCuisine().getName().toLowerCase().contains(this.getValue().toString().toLowerCase());
    }
}
