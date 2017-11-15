package entity;

import java.util.ArrayList;
import java.util.List;


/**
 * Movie
 *
 * @version 1.0
 * @since 10/10/2015
 */

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


    /**
     * Class Constructor
     *
     * @param movieID     integer of movieID
     * @param ratings     double of ratings
     * @param movieRating string of movieRating
     * @param duration    string of duration
     * @param title       string of title
     * @param synopsis    string of synopsis
     * @param director    string of director
     * @param cast        string of cast
     * @param movieType   string of movieType
     * @param movieStatus string of movieStatus
     */
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

    /**
     * Class Constructor
     */
    public Movie() {
        this.reviews = new ArrayList<Review>();
    }


    /**
     * Gets the Movie ID
     *
     * @return movieID.
     */
    public int getMovieID() {
        return movieID;
    }


    /**
     * Set the Movie ID
     *
     * @param movieID To pass a integer of movieID
     */
    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    /**
     * Gets the Ratings
     *
     * @return ratings.
     */
    public double getRatings() {
        return ratings;
    }

    /**
     * Set the Rating of the Movie
     *
     * @param ratings To pass a double of ratings
     */
    public void setRatings(double ratings) {
        this.ratings = ratings;
    }

    /**
     * Gets the Duration
     *
     * @return duration.
     */
    public String getDuration() {
        return duration;
    }

    /**
     * Set the Duration of the Movie
     *
     * @param duration To pass string of duration
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }

    /**
     * Gets the Title
     *
     * @return title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the Title of the Movie
     *
     * @param title To pass string of title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the Synopsis
     *
     * @return synopsis.
     */
    public String getSynopsis() {
        return synopsis;
    }

    /**
     * Set the Synopsis of the Movie
     *
     * @param synopsis To pass string of synopsis
     */
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }


    /**
     * Gets the Director
     *
     * @return director.
     */
    public String getDirector() {
        return director;
    }

    /**
     * Set the Director of the Movie
     *
     * @param director To pass string of director
     */
    public void setDirector(String director) {
        this.director = director;
    }


    /**
     * Gets the Cast
     *
     * @return cast.
     */
    public String getCast() {
        return cast;
    }

    /**
     * Set the Cast of the Movie
     *
     * @param cast To pass string of cast
     */
    public void setCast(String cast) {
        this.cast = cast;
    }


    /**
     * Gets the Movie Type
     *
     * @return movieType.
     */
    public String getMovieType() {
        return movieType;
    }

    /**
     * Set the Movie Type
     *
     * @param movieType To pass string of movieType
     */
    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }


    /**
     * Gets the Movie Status
     *
     * @return movieStatus.
     */
    public String getMovieStatus() {
        return movieStatus;
    }

    /**
     * Set the Movie Status
     *
     * @param movieStatus To pass string of movieStatus
     */
    public void setMovieStatus(String movieStatus) {
        this.movieStatus = movieStatus;
    }

    /**
     * Gets a list Reviews
     *
     * @return reviews.
     */
    public List<Review> getReviews() {
        return reviews;
    }

    /**
     * Set the Movie Reviews
     *
     * @param reviews To pass a list of reviews
     */
    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    /**
     * Gets the Movie Rating
     *
     * @return movieRating.
     */
    public String getMovieRating() {
        return movieRating;
    }

    /**
     * Set the Movie Rating
     *
     * @param movieRating To pass string of movieRating
     */
    public void setMovieRating(String movieRating) {
        this.movieRating = movieRating;
    }

}
