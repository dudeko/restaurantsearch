package br.com.eduardominarelli.restaurantsearch.service;

import br.com.eduardominarelli.restaurantsearch.csv.CsvReaderServiceImpl;
import br.com.eduardominarelli.restaurantsearch.csv.bean.CsvBean;
import br.com.eduardominarelli.restaurantsearch.csv.bean.CuisineCsvBean;
import br.com.eduardominarelli.restaurantsearch.csv.bean.RestaurantCsvBean;
import br.com.eduardominarelli.restaurantsearch.dto.RestaurantDTO;
import br.com.eduardominarelli.restaurantsearch.filter.RestaurantSearchFilters;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static java.util.List.of;
import static org.mockito.ArgumentMatchers.any;

class RestaurantDomainServiceImplUnitTest {

    @InjectMocks
    private RestaurantDomainServiceImpl restaurantDomainService;

    @Mock
    private CsvReaderServiceImpl csvReaderServiceMock;
    @Mock
    private CuisineDomainServiceImpl cuisineDomainServiceMock;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void searchBasedOnFiltersShouldReturn1Restaurant() {
        //given
        CuisineCsvBean cuisineCsvBean = CuisineCsvBean.build().withId(1L).withName("Mexican");
        RestaurantCsvBean restaurantCsvBean = RestaurantCsvBean.build().withName("Bronny's Taco").withCuisineId(1L);
        //and
        Mockito.when(csvReaderServiceMock.read(any(String.class), any())).thenReturn(of(restaurantCsvBean));
        Mockito.when(cuisineDomainServiceMock.findById(1L)).thenReturn(cuisineCsvBean);
        RestaurantSearchFilters restaurantSearchFilters = new RestaurantSearchFilters();
        //when
        restaurantDomainService.loadAllFromCsv();
        List<RestaurantDTO> result = restaurantDomainService.searchBasedOnFilters(restaurantSearchFilters);
        //then
        Assertions.assertThat(result).hasSize(1);
    }

    @Test
    void searchBasedOnFiltersShouldReturnAtMost5Restaurants() {
        //given
        List<CsvBean> sixRestaurants = of(
                RestaurantCsvBean.build().withName("Expected Restaurant").withCuisineId(1L).withCustomerRating(5).withDistance(1).withPrice(25),
                RestaurantCsvBean.build().withName("Expected Restaurant").withCuisineId(1L).withCustomerRating(5).withDistance(1).withPrice(25),
                RestaurantCsvBean.build().withName("Expected Restaurant").withCuisineId(1L).withCustomerRating(5).withDistance(1).withPrice(25),
                RestaurantCsvBean.build().withName("Expected Restaurant").withCuisineId(1L).withCustomerRating(5).withDistance(1).withPrice(25),
                RestaurantCsvBean.build().withName("Expected Restaurant").withCuisineId(1L).withCustomerRating(5).withDistance(1).withPrice(25),
                RestaurantCsvBean.build().withName("Expected Restaurant").withCuisineId(1L).withCustomerRating(5).withDistance(1).withPrice(25)
        );
        //and
        Mockito.when(csvReaderServiceMock.read(any(String.class), any())).thenReturn(sixRestaurants);
        Mockito.when(cuisineDomainServiceMock.findById(any())).thenReturn(CuisineCsvBean.build());
        RestaurantSearchFilters restaurantSearchFilters = new RestaurantSearchFilters();
        //when
        restaurantDomainService.loadAllFromCsv();
        List<RestaurantDTO> result = restaurantDomainService.searchBasedOnFilters(restaurantSearchFilters);
        //then
        Assertions.assertThat(result).hasSize(5);
    }

