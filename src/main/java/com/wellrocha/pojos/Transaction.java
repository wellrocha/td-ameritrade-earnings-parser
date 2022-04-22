package com.wellrocha.pojos;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

import java.time.LocalDate;

public class Transaction {
    @CsvBindByName(column = "DATE", required = true)
    @CsvDate("MM/dd/yyyy")
    private LocalDate date;

    @CsvBindByName(column = "SYMBOL", required = true)
    private String symbol;

    @CsvBindByName(column = "DESCRIPTION", required = true)
    private String description;

    @CsvBindByName(column = "AMOUNT", required = true)
    private Float amount;

    public Transaction() {
    }

    public LocalDate getDate() {
        return date;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getDescription() {
        return description;
    }

    public Float getAmount() {
        return amount;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
