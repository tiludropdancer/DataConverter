package com.company;

import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang.WordUtils;

import java.util.HashMap;
import java.util.Map;

public class Handler4 extends RowHandler {
    private static Map<String, String> months = new HashMap<>();
    static {
        months.put("1", "Jan");
        months.put("2", "Feb");
        months.put("3", "Mar");
        months.put("4", "Apr");
        months.put("5", "May");
        months.put("6", "Jun");
        months.put("7", "Jul");
        months.put("8", "Aug");
        months.put("9", "Sep");
        months.put("10", "Oct");
        months.put("11", "Nov");
        months.put("12", "Dec");
    }

    @Override
    public DataEntry handle(CSVRecord record) {
        String[] tokens = record.get(0).split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tokens.length-2; i++) {
            sb.append(tokens[i]).append(" ");
        }
        String first = sb.toString().trim();
        String last = tokens[tokens.length-2];

        String email = tokens[tokens.length-1].replaceAll("[<>]", "");

        String title = optionallyEncloseInQuotes(record.get(1));

        String mentor = WordUtils.capitalize(record.get(2));

        tokens = record.get(3).split("/");

        String date = encloseInQuotes(months.get(tokens[0])+" "+tokens[1]+", "+tokens[2]);

        String score = optionallyEncloseInQuotes(record.get(4).replace("k", ",000").replace("m", ",000,000"));
        DataEntry entry = new DataEntry(first, last, email, title, record.get(4), date, score);
        return new DataEntry(first, last, email, title, mentor, date, score);
    }
}
