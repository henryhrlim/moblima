package controller;

import java.util.ArrayList;
import java.util.List;

import dataAccess.MovieHandler;
import dataAccess.ShowTimeHandler;
import entity.Movie;
import entity.Seat;
import entity.ShowTime;
/**
 * Class: ShowTimeController
 * 
 * Class Methods:
 * - createShowTime(ShowTime st) : void
 * - retrieveShowTimeList() : List<ShowTime>
 * - retrieveShowTimeList(List<ShowTime> stList, int movieID): List<ShowTime> 
 * - updateShowTimeList(ShowTime st) : void
 * 
 * 
 *
 */
public class ShowTimeController {

	/**
	 * This method creates ShowTime object by ShowTimeHandler.
	 * The ShowTime object st's variables all must be set.
	 * @param st This is the object of ShowTime.
	 */
	public void createShowTime(ShowTime st){
		ShowTimeHandler handler = new ShowTimeHandler();
		handler.create(st);
	}
	
	/**
	 * This method lists ShowTime object by ShowTimeHandler.
	 */
	public List<ShowTime> retrieveShowTimeList() {
		ShowTimeHandler handler = new ShowTimeHandler();
		handler.retrieve();
		List<ShowTime> stList = handler.getShowTimeList();
		if(stList == null)
			stList = new ArrayList<ShowTime>();
		return stList;
	}
	
	
	/**
	 * This method lists ShowTime object by ShowTimeHandler.
	 * @param cinemaCode The cinema code of a cineplex.
	 */
	
	public List<ShowTime> retrieveShowTimeList(String cinemaCode) {
		ShowTimeHandler handler = new ShowTimeHandler();
		handler.retrieve();
		List<ShowTime> tempList = handler.getShowTimeList();
		List<ShowTime> showTimeList = new ArrayList<ShowTime>();
		if(tempList == null)
			tempList = new ArrayList<ShowTime>();
		for(ShowTime st : tempList){
			if(st.getCinemaCode().equals(cinemaCode))
				showTimeList.add(st);
		}
		return showTimeList;
	}
	
	
	/**
	 * This method lists ShowTime object by ShowTimeHandler.
	 * @param cinemaCode The cinema code of a cineplex.
	 * @param movieID The movie id of a movie.
	 */
	
	public List<ShowTime> retrieveShowTimeList(String cinemaCode, int movieID) {
		ShowTimeHandler handler = new ShowTimeHandler();
		handler.retrieve();
		List<ShowTime> tempList = handler.getShowTimeList();
		List<ShowTime> showTimeList = new ArrayList<ShowTime>();
		if(tempList == null)
			tempList = new ArrayList<ShowTime>();
		for(ShowTime st : tempList){
			if(st.getCinemaCode().equals(cinemaCode) && st.getMovieID() == movieID)
				showTimeList.add(st);
		}
		return showTimeList;
	}
	
	
	
	
	
	/**
	 * This method lists ShowTime object by ShowTimeHandler.
	 * @param stList List of ShowTime object.
	 * @param movieID The movie id of a movie.
	 */
	public List<ShowTime> retrieveShowTimeList(List<ShowTime> stList, int movieID) {
		ShowTimeHandler handler = new ShowTimeHandler();
		handler.retrieve();
		List<ShowTime> tempList = handler.getShowTimeList();
		List<ShowTime> showTimeList = new ArrayList<ShowTime>();
		if(tempList == null)
			tempList = new ArrayList<ShowTime>();
		for(ShowTime st : tempList){
			for(ShowTime showtime : stList){
				if(showtime.getShowTimeID() == st.getShowTimeID() && st.getMovieID() == movieID){
					showTimeList.add(st);
				}
			}
		}
		return showTimeList;
	}
	
	
	
	
	/**
	 * This method updates ShowTime object by ShowTimeHandler.
	 * The ShowTime object st's variables all must be set.
	 * @param st This is the object of ShowTime.
	 */
	public void updateShowTimeList(ShowTime st){

		ShowTimeHandler handler = new ShowTimeHandler();
		handler.updateST(st);
	}
	
	/**
	 * This method removes ShowTime object by ShowTimeHandler.
	 * @param st This is the object of ShowTime.
	 */
	public void removeShowtime(ShowTime st) {
		ShowTimeHandler handler = new ShowTimeHandler();
		handler.delete(st);
	}
}
