package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Cineplex
 * @version 1.0
 * @since 10/10/2015
*/

public class Cineplex{
	private int cineplexID;
	private String location;
	private String cineplexName;
	private List<Cinema> cinemas;
	private List<Movie> movie;
	private List<ShowTime> showTime;
	
	/**
	 * Class Constructor
	 */
	public Cineplex(){
	}
	
	
	/**
	 * Class Constructor
	 * @param cineplexID integer of cineplexID
	 * 
	 */
	public Cineplex(int cineplexID, String location, String cineplexName, List<Cinema> cinemas){
		this.cineplexID = cineplexID;
		this.location = location;
		this.cineplexName = cineplexName;
		if(cinemas == null){
			this.cinemas = new ArrayList<Cinema>();
			String code = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			this.cinemas.add(new Cinema("normal",code.charAt(cineplexID-1)+"01"));
			this.cinemas.add(new Cinema("normal",code.charAt(cineplexID-1)+"02"));
			this.cinemas.add(new Cinema("normal",code.charAt(cineplexID-1)+"03"));			
		}
		else{
			this.cinemas = cinemas;
		}
		
	}

	/**
	 * Gets the cineplexID
	 * @return cineplexID.
	 */
	public int getCineplexID() {
		return cineplexID;
	}

	/**
	 * Set the cineplexID
	 * @param cineplexID To pass integer of cineplexID
	 * 
	 */
	public void setCineplexID(int cineplexID) {
		this.cineplexID = cineplexID;
	}

	/**
	 * Gets the Location of Cineplex
	 * @return location.
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Set the Cinema Location
	 * @param location To pass string of location
	 * 
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * Gets the Cineplex Name
	 * @return cineplexName.
	 */
	public String getCineplexName() {
		return cineplexName;
	}

	/**
	 * Set the Cinema Name
	 * @param cineplexName To pass string of cineplexName
	 * 
	 */
	public void setCineplexName(String cineplexName) {
		this.cineplexName = cineplexName;
	}

	/**
	 * Gets a list of Cinemas
	 * @return cinemas.
	 */
	public List<Cinema> getCinemas() {
		return cinemas;
	}

	/**
	 * Set the list of Cinemas
	 * @param cinemas To pass string of cinemas
	 * 
	 */
	public void setCinemas(List<Cinema> cinemas) {
		this.cinemas = cinemas;
	}

	/**
	 * Gets the list of Movies
	 * @return movie.
	 */
	public List<Movie> getMovie() {
		return movie;
	}

	/**
	 * Set the list of Movies
	 * @param movie To pass string of movie
	 * 
	 */
	public void setMovie(List<Movie> movie) {
		this.movie = movie;
	}

	/**
	 * Gets the list of Show Time
	 * @return showTime.
	 */
	public List<ShowTime> getShowTime() {
		return showTime;
	}

	/**
	 * Set the list of Show Time
	 * @param showTime To pass string of showTime
	 * 
	 */
	public void setShowTime(List<ShowTime> showTime) {
		this.showTime = showTime;
	}
	
}
