package com.company;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Main {
    private static final String HEADING = "First Name,Last Name,Email Address,Job Titles,Mentor,Date of Last Game,High Score";

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: converter <input-file>");
            System.exit(0);
        }
        try (Reader in = new FileReader(args[0]); Writer out = new FileWriter("output.csv")) {
            List<CSVRecord> list = new CSVParser(in, selectFormat(args[0])).getRecords();
            RowHandler handler = null;

            List<DataEntry> dataEntries = new LinkedList<>();

            for (CSVRecord record : list) {
                if (handler == null) {
                    handler = selectHandler(record);
                } else {
                    DataEntry entry = handler.handle(record);
                    dataEntries.add(entry);
                }
            }

            Collections.sort(dataEntries, new Comparator<DataEntry>() {
                @Override public int compare(DataEntry o1, DataEntry o2) {
                    return o2.getScore() - o1.getScore();
                }
            });

            dataEntries = handler.postProcessing(dataEntries);

            out.write(HEADING+"\r\n");
            for (DataEntry entry : dataEntries) {
                out.write(entry+"\r\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static CSVFormat selectFormat(String input) {
        CSVFormat format = null;
        if (input.endsWith("tsv")) {
            format = CSVFormat.TDF;
        } else if (input.endsWith("csv")) {
            format = CSVFormat.DEFAULT;
        } else {
            throw new IllegalArgumentException("Unknown input format: "+input);
        }
        return format;
    }

    private static RowHandler selectHandler(CSVRecord record) {
        switch (record.get(0)) {
        case "First Name": return new Handler1();
        case "First": return new Handler2();
        case "Name": return new Handler3();
        case "Player": return new Handler4();
        default: throw new IllegalArgumentException("Unknown data format");
        }
    }
}