    @Test
    void searchBasedOnFiltersShouldReturnOrderedByDistance() {
        //given
        List<CsvBean> restaurants = of(
                RestaurantCsvBean.build().withName("Bronny's Taco").withCuisineId(1L).withCustomerRating(5).withDistance(5).withPrice(25),
                RestaurantCsvBean.build().withName("5th Farthest Restaurant").withCuisineId(1L).withCustomerRating(5).withDistance(7).withPrice(25),
                RestaurantCsvBean.build().withName("Most Far Restaurant").withCuisineId(1L).withCustomerRating(5).withDistance(14).withPrice(25),
                RestaurantCsvBean.build().withName("Bronny's Taco").withCuisineId(1L).withCustomerRating(5).withDistance(3).withPrice(25),
                RestaurantCsvBean.build().withName("Closest Restaurant").withCuisineId(1L).withCustomerRating(5).withDistance(1).withPrice(25),
                RestaurantCsvBean.build().withName("Bronny's Taco").withCuisineId(1L).withCustomerRating(5).withDistance(2).withPrice(25),
                RestaurantCsvBean.build().withName("Bronny's Taco").withCuisineId(1L).withCustomerRating(5).withDistance(8).withPrice(25)
        );
        //and
        Mockito.when(csvReaderServiceMock.read(any(String.class), any())).thenReturn(restaurants);
        Mockito.when(cuisineDomainServiceMock.findById(any())).thenReturn(CuisineCsvBean.build());
        RestaurantSearchFilters restaurantSearchFilters = new RestaurantSearchFilters();
        //when
        restaurantDomainService.loadAllFromCsv();
        List<RestaurantDTO> result = restaurantDomainService.searchBasedOnFilters(restaurantSearchFilters);
        //then
        Assertions.assertThatList(result).first().hasFieldOrPropertyWithValue("name", "Closest Restaurant");
        Assertions.assertThatList(result).last().hasFieldOrPropertyWithValue("name", "5th Farthest Restaurant");
    }

    @Test
    void searchBasedOnFiltersShouldReturnOrderedByDistanceThenByRating() {
        //given
        List<CsvBean> restaurants = of(
                RestaurantCsvBean.build().withName("Worst Rated").withCuisineId(1L).withCustomerRating(2).withDistance(1).withPrice(10),
                RestaurantCsvBean.build().withName("Best Rated").withCuisineId(1L).withCustomerRating(5).withDistance(1).withPrice(20),
                RestaurantCsvBean.build().withName("Bronny's Taco").withCuisineId(1L).withCustomerRating(3).withDistance(1).withPrice(30),
                RestaurantCsvBean.build().withName("Bronny's Taco").withCuisineId(1L).withCustomerRating(4).withDistance(1).withPrice(40)
        );
        //and
        Mockito.when(csvReaderServiceMock.read(any(String.class), any())).thenReturn(restaurants);
        Mockito.when(cuisineDomainServiceMock.findById(any())).thenReturn(CuisineCsvBean.build());
        RestaurantSearchFilters restaurantSearchFilters = new RestaurantSearchFilters();
        //when
        restaurantDomainService.loadAllFromCsv();
        List<RestaurantDTO> result = restaurantDomainService.searchBasedOnFilters(restaurantSearchFilters);
        //then
        Assertions.assertThatList(result).first().hasFieldOrPropertyWithValue("name", "Best Rated");
        Assertions.assertThatList(result).last().hasFieldOrPropertyWithValue("name", "Worst Rated");
    }

    @Test
    void searchBasedOnFiltersShouldReturnOrderedByDistanceThenByRatingThenByPrice() {
        //given
        List<CsvBean> sixRestaurants = of(
                RestaurantCsvBean.build().withName("Bronny's Taco").withCuisineId(1L).withCustomerRating(5).withDistance(1).withPrice(120),
                RestaurantCsvBean.build().withName("Most Expensive").withCuisineId(1L).withCustomerRating(5).withDistance(1).withPrice(200),
                RestaurantCsvBean.build().withName("Bronny's Taco").withCuisineId(1L).withCustomerRating(5).withDistance(1).withPrice(70),
                RestaurantCsvBean.build().withName("Cheapest").withCuisineId(1L).withCustomerRating(5).withDistance(1).withPrice(25)
        );
        //and
        Mockito.when(csvReaderServiceMock.read(any(String.class), any())).thenReturn(sixRestaurants);
        Mockito.when(cuisineDomainServiceMock.findById(any())).thenReturn(CuisineCsvBean.build());
        RestaurantSearchFilters restaurantSearchFilters = new RestaurantSearchFilters();
        //when
        restaurantDomainService.loadAllFromCsv();
        List<RestaurantDTO> result = restaurantDomainService.searchBasedOnFilters(restaurantSearchFilters);
        //then
        Assertions.assertThatList(result).first().hasFieldOrPropertyWithValue("name", "Cheapest");
        Assertions.assertThatList(result).last().hasFieldOrPropertyWithValue("name", "Most Expensive");
    }

