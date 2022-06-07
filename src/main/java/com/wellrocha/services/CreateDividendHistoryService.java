package com.wellrocha.services;

import com.wellrocha.models.DividendsHistoryMap;
import com.wellrocha.models.ParseCsvTransactions;
import com.wellrocha.models.WriteCsvFile;

final public class CreateDividendHistoryService {

    private final ParseCsvTransactions parseCsvTransactions;
    private final DividendsHistoryMap dividendsHistoryMap;
    private final WriteCsvFile writeCsvFile;

    public CreateDividendHistoryService(ParseCsvTransactions parseCsvTransactions, DividendsHistoryMap dividendsHistoryMap, WriteCsvFile writeCsvFile) {
        this.parseCsvTransactions = parseCsvTransactions;
        this.dividendsHistoryMap = dividendsHistoryMap;
        this.writeCsvFile = writeCsvFile;
    }

    public ServiceResponse execute() {
        try {
            var transactions = this.parseCsvTransactions.execute();
            var dividendsHistory = this.dividendsHistoryMap.execute(transactions);
            writeCsvFile.execute(dividendsHistory);

            return ServiceResponse
                    .builder()
                    .success(true)
                    .build();
        } catch (Exception error) {
            error.printStackTrace();
            return ServiceResponse
                    .builder()
                    .success(false)
                    .error(error.getMessage())
                    .build();
        }
    }

}
