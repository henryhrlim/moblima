package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dataAccess.MovieHandler;
import entity.Cineplex;
import entity.Movie;
import entity.Review;
import presentation.CustomerMenu;
import presentation.StaffMenu;
/**
 * This class describes the communication of the Movie 
 * with the data handler. It also contains business logic of the system.
 * @version 1.0
 * @since 10/10/2015
 */


 /**
  * Class: MovieController
  * 
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
  *
  */
public class MovieController {
	private CustomerMenu c_menu = new CustomerMenu();
	
	
	/**
	 * Constructor for Movie Controller
	 */
	public MovieController() {

		
	}
	
	/**
	 * This method creates a new movie object by going through the movie handler and adds the movie object into the Movie.json file. 
	 * @param m This is the Movie object
	 */
	public void createMovie(Movie m){
		MovieHandler handler = new MovieHandler();
		handler.create(m);
	}
	
	/**
	 * This method will retrieve all the movies from the Movie.json file through the MovieHandler. 
	 * All the movie objects will be placed into a list.
	 * @return This return statement will return a list of Movie objects
	 */
	public List<Movie> retrieveMovieList() {
		MovieHandler handler = new MovieHandler();
		handler.retrieve();
		List<Movie> mList = handler.getMovieList();
		if(mList == null)
			mList = new ArrayList<Movie>();
		return mList;
	}

	public List<Movie> retrieveMovieList(List<Movie> mList) {
		MovieHandler handler = new MovieHandler();
		handler.retrieve();
		List<Movie> movieList = new ArrayList<Movie>();
		List<Movie> tempList = handler.getMovieList();
		if(tempList == null)
			tempList = new ArrayList<Movie>();
		for(Movie m: tempList){
			for(Movie movie: mList){
				if(movie.getMovieID() == m.getMovieID()){
					movieList.add(m);
				}
			}
		}
		return movieList;
	}
	
	/**
	 * This method takes in the movie object and updates it into the Movie.json file. 
	 * @param m This is an object of Movie
	 * @return boolean This is the return status if the movie has been updated successfully. 
	 */
	public boolean updateMovie(Movie m){
		MovieHandler handler = new MovieHandler();
		if(handler.update(m))
			return true;
		else
			return false;
	}
	
	/**
	 * This method takes in the movieID and changes the showing status to "End of Showing".
	 * The movie object will not be removed from the Movie.json file. 
	 * @param movieID This is an integer number of the movieID
	 * @return boolean This is the return status if the deletion was successful.  
	 */
	public boolean removeMovie(int movieID) {
		MovieHandler handler = new MovieHandler();
		if(handler.delete(movieID))
			return true;
		else
			return false;
	}
	
	/**
	 * This method takes in the movieID and calculates the overall ratings for the respective movie. 
	 * The overall ratings is calculated by getting the average of all the ratings retrieved from the Review List.
	 * @param movieID This is an integer number of the movieID
	 * @return double This returns the overall Ratings of the movie, rounded to 1 decimal place.
	 */
	public double getMovieRatings(int movieID) {
		double overallRating, total=0;
		MovieHandler handler = new MovieHandler();
		Movie m = handler.retrieveMovieDetails(movieID);
		List<Review> reviewList = m.getReviews();
		for (Review r: reviewList){
			total += r.getRating();
		}
		overallRating = total/reviewList.size();
		return (double)Math.round(overallRating*10)/10;
		//return overallRating;
	}
	
	/******************************************************************************************************************
	 * 												Relevant Methods							                      *
	 ******************************************************************************************************************/	
	
