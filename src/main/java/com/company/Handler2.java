package com.company;

import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang.WordUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Handler2 extends RowHandler {
    private Map<String, DataEntry> entriesMap = new HashMap<>();

    @Override
    public DataEntry handle(CSVRecord record) {
        String first = WordUtils.capitalize(record.get(0));
        String last = WordUtils.capitalize(record.get(1));
        String email = record.get(2);
        String title = optionallyEncloseInQuotes(WordUtils.capitalize(record.get(3).replaceAll("/",", ")));

        String date = encloseInQuotes(record.get(5));

        String score = optionallyEncloseInQuotes(record.get(6).replace("K", ",000").replace("M", ",000,000"));
        DataEntry entry = new DataEntry(first, last, email, title, record.get(4), date, score);

        entriesMap.put(record.get(2), entry);
        return entry;
    }

    @Override
    public List<DataEntry> postProcessing(List<DataEntry> entries) {
        for (DataEntry entry : entriesMap.values()) {
            DataEntry mentor = entriesMap.get(entry.getMentor());
            if (mentor != null) {
                entry.setMentor(mentor.getFirst()+" "+mentor.getLast());
            }
        }
        return entries;
    }
}
