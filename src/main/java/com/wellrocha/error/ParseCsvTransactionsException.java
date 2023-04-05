package com.wellrocha.error;

public class ParseCsvTransactionsException extends Exception {

    public ParseCsvTransactionsException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

}
