package br.com.eduardominarelli.restaurantsearch.filter;

import br.com.eduardominarelli.restaurantsearch.dto.RestaurantDTO;

public class RestaurantDistanceFilter extends AbstractFieldFilter {
    protected RestaurantDistanceFilter(Integer value) {
        super(value);
    }

    @Override
    public boolean filter(RestaurantDTO restaurantDTO) {
        return restaurantDTO.getDistance() <= this.getDistanceFilterValue();
    }

    private Integer getDistanceFilterValue() {
        return (Integer) this.getValue();
    }
}
