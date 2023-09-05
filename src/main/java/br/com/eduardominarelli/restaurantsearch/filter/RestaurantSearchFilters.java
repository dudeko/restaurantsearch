package br.com.eduardominarelli.restaurantsearch.filter;

import br.com.eduardominarelli.restaurantsearch.dto.RestaurantDTO;

public class RestaurantSearchFilters {

    private RestaurantNameFilter restaurantName = new RestaurantNameFilter(null);
    private RestaurantCustomerRatingFilter customerRating = new RestaurantCustomerRatingFilter(null);
    private RestaurantDistanceFilter distance = new RestaurantDistanceFilter(null);
    private RestaurantPriceFilter price = new RestaurantPriceFilter(null);
    private RestaurantCuisineFilter cuisine = new RestaurantCuisineFilter(null);

    public boolean filter(RestaurantDTO restaurantDTO) {
        return restaurantName.filterIfNecessary(restaurantDTO) &&
                customerRating.filterIfNecessary(restaurantDTO) &&
                distance.filterIfNecessary(restaurantDTO) &&
                price.filterIfNecessary(restaurantDTO) &&
                cuisine.filterIfNecessary(restaurantDTO);
    }

    public RestaurantNameFilter getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = new RestaurantNameFilter(restaurantName);
    }

    public RestaurantCustomerRatingFilter getCustomerRating() {
        return customerRating;
    }

    public void setCustomerRating(Integer customerRating) {
        this.customerRating = new RestaurantCustomerRatingFilter(customerRating);
    }

    public RestaurantDistanceFilter getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = new RestaurantDistanceFilter(distance);
    }

    public RestaurantPriceFilter getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = new RestaurantPriceFilter(price);
    }

    public RestaurantCuisineFilter getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = new RestaurantCuisineFilter(cuisine);
    }
}
