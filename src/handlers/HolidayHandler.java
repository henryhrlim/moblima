package dataAccess;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import entity.Holiday;

/**
 * HolidayHandler
 * @version 1.0
 * @since 10/10/2015
*/

public class HolidayHandler extends DataHandler{
	private List<Holiday> holidayList;

	public HolidayHandler(){
		
	}
	
	/**
	 * Writes a new Holiday into the JSON file.
	 * The Holiday object h's variables all must be set.
	 * @param h This is the object of Holiday.
	 */
	public void create(Holiday h){
		loadData("Holiday");
		if(this.holidayList == null)
			this.holidayList = new ArrayList<Holiday>();
		if(h != null)
			this.holidayList.add(h);
		saveData("Holiday");
	}
	
	/**
	 * Call loadData method from parent class.
	 * Retrieve data from JSON file and save in the holidayList.
	 */
	public void retrieve(){
		loadData("Holiday");
	}
	
	/**
	 * This method update the Holiday object in the JSON file.
	 * It will update data by the date as the index.
	 * If updated return true, else return false.
	 * The Holiday object h's variables all must be set.
	 * @param h This is the object of Holiday.
	 * @return boolean
	 */
	public boolean update(Holiday h){
		loadData("Holiday");
		for(int i = 0;i<this.holidayList.size();i++){
			Holiday holiday = this.holidayList.get(i);
			if(holiday.getDate().equals(h.getDate())){
				this.holidayList.set(i, h);
				saveData("Holiday");
				return true;
			}
			else if(holiday.getHolidayName().equals(h.getHolidayName())){
				this.holidayList.set(i, h);
				saveData("Holiday");
				return true;
			}
		}
		return false;
	}
	
	/**
	 * This method update the Holiday object in the JSON file.
	 * It will delete data by the date.
	 * If deleted return true, else return false.
	 * @param h This is the object of Holiday.
	 * @return boolean
	 */
	public boolean delete(Holiday h){
		loadData("Holiday");
		for(int i = 0;i<this.holidayList.size();i++){
			Holiday holiday = this.holidayList.get(i);
			if(holiday.getDate().equals(h.getDate())){
				this.holidayList.remove(i);
				saveData("Holiday");
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Override readJSON method from parent class(DataHandler).
	 * arr contains the data retrieved from the JSON file.
	 * This method will format the arr JSONArray object and save it into the holidayList.
	 * @param arr This is a JSONArray object.
	 */
	@Override
	protected void readJSON(JSONArray arr) {
		// TODO Auto-generated method stub
		this.holidayList = new ArrayList<Holiday>();
		String date;
		String holidayName;
		
		for(int i = 0; i < arr.size();i++){
			JSONObject holidayJson = (JSONObject)arr.get(i);
			date = (String)holidayJson.get("date");
			holidayName = (String)holidayJson.get("holidayName");
			holidayList.add(new Holiday(date, holidayName));
		}
	}

	/**
	 * Override saveDataToJSON method from parent class(DataHandler).
	 * This method will format the holidayList into a JSONArray object and return it.
	 * @return JSONArray object.
	 */
	@Override
	protected JSONArray saveDataToJSON() {
		// TODO Auto-generated method stub
		JSONArray holidayArr = new JSONArray();
		
		for(Holiday h : this.holidayList){
			JSONObject holidayJson = new JSONObject();
			holidayJson.put("date", h.getDate());
			holidayJson.put("holidayName", h.getHolidayName());
			holidayArr.add(holidayJson);
		}
		return holidayArr;
	}

	/**
	 * Gets the list of holidays
	 * @return HolidayList
	 */
	public List<Holiday> getHolidayList() {
		return holidayList;
	}

}
