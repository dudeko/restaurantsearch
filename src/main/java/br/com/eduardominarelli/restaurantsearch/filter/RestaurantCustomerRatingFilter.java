package br.com.eduardominarelli.restaurantsearch.filter;

import br.com.eduardominarelli.restaurantsearch.dto.RestaurantDTO;

public class RestaurantCustomerRatingFilter extends AbstractFieldFilter {

    protected RestaurantCustomerRatingFilter(Integer value) {
        super(value);
    }

    @Override
    public boolean filter(RestaurantDTO restaurantDTO) {
        return restaurantDTO.getCustomerRating() >= this.getCustomerRatingFilterValue();
    }

    private Integer getCustomerRatingFilterValue() {
        return (Integer) this.getValue();
    }
}
