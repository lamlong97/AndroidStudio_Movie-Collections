package com.hutech.lamth.moviecollectionsfinal.Objects;

import java.util.List;

public class Movie {
    private String Title;
    private String Poster;
    private String Time;
    private String Year;
    private String Director;
    private String Writer;
    private Object Category;
    private Object Country;
    private String Detail;
    private String Starts;
    private String Trailer;

    public Movie() {
    }

    public Movie(String title, String poster, String time, String year, String director, String writer, Object category, Object country, String detail, String starts, String trailer) {
        Title = title;
        Poster = poster;
        Time = time;
        Year = year;
        Director = director;
        Writer = writer;
        Category = category;
        Country = country;
        Detail = detail;
        Starts = starts;
        Trailer = trailer;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getPoster() {
        return Poster;
    }

    public void setPoster(String poster) {
        Poster = poster;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getDirector() {
        return Director;
    }

    public void setDirector(String director) {
        Director = director;
    }

    public String getWriter() {
        return Writer;
    }

    public void setWriter(String writer) {
        Writer = writer;
    }

    public Object getCategory() {
        return Category;
    }

    public void setCategory(Object category) {
        Category = category;
    }

    public Object getCountry() {
        return Country;
    }

    public void setCountry(Object country) {
        Country = country;
    }

    public String getDetail() {
        return Detail;
    }

    public void setDetail(String detail) {
        Detail = detail;
    }

    public String getStarts() {
        return Starts;
    }

    public void setStarts(String starts) {
        Starts = starts;
    }

    public String getTrailer() {
        return Trailer;
    }

    public void setTrailer(String trailer) {
        Trailer = trailer;
    }
}


