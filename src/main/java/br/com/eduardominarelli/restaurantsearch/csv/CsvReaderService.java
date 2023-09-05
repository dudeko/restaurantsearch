package br.com.eduardominarelli.restaurantsearch.csv;

import br.com.eduardominarelli.restaurantsearch.csv.bean.CsvBean;

import java.util.List;

public interface CsvReaderService {

    List<CsvBean> read(String filePath, Class<? extends CsvBean> clazz);
}