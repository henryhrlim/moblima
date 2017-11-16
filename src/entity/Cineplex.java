package entity;

import java.util.ArrayList;
import java.util.List;


public class Cineplex {
    private int cineplexID;
    private String location;
    private String cineplexName;
    private List<Cinema> cinemas;
    private List<Movie> movie;
    private List<ShowTime> showTime;


    public Cineplex() {
    }


    public Cineplex(int cineplexID, String location, String cineplexName, List<Cinema> cinemas) {
        this.cineplexID = cineplexID;
        this.location = location;
        this.cineplexName = cineplexName;
        if (cinemas == null) {
            this.cinemas = new ArrayList<Cinema>();
            String code = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            this.cinemas.add(new Cinema("normal", code.charAt(cineplexID - 1) + "01"));
            this.cinemas.add(new Cinema("normal", code.charAt(cineplexID - 1) + "02"));
            this.cinemas.add(new Cinema("normal", code.charAt(cineplexID - 1) + "03"));
        } else {
            this.cinemas = cinemas;
        }

    }


    public int getCineplexID() {
        return cineplexID;
    }


    public void setCineplexID(int cineplexID) {
        this.cineplexID = cineplexID;
    }


    public String getLocation() {
        return location;
    }


    public void setLocation(String location) {
        this.location = location;
    }


    public String getCineplexName() {
        return cineplexName;
    }


    public void setCineplexName(String cineplexName) {
        this.cineplexName = cineplexName;
    }


    public List<Cinema> getCinemas() {
        return cinemas;
    }


    public void setCinemas(List<Cinema> cinemas) {
        this.cinemas = cinemas;
    }


    public List<Movie> getMovie() {
        return movie;
    }


    public void setMovie(List<Movie> movie) {
        this.movie = movie;
    }


    public List<ShowTime> getShowTime() {
        return showTime;
    }


    public void setShowTime(List<ShowTime> showTime) {
        this.showTime = showTime;
    }

}
