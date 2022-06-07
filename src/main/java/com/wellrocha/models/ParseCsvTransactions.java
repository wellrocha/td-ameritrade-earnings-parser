package com.wellrocha.models;

import com.opencsv.bean.CsvToBeanBuilder;
import com.wellrocha.dtos.Transaction;
import com.wellrocha.exceptions.ParseCsvTransactionsException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.List;

final public class ParseCsvTransactions {

    public List<Transaction> execute() throws ParseCsvTransactionsException {
        try {
            var systemResource = ClassLoader.getSystemResource("transactions.csv");
            if (systemResource == null) {
                throw new FileNotFoundException("Não foi encontrado o arquivo, por favor verifique a pasta resources.");
            }

            var transactionsCsvPath = Paths.get(systemResource.toURI()).toString();
            var transactionsFile = new FileReader(transactionsCsvPath);

            return new CsvToBeanBuilder<Transaction>(transactionsFile)
                    .withType(Transaction.class)
                    .withFilter(line -> !line[0].toLowerCase().contains("end of file")
                            && !line[2].toLowerCase().contains("wire incoming")
                            && !line[2].toLowerCase().contains("bought"))
                    .build()
                    .parse();
        } catch (Exception error) {
            throw new ParseCsvTransactionsException("Ocorreu um erro inesperado ao tentar fazer a leitura do arquivo transactions.csv. Mais detalhes: " + error.getMessage(), error);
        }
    }

}
