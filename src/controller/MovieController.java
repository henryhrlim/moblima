package controller;

import app.CustomerMenu;
import entity.Cineplex;
import entity.Movie;
import entity.Review;
import handler.MovieHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * Class: MovieController
 * <p>
 * Class Methods:
 * - MovieController()
 * - createMovie(Movie m) : void
 * - retrieveMovieList() : List<Movie>
 * - retrieveMovieList(List<Movie> mList) : List<Movie>
 * - updateMovie(Movie m) : boolean
 * - removeMovie(int movieID) : boolean
 * - getMovieRatings(int movieID) : double
 * - listAllMovies(): void
 * - listMovieDetails(): void
 * - listCineplex(): Cineplex
 * - listMovieSpecific(Cineplex cineUserChoice) : Movie
 */
public class MovieController {
    private CustomerMenu c_menu = new CustomerMenu();


    public MovieController() {
    }


    public void createMovie(Movie m) {
        MovieHandler handler = new MovieHandler();
        handler.create(m);
    }

    public List<Movie> retrieveMovieList() {
        MovieHandler handler = new MovieHandler();
        handler.retrieve();
        List<Movie> mList = handler.getMovieList();
        if (mList == null)
            mList = new ArrayList<Movie>();
        return mList;
    }

    public List<Movie> retrieveMovieList(List<Movie> mList) {
        MovieHandler handler = new MovieHandler();
        handler.retrieve();
        List<Movie> movieList = new ArrayList<Movie>();
        List<Movie> tempList = handler.getMovieList();
        if (tempList == null)
            tempList = new ArrayList<Movie>();
        for (Movie m : tempList) {
            for (Movie movie : mList) {
                if (movie.getMovieID() == m.getMovieID()) {
                    movieList.add(m);
                }
            }
        }
        return movieList;
    }

    public boolean updateMovie(Movie m) {
        MovieHandler handler = new MovieHandler();
        if (handler.update(m))
            return true;
        else
            return false;
    }

    public boolean removeMovie(int movieID) {
        MovieHandler handler = new MovieHandler();
        if (handler.delete(movieID))
            return true;
        else
            return false;
    }

    public double getMovieRatings(int movieID) {
        double overallRating, total = 0;
        MovieHandler handler = new MovieHandler();
        Movie m = handler.retrieveMovieDetails(movieID);
        List<Review> reviewList = m.getReviews();
        for (Review r : reviewList) {
            total += r.getRating();
        }
        overallRating = total / reviewList.size();
        return (double) Math.round(overallRating * 10) / 10;
    }


    public void listAllMovies() {
        StaffController staffControl = new StaffController();
        MovieController movieControl = new MovieController();
        Scanner sc = new Scanner(System.in);
        int movieUser;

        List<Movie> movieList = movieControl.retrieveMovieList();

        System.out.println("===== List of Movies =====");
        System.out.println("ID    Title");
        for (Movie m : movieList) {
            if (m.getMovieStatus().equals("End of Showing")) {
                continue;
            }
            System.out.format("%-5s %s\n", m.getMovieID(), m.getTitle());
        }
        System.out.format("%-5s Back\n", 0);
        System.out.print("Enter Movie ID to view more details: ");
        movieUser = sc.nextInt();
        System.out.print("\n");
        
        if (movieUser != 0) {
            for (Movie m : movieList) {
                double sum = 0.0;
                if (movieUser == m.getMovieID()) {
                    System.out.format("Movie Title    : %s\n", m.getTitle());
                    System.out.format("Movie Type     : %s\n", m.getMovieType());
                    System.out.format("Movie Rating   : %s\n", m.getMovieRating());
                    System.out.format("Duration       : %s\n", m.getDuration());
                    System.out.format("Synopsis       : %s\n", staffControl.wordWrap(m.getSynopsis()));
                    System.out.format("Director       : %s\n", m.getDirector());
                    System.out.format("Cast           : %s\n", m.getCast());
                    System.out.format("Showing Status : %s\n", m.getMovieStatus());

                    List<Review> reviewList = m.getReviews();

                    if (reviewList.size() > 1) {
                        for (Review re : reviewList)
                            sum += re.getRating();
                        System.out.format("Overall Rating: %.1f / 5.0\n", sum/reviewList.size());
                    }
                    else
                        System.out.println("Overall Ratings: N/A");

                    if (reviewList.size() > 0) {
                        for (Review r : reviewList) {
                            System.out.println("User Rating    : " + r.getRating() + " / 5");
                            System.out.println("User Review    : " + staffControl.wordWrap(r.getFeedback()));
                        }
                    }
                    System.out.print("\n");
                    break;
                }
            }
        }
        c_menu.show();
    }