    @Test
    void searchBasedOnFiltersShouldReturnRestaurantsMatchingName() {
        //given
        List<CsvBean> restaurantList = of(
                RestaurantCsvBean.build().withName("Wrong Restaurant").withCuisineId(1L).withCustomerRating(5).withDistance(1).withPrice(25),
                RestaurantCsvBean.build().withName("Wrong Restaurant").withCuisineId(1L).withCustomerRating(5).withDistance(1).withPrice(25),
                RestaurantCsvBean.build().withName("Wrong Restaurant").withCuisineId(1L).withCustomerRating(5).withDistance(1).withPrice(25),
                RestaurantCsvBean.build().withName("Expected Restaurant").withCuisineId(1L).withCustomerRating(5).withDistance(1).withPrice(25),
                RestaurantCsvBean.build().withName("Wrong Restaurant").withCuisineId(1L).withCustomerRating(5).withDistance(1).withPrice(25),
                RestaurantCsvBean.build().withName("Wrong Restaurant").withCuisineId(1L).withCustomerRating(5).withDistance(1).withPrice(25)
        );
        //and
        Mockito.when(csvReaderServiceMock.read(any(String.class), any())).thenReturn(restaurantList);
        Mockito.when(cuisineDomainServiceMock.findById(any())).thenReturn(CuisineCsvBean.build());
        RestaurantSearchFilters restaurantSearchFilters = new RestaurantSearchFilters();
        restaurantSearchFilters.setRestaurantName("Expected");
        //when
        restaurantDomainService.loadAllFromCsv();
        List<RestaurantDTO> result = restaurantDomainService.searchBasedOnFilters(restaurantSearchFilters);
        //then
        Assertions.assertThat(result).hasSize(1);
    }

    @Test
    void searchBasedOnFiltersShouldReturnRestaurantsMatchingCuisineName() {
        //given
        CuisineCsvBean specificCuisine = CuisineCsvBean.build().withId(10L).withName("Specific Cuisine");
        CuisineCsvBean specificCuisine2 = CuisineCsvBean.build().withId(11L).withName("Specific Cuisine 2");
        CuisineCsvBean otherCuisine = CuisineCsvBean.build().withId(1L).withName("Other Cuisine");
        List<CsvBean> restaurantList = of(
                RestaurantCsvBean.build().withName("Wrong Restaurant").withCuisineId(1L).withCustomerRating(5).withDistance(1).withPrice(25),
                RestaurantCsvBean.build().withName("Expected Restaurant").withCuisineId(11L).withCustomerRating(5).withDistance(1).withPrice(25),
                RestaurantCsvBean.build().withName("Wrong Restaurant").withCuisineId(1L).withCustomerRating(5).withDistance(1).withPrice(25),
                RestaurantCsvBean.build().withName("Expected Restaurant").withCuisineId(10L).withCustomerRating(5).withDistance(1).withPrice(25),
                RestaurantCsvBean.build().withName("Wrong Restaurant").withCuisineId(1L).withCustomerRating(5).withDistance(1).withPrice(25),
                RestaurantCsvBean.build().withName("Wrong Restaurant").withCuisineId(1L).withCustomerRating(5).withDistance(1).withPrice(25)
        );
        //and
        Mockito.when(csvReaderServiceMock.read(any(String.class), any())).thenReturn(restaurantList);
        Mockito.when(cuisineDomainServiceMock.findById(1L)).thenReturn(otherCuisine);
        Mockito.when(cuisineDomainServiceMock.findById(10L)).thenReturn(specificCuisine);
        Mockito.when(cuisineDomainServiceMock.findById(11L)).thenReturn(specificCuisine2);
        RestaurantSearchFilters restaurantSearchFilters = new RestaurantSearchFilters();
        restaurantSearchFilters.setCuisine("specific");
        //when
        restaurantDomainService.loadAllFromCsv();
        List<RestaurantDTO> result = restaurantDomainService.searchBasedOnFilters(restaurantSearchFilters);
        //then
        Assertions.assertThat(result).hasSize(2);
        Assertions.assertThatList(result).first().hasFieldOrPropertyWithValue("name", "Expected Restaurant");
        Assertions.assertThatList(result).last().hasFieldOrPropertyWithValue("name", "Expected Restaurant");
    }

