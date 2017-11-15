package controller;

import java.util.ArrayList;
import java.util.List;

import dataAccess.PriceChartHandler;
import dataAccess.ShowTimeHandler;
import entity.PriceChart;
import entity.ShowTime;
/**
 * Class: PriceChartController
 * 
 * Class Methods:
 * - getTicketPrice(PriceChart pc): double
 * - createPriceChart(PriceChart pc) : void
 * - retrievePriceChartList() : List<PriceChart> 
 * - retrievePriceChartList(String ageGrp) :List<PriceChart>
 * - retrievePriceChartList(String ageGrp, String cineType, String movType) :List<PriceChart>
 * - updatePriceChart(PriceChart pc): boolean
 *
 */

public class PriceChartController {

	/**
	 * This method retrieves the price of the ticket
	 * according to the age group and/or movie type
	 * @param pc This is the object of PriceChart
	 * @return double
	 */
	public double getTicketPrice(PriceChart pc){
		PriceChartHandler handler = new PriceChartHandler();
		handler.retrieve();
		List<PriceChart> pcList =  handler.getPriceChartList();
		String cinemaType = pc.getCinemaType().toLowerCase();
		String age = pc.getAge().toLowerCase();
		String day = pc.getDay().toLowerCase();
		String movieType = pc.getMovieType().toLowerCase();
		double price = 0;
		if(cinemaType.equals("platinum")){
			movieType = "Any";
			age = "Standard";			
		}
//		else{		
//			if(age.equals("children") || age.equals("senior")){
//				movieType = "";
//				cinemaType = "";
//				day = "";
//			}
//		}
		for(PriceChart pricechart : pcList){
			String pcCinemaType = pricechart.getCinemaType().toLowerCase();
			String pcMovieType = pricechart.getMovieType().toLowerCase();
			String pcDay = pricechart.getDay().toLowerCase();
			String pcAge = pricechart.getAge().toLowerCase();
			if(pcCinemaType.equals(cinemaType)){
				if(pcMovieType.equals(movieType)){
					if(pcDay.equals(day)){
						if(pcAge.equals(age)){
							price = pricechart.getPrice();
						}
					}
				}
			}
		}
		return price;
	}
	
	
	/**
	 * This method creates PriceChart object by PriceChartHandler.
	 * The PriceChart object pc's variables all must be set.
	 * @param pc This is the object of PriceChart.
	 */
	public void createPriceChart(PriceChart pc){
		PriceChartHandler handler = new PriceChartHandler();
		handler.create(pc);
	}
	
	/**
	 * This method lists PriceChart object by PriceChartHandler.
	 */
	public List<PriceChart> retrievePriceChartList(){
		PriceChartHandler handler = new PriceChartHandler();
		handler.retrieve();
		return handler.getPriceChartList();
	}
	
	/**
	 * This method lists PriceChart object by PriceChartHandler.
	 * @param ageGrp A specific age group.
	 */
	public List<PriceChart> retrievePriceChartList(String ageGrp) {
		PriceChartHandler handler = new PriceChartHandler();
		handler.retrieve();
		List<PriceChart> tempList = handler.getPriceChartList();
		List<PriceChart> priceChartList = new ArrayList<PriceChart>();
		for (PriceChart pcTemp : tempList) {
			if (pcTemp.getAge().equals(ageGrp)) {
				priceChartList.add(pcTemp);
			}
		}
		return priceChartList;
	}

	public List<PriceChart> retrievePriceChartListByCinemaType(String cineType) {
		PriceChartHandler handler = new PriceChartHandler();
		handler.retrieve();
		List<PriceChart> tempList = handler.getPriceChartList();
		List<PriceChart> priceChartList = new ArrayList<PriceChart>();
		for (PriceChart pcTemp : tempList) {
			if (pcTemp.getCinemaType().equals(cineType)) {
				priceChartList.add(pcTemp);
			}
		}
		return priceChartList;
	}

	/**
	 * This method lists PriceChart object by PriceChartHandler.
	 * @param ageGrp A specific age group.
	 * @param cineType The cinema type of a cineplex.
	 * @param movType The movie type of a movie.
	 */
	public List<PriceChart> retrievePriceChartList(String ageGrp, String cineType, String movType) {
		PriceChartHandler handler = new PriceChartHandler();
		handler.retrieve();
		List<PriceChart> tempList = handler.getPriceChartList();
		List<PriceChart> priceChartList = new ArrayList<PriceChart>();
		for (PriceChart pcTemp : tempList) {
			if (pcTemp.getAge().equals(ageGrp) && pcTemp.getCinemaType().equals(cineType) && pcTemp.getMovieType().equals(movType)) {
				priceChartList.add(pcTemp);
			}
		}
		return priceChartList;
	}
	
	
	/**
	 * This method updates PriceChart object by PriceChartHandler.
	 * The PriceChart object pc's variables all must be set
	 * @param pc This is the object of PriceChart
	 */
	public boolean updatePriceChart(PriceChart pc){
		PriceChartHandler handler = new PriceChartHandler();
		if(handler.update(pc)){
			return true;
		}
		return false;
	}
	

}
