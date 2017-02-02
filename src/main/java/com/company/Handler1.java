package com.company;

import org.apache.commons.csv.CSVRecord;

public class Handler1 extends RowHandler {

    @Override
    public DataEntry handle(CSVRecord record) {
        String title = optionallyEncloseInQuotes(record.get(3));
        String date = encloseInQuotes(record.get(5));
        String score = optionallyEncloseInQuotes(record.get(6));
        return new DataEntry(record.get(0), record.get(1), record.get(2), title, record.get(4), date, score);
    }
}
