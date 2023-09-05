package br.com.eduardominarelli.restaurantsearch.dto;

import br.com.eduardominarelli.restaurantsearch.csv.bean.RestaurantCsvBean;

public class RestaurantDTO {

    private String name;
    private Short customerRating;
    private Integer distance;
    private Integer price;
    private CuisineDTO cuisine;

    public static RestaurantDTO of(RestaurantCsvBean restaurantCsvBean) {
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        restaurantDTO.setName(restaurantCsvBean.getName());
        restaurantDTO.setCustomerRating(restaurantCsvBean.getCustomerRating());
        restaurantDTO.setDistance(restaurantCsvBean.getDistance());
        restaurantDTO.setPrice(restaurantCsvBean.getPrice());
        return restaurantDTO;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Short getCustomerRating() {
        return customerRating;
    }

    public void setCustomerRating(Short customerRating) {
        this.customerRating = customerRating;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public CuisineDTO getCuisine() {
        return cuisine;
    }

    public void setCuisine(CuisineDTO cuisine) {
        this.cuisine = cuisine;
    }

    public RestaurantDTO withCuisine(CuisineDTO cuisine) {
        this.setCuisine(cuisine);
        return this;
    }
}