    @Test
    void searchBasedOnFiltersShouldReturnRestaurantsOverSpecifiedCustomerRating() {
        //given
        List<CsvBean> restaurantList = of(
                RestaurantCsvBean.build().withName("Wrong Restaurant").withCuisineId(1L).withCustomerRating(1).withDistance(1).withPrice(25),
                RestaurantCsvBean.build().withName("Expected Restaurant").withCuisineId(1L).withCustomerRating(5).withDistance(1).withPrice(25),
                RestaurantCsvBean.build().withName("Wrong Restaurant").withCuisineId(1L).withCustomerRating(2).withDistance(1).withPrice(25),
                RestaurantCsvBean.build().withName("Expected Restaurant").withCuisineId(1L).withCustomerRating(5).withDistance(1).withPrice(25),
                RestaurantCsvBean.build().withName("Wrong Restaurant").withCuisineId(1L).withCustomerRating(3).withDistance(1).withPrice(25),
                RestaurantCsvBean.build().withName("Expected Restaurant").withCuisineId(1L).withCustomerRating(5).withDistance(1).withPrice(25),
                RestaurantCsvBean.build().withName("Expected Restaurant").withCuisineId(1L).withCustomerRating(4).withDistance(1).withPrice(25)
        );
        //and
        Mockito.when(csvReaderServiceMock.read(any(String.class), any())).thenReturn(restaurantList);
        Mockito.when(cuisineDomainServiceMock.findById(any())).thenReturn(CuisineCsvBean.build());
        RestaurantSearchFilters restaurantSearchFilters = new RestaurantSearchFilters();
        restaurantSearchFilters.setCustomerRating(4);
        //when
        restaurantDomainService.loadAllFromCsv();
        List<RestaurantDTO> result = restaurantDomainService.searchBasedOnFilters(restaurantSearchFilters);
        //then
        Assertions.assertThat(result).hasSize(4);
        Assertions.assertThatList(result).allMatch(restaurantDTO -> restaurantDTO.getName().equals("Expected Restaurant"));
        Assertions.assertThatList(result).allMatch(restaurantDTO -> restaurantDTO.getCustomerRating() >= 4);
    }

    @Test
    void searchBasedOnFiltersShouldReturnRestaurantsUnderSpecifiedDistance() {
        //given
        List<CsvBean> restaurantList = of(
                RestaurantCsvBean.build().withName("Wrong Restaurant").withCuisineId(1L).withCustomerRating(5).withDistance(50).withPrice(25),
                RestaurantCsvBean.build().withName("Expected Restaurant").withCuisineId(1L).withCustomerRating(5).withDistance(2).withPrice(25),
                RestaurantCsvBean.build().withName("Expected Restaurant").withCuisineId(1L).withCustomerRating(5).withDistance(1).withPrice(25),
                RestaurantCsvBean.build().withName("Expected Restaurant").withCuisineId(1L).withCustomerRating(5).withDistance(3).withPrice(25),
                RestaurantCsvBean.build().withName("Wrong Restaurant").withCuisineId(1L).withCustomerRating(5).withDistance(4).withPrice(25),
                RestaurantCsvBean.build().withName("Expected Restaurant").withCuisineId(1L).withCustomerRating(5).withDistance(3).withPrice(25),
                RestaurantCsvBean.build().withName("Expected Restaurant").withCuisineId(1L).withCustomerRating(5).withDistance(1).withPrice(25),
                RestaurantCsvBean.build().withName("Wrong Restaurant").withCuisineId(1L).withCustomerRating(5).withDistance(99).withPrice(25)
        );
        //and
        Mockito.when(csvReaderServiceMock.read(any(String.class), any())).thenReturn(restaurantList);
        Mockito.when(cuisineDomainServiceMock.findById(any())).thenReturn(CuisineCsvBean.build());
        RestaurantSearchFilters restaurantSearchFilters = new RestaurantSearchFilters();
        restaurantSearchFilters.setDistance(3);
        //when
        restaurantDomainService.loadAllFromCsv();
        List<RestaurantDTO> result = restaurantDomainService.searchBasedOnFilters(restaurantSearchFilters);
        //then
        Assertions.assertThat(result).hasSize(5);
        Assertions.assertThatList(result).allMatch(restaurantDTO -> restaurantDTO.getName().equals("Expected Restaurant"));
        Assertions.assertThatList(result).allMatch(restaurantDTO -> restaurantDTO.getDistance() < 4);
    }

