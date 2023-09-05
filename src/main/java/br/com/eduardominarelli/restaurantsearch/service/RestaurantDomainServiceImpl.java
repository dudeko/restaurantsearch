package br.com.eduardominarelli.restaurantsearch.service;

import br.com.eduardominarelli.restaurantsearch.csv.CsvReaderService;
import br.com.eduardominarelli.restaurantsearch.csv.bean.RestaurantCsvBean;
import br.com.eduardominarelli.restaurantsearch.dto.CuisineDTO;
import br.com.eduardominarelli.restaurantsearch.dto.RestaurantDTO;
import br.com.eduardominarelli.restaurantsearch.filter.RestaurantSearchFilters;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

import static br.com.eduardominarelli.restaurantsearch.dto.RestaurantDTO.of;

@Service
public class RestaurantDomainServiceImpl implements RestaurantDomainService {

    @Autowired
    private CsvReaderService csvReaderService;
    @Autowired
    private CuisineDomainService cuisineDomainService;

    private List<RestaurantCsvBean> restaurantCsvBeanList;

    @PostConstruct
    public void loadAllFromCsv() {
        restaurantCsvBeanList = csvReaderService.read("./src/main/resources/csv/restaurants.csv", RestaurantCsvBean.class).stream().map(RestaurantCsvBean.class::cast).toList();
    }

    public List<RestaurantDTO> searchBasedOnFilters(RestaurantSearchFilters restaurantSearchFilters) {
        return restaurantCsvBeanList.stream()
                                    .map(this::convertToDTOAndLoadCuisine)
                                    .filter(restaurantSearchFilters::filter)
                                    .sorted(this.getResultPriorityOrder())
                                    .limit(5)
                                    .toList();
    }

    private RestaurantDTO convertToDTOAndLoadCuisine(RestaurantCsvBean restaurantCsvBean) {
        return of(restaurantCsvBean).withCuisine(CuisineDTO.of(cuisineDomainService.findById(restaurantCsvBean.getCuisineId())));
    }

    private Comparator<RestaurantDTO> getResultPriorityOrder() {
        return Comparator.comparingInt(RestaurantDTO::getDistance)
                         .thenComparing(Comparator.comparingInt(RestaurantDTO::getCustomerRating).reversed())
                         .thenComparingInt(RestaurantDTO::getPrice);
    }
}