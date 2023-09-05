package br.com.eduardominarelli.restaurantsearch.csv.bean;

import com.opencsv.bean.CsvBindByName;

public class RestaurantCsvBean extends CsvBean {

    @CsvBindByName
    private String name;
    @CsvBindByName(column = "customer_rating")
    private Short customerRating;
    @CsvBindByName
    private Integer distance;
    @CsvBindByName
    private Integer price;
    @CsvBindByName(column = "cuisine_id")
    private Long cuisineId;

    public static RestaurantCsvBean build() {
        return new RestaurantCsvBean();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RestaurantCsvBean withName(String name) {
        this.setName(name);
        return this;
    }

    public Short getCustomerRating() {
        return customerRating;
    }

    public void setCustomerRating(Short customerRating) {
        this.customerRating = customerRating;
    }

    public RestaurantCsvBean withCustomerRating(Integer customerRating) {
        this.setCustomerRating(customerRating.shortValue());
        return this;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public RestaurantCsvBean withDistance(Integer distance) {
        this.setDistance(distance);
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public RestaurantCsvBean withPrice(Integer price) {
        this.setPrice(price);
        return this;
    }

    public Long getCuisineId() {
        return cuisineId;
    }

    public void setCuisineId(Long cuisineId) {
        this.cuisineId = cuisineId;
    }

    public RestaurantCsvBean withCuisineId(Long cuisineId) {
        this.setCuisineId(cuisineId);
        return this;
    }
}
