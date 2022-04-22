package com.wellrocha.models;

import com.opencsv.bean.CsvToBeanBuilder;
import com.wellrocha.pojos.Transaction;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Collection;

public class ParseCsvTransactions {

    public Collection<Transaction> execute() throws URISyntaxException, FileNotFoundException {
        var transactionsCsvPath = Paths.get(ClassLoader.getSystemResource("transactions.csv").toURI()).toString();
        var transactionsFile = new FileReader(transactionsCsvPath);
        var transactions = new CsvToBeanBuilder(transactionsFile)
                .withType(Transaction.class)
                .withFilter(line -> !line[0].toLowerCase().contains("end of file")
                        && !line[2].toLowerCase().contains("wire incoming")
                        && !line[2].toLowerCase().contains("bought"))
                .build()
                .parse();
        return transactions;
    }

}
