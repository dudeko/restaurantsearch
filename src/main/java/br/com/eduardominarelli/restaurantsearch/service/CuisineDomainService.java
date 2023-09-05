package br.com.eduardominarelli.restaurantsearch.service;

import br.com.eduardominarelli.restaurantsearch.csv.bean.CuisineCsvBean;

public interface CuisineDomainService {

    CuisineCsvBean findById(Long id);

}
