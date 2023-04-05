package com.wellrocha.adapter.csv;

import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;

final public class WriteCsvFile {

    private static final String FILE_PATH = "src/main/resources/dividends-history.csv";

    public <T> void execute(List<T> data) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        var writer = new FileWriter(FILE_PATH);
        var beanToCsv = new StatefulBeanToCsvBuilder<T>(writer).build();

        beanToCsv.write(data);
        writer.close();

        replaceCurrencyDotForComma();
    }

    private void replaceCurrencyDotForComma() throws IOException {
        var file = new File(FILE_PATH);
        var csvDataAsString = FileUtils
                .readFileToString(file)
                .replace(".", ",");

        copyFileToClipboard(csvDataAsString);

        FileUtils.writeStringToFile(file, csvDataAsString);
    }

    private void copyFileToClipboard(String csvDataAsString) {
        var stringSelection = new StringSelection(csvDataAsString);
        var clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }
}
