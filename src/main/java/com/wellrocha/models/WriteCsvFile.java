package com.wellrocha.models;

import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

final public class WriteCsvFile {

    private static final String FILE_PATH = "src/main/resources/dividends-history.csv";

    public <T> void execute(List<T> data) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        var writer = new FileWriter(FILE_PATH);
        var beanToCsv = new StatefulBeanToCsvBuilder<T>(writer).build();

        beanToCsv.write(data);
        writer.close();
    }

}