	/**
	 * This method lists all movies except those that are "end of showing"
	 */
	public void listAllMovies(){
		StaffController staffControl = new StaffController();
		
		int movieUser;
		Scanner sc = new Scanner(System.in);
		
		MovieController movieControl = new MovieController();
		List<Movie> movieList = movieControl.retrieveMovieList();

		System.out.println("*************List of Movies: *************");
		for (Movie m : movieList) {
			if(m.getMovieStatus().equals("End of Showing")){
				continue;
			}
			System.out.format("|%s  Movie Title: %s",m.getMovieID(),m.getTitle());
			System.out.println("\n");
		}
		System.out.println("Enter Movie ID to view more details (or -1 to go back to main menu): ");
		movieUser = sc.nextInt();
		
		if(movieUser != -1) {
			for(Movie m: movieList){
				if(movieUser == m.getMovieID()){
					System.out.println("***********Movie Details**********");
					System.out.format("Movie Title   : %s\n", m.getTitle());
					System.out.format("Movie Type    : %s\n", m.getMovieType());
					System.out.format("Movie Rating  : %s\n", m.getMovieRating());
					System.out.format("Duration      : %s\n", m.getDuration());
					System.out.format("Synopsis      : %s\n", staffControl.wordWrap(m.getSynopsis()));
					System.out.format("Director      : %s\n", m.getDirector());
					System.out.format("Cast          : %s\n", m.getCast());
					System.out.format("Showing Status: %s\n", m.getMovieStatus());
					
					//~~~~~~~~~ADDED HERE!!!!!!!~~~~~~~~
					List<Review> reviewList =  m.getReviews();
					//overall ratings will only be displayed when there are more than 1 individual rating
					if(reviewList.size()>1)
						System.out.println("Overall Reviewer Ratings: " + m.getRatings());
					
					if(reviewList.size()>0) {
						System.out.println("****************Past reviewers rating**************** ");

						for(Review r: reviewList) {
							System.out.println("Past Rating   : " + r.getRating());
							System.out.println("Past feedback : " + staffControl.wordWrap(r.getFeedback()));
						}
					}
					
					System.out.println("\n");
					break;
				}
			}
			//re-enter movie ID
		}
		c_menu.show();
		
	}
	/**
	 * This method is to search movie by customer key word search
	 */
	public void searchMovie(){
		StaffController staffControl = new StaffController();
		Scanner sc = new Scanner(System.in);
		String searchKey = "";
		
		System.out.println("Enter Search Keyword: ");
		searchKey = sc.nextLine();
		//System.out.println(searchKey);
		MovieController movieControl = new MovieController();
		List<Movie> movieList = movieControl.retrieveMovieList();
		boolean endOfShowing;
		do {
			endOfShowing = true;
			for (Movie m : movieList) {
				
				if(m.getTitle().toLowerCase().indexOf(searchKey) != -1){
					if(m.getMovieStatus().equals("End of Showing")){
						continue;
					}
					endOfShowing = false;
					System.out.println("***********Movie Search Results**********");
					System.out.format("Movie Title   : %s\n", m.getTitle());
					System.out.format("Movie Type    : %s\n", m.getMovieType());
					System.out.format("Movie Rating  : %s\n", m.getMovieRating());
					System.out.format("Duration      : %s\n", m.getDuration());
					System.out.format("Synopsis      : %s\n", staffControl.wordWrap(m.getSynopsis()));
					System.out.format("Director      : %s\n", m.getDirector());
					System.out.format("Cast          : %s\n", m.getCast());
					System.out.format("Showing Status: %s\n", m.getMovieStatus());
					System.out.println("\n");
					List<Review> reviewList =  m.getReviews();
					//overall ratings will only be displayed when there are more than 1 individual rating
					if(reviewList.size()>1)
						System.out.println("Overall Reviewer Ratings: " + movieControl.getMovieRatings(m.getMovieID()));
					
					if(reviewList.size()>0) {
						System.out.println("\n");
						System.out.println("*************Past reviewers rating************* ");

						for(Review r: reviewList) {
							System.out.println("Past Rating   : " + r.getRating());
							System.out.println("Past feedback : " + staffControl.wordWrap(r.getFeedback()));
							System.out.println("\n");
						}
					}
				}
			}
			
			if(endOfShowing){
				System.out.println("The search has found no results!");
				System.out.println("Re-enter Search Keyword: ");
				searchKey = sc.nextLine();
			}
			
		}while(endOfShowing);
		
		
		c_menu.show();
		
	}
	
	/**
	 * This method lists the cineplexes
	 * @return cineplex of user choice
	 */
	public Cineplex listCineplex(){
		Scanner sc = new Scanner(System.in);
		CineplexController cineControl = new CineplexController();
		List<Cineplex> cineplexList = cineControl.retrieveCineplexList();
		
		int i=0;
		for (Cineplex c : cineplexList) {
			System.out.format("|"+ (i+1) +". Cineplex Name: %s  Location: %s",c.getCineplexName(),c.getLocation());
			i++;
			System.out.println("\n");
		}
		
		System.out.println(">> Choose Cineplex: -----------");
		int cineplexChoice = sc.nextInt();
		
		Cineplex cineUserChoice = cineplexList.get(cineplexChoice-1);
	
		List<Movie> movieList = retrieveMovieList(cineUserChoice.getMovie());
		cineUserChoice.setMovie(movieList);
		
		return cineUserChoice;
	}
	
	/**
	 * This method lists movie specific to cineplexs
	 * @param cineUserChoice which is the cineplex chosen by the user
	 * @return movie of user choice
	 */
	public Movie listMovieSpecific(Cineplex cineUserChoice){
		Scanner sc = new Scanner(System.in);
		
		List<Movie> movieList = cineUserChoice.getMovie();
		
		System.out.println("*************List of Movies: *************");
		int i = 0;
		for (Movie m : movieList) {
			System.out.format("|"+ (i+1) +". Movie Title: %s",m.getTitle());
			System.out.println("\n");
			i++;
		}
		
		System.out.println(">>Select Movie: ---------------");
		
		int movieChoice = sc.nextInt();

		Movie movUserChoice = movieList.get(movieChoice-1);
		System.out.println("Selected Movie: ");
		System.out.println("Title: " + movUserChoice.getTitle());
		System.out.println("Type: " + movUserChoice.getMovieType());
		System.out.println("Ratings: " + movUserChoice.getRatings());
		
		return movUserChoice;
	
	}
	
	/**
	 * This method lists all the movies and asks the user to select the movie that they want to rate/provide feedback.
	 * The new review will be added into the Movie.json file. And the overall Ratings will be updated as well. 
	 */
	public void getRatingsFromUser(){
		int movieUser, rating;
		String feedback;
		double overallRat;
		Scanner sc = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);
		
		MovieController movieControl = new MovieController();
		List<Movie> movieList = movieControl.retrieveMovieList();

		System.out.println("*************List of Movies: *************");
		int i = 0;
		for (Movie m : movieList) {
			System.out.format("|"+ (i+1) +". Movie Title: %s",m.getTitle());
			System.out.println("\n");
			i++;
		}
		System.out.println("Enter Movie Index to submit a review (or -1 to go back to customer menu): ");
		movieUser = sc.nextInt();
		
		if(movieUser != -1) {
			Movie m = movieList.get(movieUser-1);
			List<Review> reviewList =  m.getReviews();
			
			System.out.println("Enter Rating (1-5): ");
			rating = sc.nextInt();
			
			System.out.println("Enter your Feedback: ");
			feedback = sc2.nextLine();
			
			Review r = new Review(feedback, rating);
			
			reviewList.add(r);
			
			movieControl.updateMovie(m);
			
			//update overall ratings
			overallRat = movieControl.getMovieRatings(movieUser);
			m.setRatings(overallRat);
			movieControl.updateMovie(m);
				
		}
		
		c_menu.show();
	}
	
}

