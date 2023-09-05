package br.com.eduardominarelli.restaurantsearch.csv.bean;

import com.opencsv.bean.CsvBindByName;

public class CuisineCsvBean extends CsvBean {

    @CsvBindByName
    private Long id;
    @CsvBindByName
    private String name;

    public static CuisineCsvBean build() {
        return new CuisineCsvBean();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CuisineCsvBean withId(Long id) {
        this.setId(id);
        return this;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CuisineCsvBean withName(String name) {
        this.setName(name);
        return this;
    }
}