    public void searchMovie() {
        StaffController staffControl = new StaffController();
        MovieController movieControl = new MovieController();
        Scanner sc = new Scanner(System.in);
        
        String searchKey = "";
        boolean endOfShowing;

        System.out.print("Enter Search Keyword: ");
        searchKey = sc.nextLine();
        System.out.print("\n");

        List<Movie> movieList = movieControl.retrieveMovieList();

        do {
            endOfShowing = true;
            for (Movie m : movieList) {
                double sum = 0.0;
                if (m.getTitle().toLowerCase().indexOf(searchKey) != -1) {
                    if (m.getMovieStatus().equals("End of Showing")) {
                        continue;
                    }
                    endOfShowing = false;
                    System.out.println("===== Search Results =====");
                    System.out.format("Movie Title    : %s\n", m.getTitle());
                    System.out.format("Movie Type     : %s\n", m.getMovieType());
                    System.out.format("Movie Rating   : %s\n", m.getMovieRating());
                    System.out.format("Duration       : %s\n", m.getDuration());
                    System.out.format("Synopsis       : %s\n", staffControl.wordWrap(m.getSynopsis()));
                    System.out.format("Director       : %s\n", m.getDirector());
                    System.out.format("Cast           : %s\n", m.getCast());
                    System.out.format("Showing Status : %s\n", m.getMovieStatus());
                   
                    List<Review> reviewList = m.getReviews();
                    if (reviewList.size() > 1) {
                        for (Review re : reviewList)
                            sum += re.getRating();
                        System.out.format("Overall Rating: %.1f / 5.0\n", sum/reviewList.size());
                    }
                    else
                        System.out.println("Overall Ratings: N/A");

                    if (reviewList.size() > 0) {
                        for (Review r : reviewList) {
                            System.out.println("User Rating    : " + r.getRating() + " / 5");
                            System.out.println("User Review    : " + staffControl.wordWrap(r.getFeedback()));
                        }
                    }
                    System.out.print("\n");
                }
            }

            if (endOfShowing) {
                System.out.println("The search has found no results!");
                System.out.println("Enter Search Keyword: ");
                searchKey = sc.nextLine();
                System.out.print("\n");
            }

        } while (endOfShowing);
        c_menu.show();
    }

    /**
     * This method lists all the movies and asks the user to select the movie that they want to rate/provide feedback.
     * The new review will be added into the Movie.json file. And the overall Ratings will be updated as well.
     */
    public void getRatingsFromUser() {
        int movieUser, rating;
        String feedback;
        double overallRat;
        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);

        MovieController movieControl = new MovieController();
        List<Movie> movieList = movieControl.retrieveMovieList();

        System.out.println("==== List of Movies ====");
        int i = 0;
        System.out.println("ID    Title");
        for (Movie m : movieList) {
            System.out.format("%-5s %s\n", m.getMovieID(), m.getTitle());
            i++;
        }
        System.out.println("Enter Movie Index to submit a review (or -1 to go back to customer menu): ");
        movieUser = sc.nextInt();

        if (movieUser != -1) {
            Movie m = movieList.get(movieUser - 1);
            List<Review> reviewList = m.getReviews();

            System.out.println("Enter Rating (1-5): ");
            rating = sc.nextInt();

            System.out.println("Enter your Feedback: ");
            feedback = sc2.nextLine();

            Review r = new Review(feedback, rating);

            reviewList.add(r);

            movieControl.updateMovie(m);


            overallRat = movieControl.getMovieRatings(movieUser);
            m.setRatings(overallRat);
            movieControl.updateMovie(m);

        }

        c_menu.show();
    }

}

