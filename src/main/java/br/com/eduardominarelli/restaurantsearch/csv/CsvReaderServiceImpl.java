package br.com.eduardominarelli.restaurantsearch.csv;

import br.com.eduardominarelli.restaurantsearch.csv.bean.CsvBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Service
public class CsvReaderServiceImpl implements CsvReaderService {

    public List<CsvBean> read(String filePath, Class<? extends CsvBean> clazz) {
        try {
            return new CsvToBeanBuilder<CsvBean>(new FileReader(filePath))
                    .withType(clazz)
                    .build()
                    .parse();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
