package com.wellrocha;

import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.wellrocha.models.DividendsHistoryMap;
import com.wellrocha.models.ParseCsvTransactions;
import com.wellrocha.pojos.DividendHistory;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;

public class App {
    public static void main( String[] args ) throws IOException, URISyntaxException, CsvRequiredFieldEmptyException,
            CsvDataTypeMismatchException {
        var csvTransactions = new ParseCsvTransactions();
        var transactions = csvTransactions.execute();

        var dividendsHistoryMap = new DividendsHistoryMap();
        var dividendsHistory = dividendsHistoryMap.execute(transactions);

        var writer = new FileWriter("src/main/resources/dividends-history.csv");
        var beanToCsv = new StatefulBeanToCsvBuilder<DividendHistory>(writer).build();

        beanToCsv.write(dividendsHistory);
        writer.close();
    }
}
