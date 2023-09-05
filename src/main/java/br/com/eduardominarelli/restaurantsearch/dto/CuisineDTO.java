package br.com.eduardominarelli.restaurantsearch.dto;

import br.com.eduardominarelli.restaurantsearch.csv.bean.CuisineCsvBean;

public class CuisineDTO {

    private Long id;
    private String name;

    public static CuisineDTO of(CuisineCsvBean cuisineCsvBean) {
        CuisineDTO cuisineDTO = new CuisineDTO();
        cuisineDTO.setId(cuisineCsvBean.getId());
        cuisineDTO.setName(cuisineCsvBean.getName());
        return cuisineDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
