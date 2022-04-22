package com.wellrocha.models;

import com.wellrocha.pojos.DividendHistory;
import com.wellrocha.pojos.Transaction;

import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DividendsHistoryMap {

    public List<DividendHistory> execute(final Collection<Transaction> transactions) {
        var taxes = transactions
                .stream()
                .filter(this::getTransactionTax)
                .collect(Collectors.toList());

        var dividendsHistory = transactions
                .stream()
                .filter(this::getTransactionDividend)
                .map(dividend -> this.createDividendHistory(dividend, taxes))
                .collect(Collectors.toList());

        transactions
                .stream()
                .filter(this::getTransactionRoc)
                .forEach(roc -> this.addRocToDividendsHistory(roc, dividendsHistory));

        Collections.sort(dividendsHistory, Comparator.comparing(DividendHistory::getDate));
        return Collections.unmodifiableList(dividendsHistory);
    }

    private void addRocToDividendsHistory(final Transaction roc, final List<DividendHistory> dividendsHistory) {
        var dividendHistory = DividendHistory.builder()
                .date(roc.getDate())
                .type("Return of Capital")
                .symbol(roc.getSymbol())
                .description(roc.getDescription())
                .amount(roc.getAmount())
                .total(roc.getAmount())
                .build();
        dividendsHistory.add(dividendHistory);
    }

    private boolean getTransactionRoc(final Transaction transaction) {
        return transaction.getDescription().toLowerCase().contains("withholding") && transaction.getAmount() > 0;
    }

    private DividendHistory createDividendHistory(final Transaction transaction, final List<Transaction> taxes) {
        var tax = taxes
                .stream()
                .filter(it -> it.getSymbol().equals(transaction.getSymbol()))
                .findFirst()
                .get();
        taxes.remove(tax);

        var decimalFormatPattern = new DecimalFormat("#.##");
        var total = transaction.getAmount() + tax.getAmount();
        total = Float.parseFloat(decimalFormatPattern.format(total));

        return DividendHistory.builder()
                .date(transaction.getDate())
                .type("Dividends")
                .symbol(transaction.getSymbol())
                .description(transaction.getDescription())
                .amount(transaction.getAmount())
                .taxDescription(tax.getDescription())
                .taxAmount(tax.getAmount())
                .total(total)
                .build();
    }

    private boolean getTransactionDividend(final Transaction transaction) {
        return transaction.getDescription().toLowerCase().contains("dividend");
    }

    private boolean getTransactionTax(final Transaction transaction) {
        return transaction.getDescription().toLowerCase().contains("withholding") && transaction.getAmount() < 0;
    }

}
