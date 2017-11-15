package controller;

import java.util.ArrayList;
import java.util.List;

import dataAccess.HolidayHandler;
import entity.Holiday;
/**
 * Class: HolidayController
 * 
 * Class Methods:
 * - createHoliday(Holiday h): void
 * - retrieveHolidayList() : List<Holiday> 
 * - updateHoliday(Holiday h): void
 * - deleteHoliday(Holiday h): void
 * - isHoliday(String date): boolean
 * 
 *
 */
public class HolidayController {
	
	/**
	 * This method creates Holiday object by HolidayHandler.
	 * The Holiday object h's variables all must be set.
	 * @param h This is the object of Holiday.
	 */
	public void createHoliday(Holiday h){
		HolidayHandler handler = new HolidayHandler();
		handler.create(h);
	}
	
	/**
	 * This method lists Holiday object by HolidayHandler.
	 */
	public List<Holiday> retrieveHolidayList(){
		HolidayHandler handler = new HolidayHandler();
		handler.retrieve();
		List<Holiday> hList = handler.getHolidayList();
		if(hList == null)
			hList = new ArrayList<Holiday>();
		return hList;
	}
	
	/**
	 * This method updates Holiday object by HolidayHandler.
	 * The Holiday object h's variables all must be set.
	 * @param h This is the object of Holiday.
	 */
	public void updateHoliday(Holiday h){
		HolidayHandler handler = new HolidayHandler();
		handler.update(h);
	}
	
	/**
	 * This method deletes Holiday object by HolidayHandler.
	 * @param h This is the object of Holiday.
	 */
	public void deleteHoliday(Holiday h){
		HolidayHandler handler = new HolidayHandler();
		handler.delete(h);
	}
	
	/**
	 * This method checks if a date is listed as a public holiday
	 * If the dates match return true, else return false.
	 * @param date Refers to the date of the showtime movie.
	 * @return boolean
	 */		
	public boolean isHoliday(String date){
		HolidayHandler handler = new HolidayHandler();
		handler.retrieve();
		List<Holiday> holidayList =  handler.getHolidayList();
		for(Holiday h: holidayList){
			if(h.getDate().equals(date)){
				return true;
			}
		}
		return false;
	}
}
