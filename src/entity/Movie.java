package entity;

import java.util.ArrayList;
import java.util.List;


public class Movie {
    private int movieID;
    private double ratings;
    private String movieRating;
    private String duration;
    private String title;
    private String synopsis;
    private String director;
    private String cast;
    private String movieType;
    private String movieStatus;
    private List<Review> reviews;


    public Movie(int movieID, double ratings, String movieRating, String duration, String title,
                 String synopsis, String director, String cast, String movieType, String movieStatus) {
        this.movieID = movieID;
        this.ratings = ratings;
        this.movieRating = movieRating;
        this.duration = duration;
        this.title = title;
        this.synopsis = synopsis;
        this.director = director;
        this.cast = cast;
        this.movieType = movieType;
        this.movieStatus = movieStatus;
        this.reviews = new ArrayList<Review>();
    }


    public Movie() {
        this.reviews = new ArrayList<Review>();
    }


    public int getMovieID() {
        return movieID;
    }


    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }


    public double getRatings() {
        return ratings;
    }


    public void setRatings(double ratings) {
        this.ratings = ratings;
    }


    public String getDuration() {
        return duration;
    }


    public void setDuration(String duration) {
        this.duration = duration;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getSynopsis() {
        return synopsis;
    }


    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }


    public String getDirector() {
        return director;
    }


    public void setDirector(String director) {
        this.director = director;
    }


    public String getCast() {
        return cast;
    }


    public void setCast(String cast) {
        this.cast = cast;
    }


    public String getMovieType() {
        return movieType;
    }


    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }


    public String getMovieStatus() {
        return movieStatus;
    }


    public void setMovieStatus(String movieStatus) {
        this.movieStatus = movieStatus;
    }


    public List<Review> getReviews() {
        return reviews;
    }


    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }


    public String getMovieRating() {
        return movieRating;
    }


    public void setMovieRating(String movieRating) {
        this.movieRating = movieRating;
    }

    public static class Review {
        private String feedback;
        private int rating;


        public Review() {

        }


        public Review(String feedback, int rating) {
            this.feedback = feedback;
            this.rating = rating;
        }


        public String getFeedback() {
            return feedback;
        }


        public void setFeedback(String feedback) {
            this.feedback = feedback;
        }


        public int getRating() {
            return rating;
        }


        public void setRating(int rating) {
            this.rating = rating;
        }


    }
}
