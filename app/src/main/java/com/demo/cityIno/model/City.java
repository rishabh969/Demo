package com.demo.cityIno.model;


import java.util.Date;

/**
 * Created by Rishabh.
 */

public class City {
    private int id;
    private String title;
    private Date releaseDate;
    private double rating;
    private int durationinMinutes;
    private Type type;

    public City() {
    }

    /**
     *
     * @param id
     * @param title
     * @param releaseDate
     * @param rating
     * @param durationinMinutes
     * @param type
     */
    public City(int id, String title, Date releaseDate, double rating, int durationinMinutes, Type type) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.durationinMinutes = durationinMinutes;
        this.type = type;
    }

    public enum Type{
        ACTION,HORROR,THRILLER,ROMANTIC,DRAMA,FICTION,ANIMATION
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getDurationinMinutes() {
        return durationinMinutes;
    }

    public void setDurationinMinutes(int durationinMinutes) {
        this.durationinMinutes = durationinMinutes;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Title : '" + title + '\''+" Rating : '" + rating + '\'';
    }
}
