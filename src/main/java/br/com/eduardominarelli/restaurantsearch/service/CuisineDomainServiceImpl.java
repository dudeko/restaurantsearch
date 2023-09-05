package br.com.eduardominarelli.restaurantsearch.service;

import br.com.eduardominarelli.restaurantsearch.csv.CsvReaderService;
import br.com.eduardominarelli.restaurantsearch.csv.bean.CuisineCsvBean;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuisineDomainServiceImpl implements CuisineDomainService {

    @Autowired
    private CsvReaderService csvReaderService;

    private List<CuisineCsvBean> cuisineCsvBeanList;

    @PostConstruct
    private void loadAllFromCsv() {
        cuisineCsvBeanList = csvReaderService.read("./src/main/resources/csv/cuisines.csv", CuisineCsvBean.class).stream().map(CuisineCsvBean.class::cast).toList();
    }

    @Override
    public CuisineCsvBean findById(Long id) {
        return cuisineCsvBeanList.stream().filter(cuisineCsvBean -> cuisineCsvBean.getId().equals(id)).findFirst().orElse(null);
    }
}