package handler;

import entity.Movie;
import entity.Review;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class MovieHandler extends DataHandler {

    private List<Movie> movieList;


    public MovieHandler() {
    }


    public MovieHandler(List<Movie> movieList) {
        this.movieList = movieList;
    }


    public List<Movie> getMovieList() {
        return movieList;
    }


    public void create(Movie m) {
        loadData("Movie");
        if (this.movieList == null)
            this.movieList = new ArrayList<Movie>();
        if (m != null)
            this.movieList.add(m);
        saveData("Movie");
    }


    public void retrieve() {
        loadData("Movie");
    }


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


    @Override
    protected void readCSV(FileReader csvFile) {
        this.movieList = new ArrayList<Movie>();

        List<String> duration = new ArrayList<String>();
        List<String> cast = new ArrayList<String>();
        List<String> movieType = new ArrayList<String>();
        List<String> movieRating = new ArrayList<String>();
        List<String> movieStatus = new ArrayList<String>();
        List<String> ratings = new ArrayList<String>();
        List<String> synopsis = new ArrayList<String>();
        List<String> director = new ArrayList<String>();
        List<Integer> movieID = new ArrayList<Integer>();
        List<String> title = new ArrayList<String>();
        List<String> userReview = new ArrayList<String>();
        List<String> userReviewRating = new ArrayList<String>();

        BufferedReader br = null;
        String line;
        String cvsSplitBy = ",(?=(?:[^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)";

        try {

            br = new BufferedReader(csvFile);
            String[] data;
            while ((line = br.readLine()) != null) {

                data = line.split(cvsSplitBy);
                duration.add(data[0].replace("\"", ""));
                cast.add(data[1].replace("\"", ""));
                movieType.add(data[2].replace("\"", ""));
                movieRating.add(data[3].replace("\"", ""));
                movieStatus.add(data[4].replace("\"", ""));
                ratings.add(data[5]);
                synopsis.add(data[6].replace("\"", ""));
                director.add(data[7].replace("\"", ""));
                title.add(data[8].replace("\"", ""));
                movieID.add(Integer.valueOf(data[9]));
                try {
                    userReviewRating.add(data[10]);
                    userReview.add(data[11]);
                } catch (ArrayIndexOutOfBoundsException e) {
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        for (int i = 0; i < duration.size(); i++) {
            Movie m = new Movie(movieID.get(i), Double.valueOf(ratings.get(i)), movieRating.get(i), duration.get(i), title.get(i), synopsis.get(i), director.get(i), cast.get(i), movieType.get(i), movieStatus.get(i));
            try {
                List<Review> reviewList = new ArrayList<Review>();
                String line2 = userReview.get(i);
                String line3 = userReviewRating.get(i);
                String[] reviewArray = line2.split("\"\"");
                String[] reviewRatingArray = line3.split("-");
                for (int j = 0; j < reviewArray.length; j++) {
                    String feedback = reviewArray[j];
                    Integer score = Integer.valueOf(reviewRatingArray[j]);
                    Review r = new Review(feedback.replace("\"", ""), score);
                    reviewList.add(r);
                }
                m.setReviews(reviewList);
            } catch (IndexOutOfBoundsException e) {
            }
            this.movieList.add(m);
        }
    }

    @Override
    protected void saveDataToCSV(String to) {
        try (FileWriter file = new FileWriter(to)) {
            for (Movie m : movieList) {
                file.append('\"' + m.getDuration() + "\",\"" + m.getCast() + "\",\"" + m.getMovieType() + "\",\"" + m.getMovieRating() + "\",\"" +
                        m.getMovieStatus() + "\"," + String.valueOf(m.getRatings()) + ",\"" + m.getSynopsis() + "\",\"" +
                        m.getDirector() + "\",\"" + m.getTitle() + "\"," + String.valueOf(m.getMovieID()));
                List<Review> reviewList = m.getReviews();
                if (reviewList.size() != 0) {
                    file.append(",");
                    for (Review r : reviewList) {
                        file.append(String.valueOf(r.getRating()) + "-");
                    }
                    file.append(",");
                    for (Review r : reviewList) {
                        file.append("\"" + r.getFeedback() + "\"");
                    }
                }
                file.append("\n");
            }
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
