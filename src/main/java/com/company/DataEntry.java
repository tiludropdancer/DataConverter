package com.company;

import java.util.Arrays;

public class DataEntry {
    private String first;
    private String last;
    private String email;
    private String title;
    private String mentor;
    private String date;
    private String score;

    public DataEntry(String first, String last, String email, String title, String mentor, String date, String score) {
        this.first = first;
        this.last = last;
        this.email = email;
        this.title = title;
        this.mentor = mentor;
        this.date = date;
        this.score = score;
    }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    public String getEmail() {
        return email;
    }

    public String getTitle() {
        return title;
    }

    public String getMentor() {
        return mentor;
    }

    public void setMentor(String mentor) {
        this.mentor = mentor;
    }

    public String getDate() {
        return date;
    }

    public int getScore() {
        return Integer.valueOf(score.replaceAll("[,\"]", ""));
    }

    public String toString() {
        return String.join(",", Arrays.asList(first, last, email, title, mentor, date, score));
    }
}
