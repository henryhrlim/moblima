package handlers;

import java.util.ArrayList;
import java.util.List;

import objects.Cinema;
import objects.Cineplex;
import objects.Movie;
import objects.Review;

/**
 * MovieHandler
 * @version 1.0
 * @since 10/10/2015
 */

public class MovieHandler extends DataHandler {
    /**
     * The list of Movie.
     */
    public List<Movie> movieList;

    /**
     * Class constructor
     *
     */
    public MovieHandler() {
    }

    /**
     * Class constructor
     * @param movieList a list of Movies.
     */
    public MovieHandler(List<Movie> movieList) {
        this.movieList = movieList;
    }

    /**
     * Gets the movieList
     * @return movieList.
     */
    public List<Movie> getMovieList() {
        return movieList;
    }

    /**
     * Writes a new Movie into the JSON file.
     * The Movie object m's variables all must be set.
     * @param m This is the object of Movie.
     */
    public void create(Movie m) {
        loadData("Movie");
        if (this.movieList == null)
            this.movieList = new ArrayList<Movie>();
        if (m != null)
            this.movieList.add(m);
        saveData("Movie");
    }

    /**
     * Call loadData method from parent class.
     * Retrieve data from CSV file and save in the cineplexList.
     */
    public void retrieve() {
        loadData("Movie");
    }

    /**
     * Retrieve movie object using movieID.
     * @param movieID This is the Movie's movieID.
     * @return Movie object
     */
    public Movie retrieveMovieDetails(int movieID) {
        Movie m = null;
        loadData("Movie");
        for (int i = 0; i < this.movieList.size(); i++) {
            m = this.movieList.get(i);
            if (m.getMovieID() == movieID) {
                break;
            }
        }
        return m;
    }

    /**
     * This method update the Movie object in the JSON file.
     * It will update data by the movieID as the index.
     * If updated return true, else return false.
     * The Movie object m's variables all must be set.
     * @param m This is the object of Movie.
     * @return boolean
     */
    public boolean update(Movie m) {
        loadData("Movie");
        for (int i = 0; i < this.movieList.size(); i++) {
            Movie movie = this.movieList.get(i);
            if (movie.getMovieID() == m.getMovieID()) {
                this.movieList.set(i, m);
                saveData("Movie");
                return true;
            }
        }
        return false;
    }

    /**
     * This method update the Movie object in the JSON file.
     * It will update movieStatus in Movie to "End of Showing" by the movieID.
     * If updated return true, else return false.
     * @param movieID This is the Movie's movieID.
     * @return boolean
     */
    public boolean delete(int movieID) {
        loadData("Movie");
        for (int i = 0; i < this.movieList.size(); i++) {
            Movie movie = this.movieList.get(i);
            if (movie.getMovieID() == movieID) {
                movie.setMovieStatus("End of Showing");
                this.movieList.set(i, movie);
                saveData("Movie");
                return true;
            }
        }
        return false;
    }

    /**
     * Override readJSON method from parent class(DataHandler).
     * arr contains the data retrieved from the JSON file.
     * This method will format the arr JSONArray object and save it into the movieList.
     * @param arr This is a JSONArray object.
     */
    @Override
    protected void readCSV(String[] data) {
        // TODO Auto-generated method stub
        this.movieList = new ArrayList<Movie>();
        int movieID;
        double ratings;
        String duration;
        String title;
        String synoposis;
        String director;
        String cast;
        String movieType;
        String movieStatus;
        String movieRating;
        long temp;
        for (int i = 0; i < data.size(); i++) {
            String[] movieCSV = (String[]) data.get(i);
            temp = (long) movieJson.get("movieID");
            movieID = (int) temp;
            ratings = (double) movieJson.get("ratings");
            duration = (String) movieJson.get("duration");
            title = (String) movieJson.get("title");
            synoposis = (String) movieJson.get("synoposis");
            director = (String) movieJson.get("director");
            cast = (String) movieJson.get("cast");
            movieType = (String) movieJson.get("movieType");
            movieStatus = (String) movieJson.get("movieStatus");
            movieRating = (String) movieJson.get("movieRating");

            List<Review> reviewList = new ArrayList<Review>();
            JSONArray reviewArr = (JSONArray) movieJson.get("review");
            for (int x = 0; x < reviewArr.size(); x++) {
                JSONObject reviewJson = (JSONObject) reviewArr.get(x);
                String feedback = (String) reviewJson.get("feedback");
                temp = (long) reviewJson.get("rating");
                int rating = (int) temp;
                Review r = new Review(feedback, rating);
                reviewList.add(r);
            }

            Movie m = new Movie(movieID, ratings, movieRating, duration, title,
                    synoposis, director, cast, movieType, movieStatus);
            m.setReviews(reviewList);
            this.movieList.add(m);
        }
    }

    /**
     * Override saveDataToJSON method from parent class(DataHandler).
     * This method will format the movieList into a JSONArray object and return it.
     * @return JSONArray object.
     */
    @Override
    protected JSONArray saveDataToJSON() {
        JSONArray movieArr = new JSONArray();

        for (Movie m : movieList) {
            JSONObject movieJson = new JSONObject();
            movieJson.put("movieID", m.getMovieID());
            movieJson.put("ratings", m.getRatings());
            movieJson.put("duration", m.getDuration());
            movieJson.put("title", m.getTitle());
            movieJson.put("synoposis", m.getSynopsis());
            movieJson.put("director", m.getDirector());
            movieJson.put("cast", m.getCast());
            movieJson.put("movieType", m.getMovieType());
            movieJson.put("movieStatus", m.getMovieStatus());
            movieJson.put("movieRating", m.getMovieRating());

            JSONArray reviewArr = new JSONArray();
            List<Review> reviewList = m.getReviews();
            for (Review r : reviewList) {
                JSONObject reviewJson = new JSONObject();
                reviewJson.put("feedback", r.getFeedback());
                reviewJson.put("rating", r.getRating());
                reviewArr.add(reviewJson);
            }
            movieJson.put("review", reviewArr);
            movieArr.add(movieJson);
        }
        return movieArr;
    }

}
