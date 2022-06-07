package com.wellrocha.dtos;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Getter
public class DividendHistory {

    @CsvBindByPosition(position = 0)
    private LocalDate date;

    @CsvBindByPosition(position = 1)
    private String type;

    @CsvBindByPosition(position = 2)
    private String symbol;

    @CsvBindByPosition(position = 3)
    private String description;

    @CsvBindByPosition(position = 4)
    private BigDecimal amount;

    @CsvBindByPosition(position = 5)
    private String taxDescription;

    @CsvBindByPosition(position = 6)
    private BigDecimal taxAmount;

    @CsvBindByPosition(position = 7)
    private BigDecimal total;

}
