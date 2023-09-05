package br.com.eduardominarelli.restaurantsearch.filter;

import br.com.eduardominarelli.restaurantsearch.dto.RestaurantDTO;

public abstract class AbstractFieldFilter {
    private Object value;

    protected AbstractFieldFilter(Object value) {
        this.value = value;
    }

    public abstract boolean filter(RestaurantDTO restaurantDTO);

    public boolean filterIfNecessary(RestaurantDTO restaurantDTO) {
        return this.getValue() == null || this.filter(restaurantDTO);
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
