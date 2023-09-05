package br.com.eduardominarelli.restaurantsearch.filter;

import br.com.eduardominarelli.restaurantsearch.dto.RestaurantDTO;

public class RestaurantPriceFilter extends AbstractFieldFilter {
    protected RestaurantPriceFilter(Integer value) {
        super(value);
    }

    @Override
    public boolean filter(RestaurantDTO restaurantDTO) {
        return restaurantDTO.getPrice() <= this.getPriceFilterValue();
    }

    private Integer getPriceFilterValue() {
        return (Integer) this.getValue();
    }
}