    @Test
    void searchBasedOnFiltersShouldReturnRestaurantsUnderSpecifiedPrice() {
        //given
        List<CsvBean> restaurantList = of(
                RestaurantCsvBean.build().withName("Expected Restaurant").withCuisineId(1L).withCustomerRating(5).withDistance(1).withPrice(10),
                RestaurantCsvBean.build().withName("Expected Restaurant").withCuisineId(1L).withCustomerRating(5).withDistance(1).withPrice(30),
                RestaurantCsvBean.build().withName("Expected Restaurant").withCuisineId(1L).withCustomerRating(5).withDistance(1).withPrice(25),
                RestaurantCsvBean.build().withName("Wrong Restaurant").withCuisineId(1L).withCustomerRating(5).withDistance(1).withPrice(100),
                RestaurantCsvBean.build().withName("Wrong Restaurant").withCuisineId(1L).withCustomerRating(5).withDistance(1).withPrice(31),
                RestaurantCsvBean.build().withName("Expected Restaurant").withCuisineId(1L).withCustomerRating(5).withDistance(1).withPrice(1)
        );
        //and
        Mockito.when(csvReaderServiceMock.read(any(String.class), any())).thenReturn(restaurantList);
        Mockito.when(cuisineDomainServiceMock.findById(any())).thenReturn(CuisineCsvBean.build());
        RestaurantSearchFilters restaurantSearchFilters = new RestaurantSearchFilters();
        restaurantSearchFilters.setPrice(30);
        //when
        restaurantDomainService.loadAllFromCsv();
        List<RestaurantDTO> result = restaurantDomainService.searchBasedOnFilters(restaurantSearchFilters);
        //then
        Assertions.assertThat(result).hasSize(4);
        Assertions.assertThatList(result).allMatch(restaurantDTO -> restaurantDTO.getName().equals("Expected Restaurant"));
        Assertions.assertThatList(result).allMatch(restaurantDTO -> restaurantDTO.getPrice() <= 30);
    }

    @Test
    void searchBasedOnFiltersShouldReturnRestaurantsThaMatchAllFilters() {
        //given
        CuisineCsvBean specificCuisine = CuisineCsvBean.build().withId(10L).withName("Specific Cuisine");
        CuisineCsvBean specificCuisine2 = CuisineCsvBean.build().withId(11L).withName("Specific Cuisine 2");
        CuisineCsvBean otherCuisine = CuisineCsvBean.build().withId(1L).withName("Other Cuisine");
        List<CsvBean> restaurantList = of(
                RestaurantCsvBean.build().withName("Expected Restaurant").withCuisineId(11L).withCustomerRating(5).withDistance(1).withPrice(25),
                RestaurantCsvBean.build().withName("Wrong Restaurant").withCuisineId(1L).withCustomerRating(1).withDistance(1).withPrice(25),
                RestaurantCsvBean.build().withName("Wrong Restaurant").withCuisineId(1L).withCustomerRating(2).withDistance(1).withPrice(25),
                RestaurantCsvBean.build().withName("Wrong Restaurant").withCuisineId(1L).withCustomerRating(3).withDistance(1).withPrice(25),
                RestaurantCsvBean.build().withName("The Other Expected Restaurant").withCuisineId(10L).withCustomerRating(5).withDistance(1).withPrice(25),
                RestaurantCsvBean.build().withName("Wrong Restaurant").withCuisineId(10L).withCustomerRating(5).withDistance(1).withPrice(25)
        );
        //and
        Mockito.when(csvReaderServiceMock.read(any(String.class), any())).thenReturn(restaurantList);
        Mockito.when(cuisineDomainServiceMock.findById(1L)).thenReturn(otherCuisine);
        Mockito.when(cuisineDomainServiceMock.findById(10L)).thenReturn(specificCuisine);
        Mockito.when(cuisineDomainServiceMock.findById(11L)).thenReturn(specificCuisine2);
        RestaurantSearchFilters restaurantSearchFilters = new RestaurantSearchFilters();
        restaurantSearchFilters.setRestaurantName("expected");
        restaurantSearchFilters.setCuisine("specific");
        restaurantSearchFilters.setCustomerRating(5);
        restaurantSearchFilters.setDistance(2);
        restaurantSearchFilters.setPrice(25);
        //when
        restaurantDomainService.loadAllFromCsv();
        List<RestaurantDTO> result = restaurantDomainService.searchBasedOnFilters(restaurantSearchFilters);
        //then
        Assertions.assertThat(result).hasSize(2);
        Assertions.assertThatList(result).first().hasFieldOrPropertyWithValue("name", "Expected Restaurant");
        Assertions.assertThatList(result).last().hasFieldOrPropertyWithValue("name", "The Other Expected Restaurant");
    }
}