package com.wellrocha;

import com.wellrocha.models.DividendsHistoryMap;
import com.wellrocha.models.ParseCsvTransactions;
import com.wellrocha.models.WriteCsvFile;
import com.wellrocha.services.CreateDividendHistoryService;

public class App {
    public static void main( String[] args ) {
        var csvTransactions = new ParseCsvTransactions();
        var dividendsHistoryMap = new DividendsHistoryMap();
        var writeCsvFile = new WriteCsvFile();

        var createDividendHistoryService = new CreateDividendHistoryService(csvTransactions, dividendsHistoryMap, writeCsvFile);
        var response = createDividendHistoryService.execute();
        if (response.isSuccess()) {
            System.out.println("Operação efetuada com sucesso.");
        } else {
            System.out.println(response.getError());
        }
    }
}
