package objects;
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
    List<Review> reviews;

    public Movie(){

    }

    public Movie(int movieID, double ratings, String movieRating, String duration, String title, String synopsis, String director, String cast, String movieType, String movieStatus){
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
        this.reviews = reviews;
    }

    public int getMovieID(){
        return movieID;
    }

    public void setMovieID(int movieID){
        this.movieID = movieID;
    }

    public double getRatings() {
        return ratings;
    }

    public void setRatings(double ratings) {
        this.ratings = ratings;
    }

    public String getMovieRating() {
        return movieRating;
    }

    public void setMovieRating(String movieRating) {
        this.movieRating = movieRating;
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
}
