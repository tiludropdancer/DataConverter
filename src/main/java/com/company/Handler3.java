package com.company;

import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang.WordUtils;

public class Handler3 extends RowHandler {

    @Override
    public DataEntry handle(CSVRecord record) {
        String[] tokens = record.get(0).split(", ");
        String first = tokens[1];
        String last = tokens[0];
        String email = record.get(1);
        String title = optionallyEncloseInQuotes(WordUtils.capitalize(record.get(2)));

        String mentor = record.get(3);

        if (!mentor.isEmpty()) {
            tokens = record.get(3).split(", ");
            mentor = tokens[1] + " " + tokens[0];
        }

        tokens = record.get(4).split(" ");
        String date = encloseInQuotes(tokens[0].substring(0,3)+" "+tokens[1]+" "+tokens[2]);

        String score = record.get(5);

        StringBuilder sb = new StringBuilder();
        int len = score.length();
        for (int i = 0; i < len; i++) {
            if (i % 3 == 0 && i > 0) {
                sb.insert(0, ",");
            }
            sb.insert(0, score.charAt(len - i - 1));
        }
        score = optionallyEncloseInQuotes(sb.toString());

        return new DataEntry(first, last, email, title, mentor, date, score);
    }

}
