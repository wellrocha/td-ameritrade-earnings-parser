package com.wellrocha;

import com.wellrocha.map.DividendsHistoryMap;
import com.wellrocha.adapter.csv.ParseCsvTransactions;
import com.wellrocha.adapter.csv.WriteCsvFile;
import com.wellrocha.service.CreateDividendHistoryService;

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
