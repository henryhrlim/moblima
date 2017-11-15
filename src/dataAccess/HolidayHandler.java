package dataAccess;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
	protected void readCSV(FileReader csvFile) {
		this.holidayList = new ArrayList<Holiday>();
		
		List<String> date = new ArrayList<String>();
		List<String> holidayName = new ArrayList<String>();
		
		BufferedReader br = null;
		String line;
		String csvSplitBy = ",";
		
		try {
			br = new BufferedReader(csvFile);
			String[] data;
			while ((line = br.readLine()) != null) {
				// use comma as separator
				data = line.split(csvSplitBy);
				date.add(data[0]);
				holidayName.add(data[1]);
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
		for (int i = 0; i < date.size(); i++) {
			Holiday h = new Holiday(date.get(i), holidayName.get(i));
			this.holidayList.add(h);
		}
	}
	
	protected void saveDataToCSV(String to) {
		try (FileWriter file = new FileWriter(to)) {
			for (Holiday h: holidayList) {
				file.append(h.getDate() + "," + h.getHolidayName() + "\n");
			}
			file.flush();
			file.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * Gets the list of holidays
	 * @return HolidayList
	 */
	public List<Holiday> getHolidayList() {
		return holidayList;
	}

}
