package com.wellrocha.exceptions;

public class ParseCsvTransactionsException extends Exception {

    public ParseCsvTransactionsException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

}
