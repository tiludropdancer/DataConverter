package com.company;

import org.apache.commons.csv.CSVRecord;

import java.util.List;

public abstract class RowHandler {
    public abstract DataEntry handle(CSVRecord record);

    public List<DataEntry> postProcessing(List<DataEntry> entries) {
        return entries;
    }

    protected static String encloseInQuotes(String v) {
        return String.format("\"%s\"", v);
    }

    protected static String optionallyEncloseInQuotes(String v) {
        if (v.indexOf(",") < 0) {
            return v;
        }
        return encloseInQuotes(v);
    }
}
