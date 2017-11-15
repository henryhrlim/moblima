package dataAccess;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import entity.Holiday;
import entity.Movie;
import entity.PriceChart;

/**
 * PriceChartHandler
 * @version 1.0
 * @since 10/10/2015
*/

public class PriceChartHandler extends DataHandler {
	/**
	 * The list of PriceChart.
	 */
	private List<PriceChart> priceChartList;

	public PriceChartHandler(){
		
	}
	
	/**
	 * Call loadData method from parent class.
	 * Retrieve data from JSON file and save in the priceChartList.
	 */
	public void retrieve(){
		loadData("PriceChart");
	}
	
	/**
	 * Writes a new PriceChart into the JSON file.
	 * The PriceChart object pc's variables all must be set.
	 * @param pc This is the object of PriceChart.
	 */
	public void create(PriceChart pc) {
		loadData("PriceChart");
		if (this.priceChartList == null)
			this.priceChartList = new ArrayList<PriceChart>();
		if (pc != null)
			this.priceChartList.add(pc);
		saveData("PriceChart");
	}
	
	/**
	 * This method update the PriceChart object in the JSON file.
	 * It will update data by the priceChartID as the index.
	 * If updated return true, else return false.
	 * The PriceChart object pc's variables all must be set.
	 * @param pc This is the object of PriceChart.
	 * @return boolean
	 */
	public boolean update(PriceChart pc){
		loadData("PriceChart");
		for(int i = 0; i < this.priceChartList.size();i++){
			PriceChart priceChart = this.priceChartList.get(i);
			if(priceChart.getPriceChartID() == pc.getPriceChartID()){
				this.priceChartList.set(i, pc);
				saveData("PriceChart");
				return true;
			}
		}
		
		return false;
	}
	protected void readCSV(FileReader csvFile) {
		this.priceChartList = new ArrayList<PriceChart>();
		
		List<String> priceChartID = new ArrayList<String>();
		List<String> movieType = new ArrayList<String>();
		List<String> cinemaType = new ArrayList<String>();
		List<String> price = new ArrayList<String>();
		List<String> day = new ArrayList<String>();
		List<String> age = new ArrayList<String>();
		
		BufferedReader br = null;
		String line;
		String csvSplitBy = ",";
		
		try {
			br = new BufferedReader(csvFile);
			String[] data;
			while ((line = br.readLine()) != null) {
				// use comma as separator
				data = line.split(csvSplitBy);
				priceChartID.add(data[0]);
				movieType.add(data[1]);
				cinemaType.add(data[2]);
				price.add(data[3]);
				day.add(data[4]);
				age.add(data[5]);
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (br != null) {
				try {
					br.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		for (int i = 0; i < priceChartID.size(); i++) {
			PriceChart pc = new PriceChart(Integer.valueOf(priceChartID.get(i)), cinemaType.get(i), age.get(i), day.get(i), movieType.get(i), Double.valueOf(price.get(i)));
			this.priceChartList.add(pc);
		}
	}
	
	/**
	 * Override readJSON method from parent class(DataHandler).
	 * arr contains the data retrieved from the JSON file.
	 * This method will format the arr JSONArray object and save it into the priceChartList.
	 * @param arr This is a JSONArray object.
	 */
	@Override
	protected void readJSON(JSONArray arr) {
		// TODO Auto-generated method stub
		this.priceChartList = new ArrayList<PriceChart>();
		
		int priceChartID;
		String cinemaType;
		String age;
		String day;
		String movieType;
		double price;
		long temp;
		for (int i = 0; i < arr.size(); i++) {
			JSONObject pcJson = (JSONObject) arr.get(i);
			temp = (long)pcJson.get("priceChartID");
			priceChartID = (int)temp;
			cinemaType = (String) pcJson.get("cinemaType");
			age = (String) pcJson.get("age");
			day = (String) pcJson.get("day");
			movieType = (String) pcJson.get("movieType");
			price = (double) pcJson.get("price");
			this.priceChartList.add(new PriceChart(priceChartID, cinemaType, age, day,
					movieType, price));
		}

	}

	protected void saveDataToCSV(String to) {
		try (FileWriter file = new FileWriter(to)) {
			for (PriceChart pc: priceChartList) {
				file.append(pc.getPriceChartID() + "," + pc.getMovieType() + "," + pc.getCinemaType() + "," + pc.getPrice() + "," + pc.getDay() + "," + pc.getAge() + "\n");
			}
			file.flush();
			file.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Override saveDataToJSON method from parent class(DataHandler).
	 * This method will format the priceChartList into a JSONArray object and return it.
	 * @return JSONArray object.
	 */
	@Override
	protected JSONArray saveDataToJSON() {
		// TODO Auto-generated method stub
		JSONArray pcArr = new JSONArray();

		for (PriceChart pc : this.priceChartList) {
			JSONObject pcJson = new JSONObject();
			pcJson.put("priceChartID", pc.getPriceChartID());
			pcJson.put("cinemaType", pc.getCinemaType());
			pcJson.put("age", pc.getAge());
			pcJson.put("day", pc.getDay());
			pcJson.put("movieType", pc.getMovieType());
			pcJson.put("price", pc.getPrice());
			pcArr.add(pcJson);
		}
		return pcArr;
	}

	/**
	 * Gets the priceChartList
	 * @return priceChartList.
	 */
	public List<PriceChart> getPriceChartList() {
		return priceChartList;
	}

}
