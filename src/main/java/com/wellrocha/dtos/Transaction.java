package com.wellrocha.dtos;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;

public class Transaction {

    @CsvBindByName(column = "DATE", required = true)
    @CsvDate("MM/dd/yyyy")
    private LocalDate date;

    @CsvBindByName(column = "SYMBOL")
    private String symbol;

    @CsvBindByName(column = "DESCRIPTION", required = true)
    private String description;

    @CsvBindByName(column = "AMOUNT", required = true)
    private BigDecimal amount;

    public Transaction() {
    }

    public LocalDate getDate() {
        return date;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        if (symbol.isEmpty()) {
            this.symbol= this.getDescription().split(" \\(")[1].replace(")", "");
        } else {
            this.symbol = symbol;
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description.contains("~")) {
            var descriptionSplit = description.split("~");
            this.description = String.format("%s (%s)",
                    descriptionSplit[0].toUpperCase(Locale.ENGLISH),
                    descriptionSplit[1].toUpperCase(Locale.ENGLISH));
        } else {
            this.description = description.toUpperCase(Locale.ENGLISH);
        }
    }

    public BigDecimal getAmount() {
        return amount;
    }

}
