package dataAccess;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import entity.Seat;
import entity.ShowTime;
import entity.Tickets;

/**
 * ShowTimeHandler
 * @version 1.0
 * @since 10/10/2015
*/


public class ShowTimeHandler extends DataHandler {
	/**
	 * The list of ShowTime.
	 */
	private List<ShowTime> showTimeList;
	private List<Seat> seats;

	public ShowTimeHandler() {

	}

	/**
	 * Gets the showTimeList
	 * @return showTimeList.
	 */
	public List<ShowTime> getShowTimeList() {
		return showTimeList;
	}

	/**
	 * Writes a new ShowTime into the JSON file.
	 * The ShowTime object st's variables all must be set.
	 * @param st This is the object of ShowTime.
	 */
	public void create(ShowTime st) {
		loadData("ShowTime");
		if (this.showTimeList == null)
			this.showTimeList = new ArrayList<ShowTime>();
		if (st != null)
			this.showTimeList.add(st);
		saveData("ShowTime");
	}

	/**
	 * Call loadData method from parent class.
	 * Retrieve data from JSON file and save in the showTimeList.
	 */
	public void retrieve() {
		loadData("ShowTime");
		if (this.showTimeList == null)
			this.showTimeList = new ArrayList<ShowTime>();
	}
	
	
	/**
	 * This method update the ShowTime object in the JSON file.
	 * It will update data by the showTimeID as the index.
	 * If updated return true, else return false.
	 * The ShowTime object st's variables all must be set.
	 * @param st This is the object of ShowTime.
	 * @return boolean
	 */
	public boolean updateST(ShowTime st){
		loadData("ShowTime");
		for (int i = 0; i < this.showTimeList.size(); i++) {
			ShowTime showtime = this.showTimeList.get(i);
			if(st.getShowTimeID() == showtime.getShowTimeID()){
				this.showTimeList.set(i, st);
				saveData("ShowTime");
				return true;
			}
		}
		return false;
	}
	
	/**
	 * This method update the seat in ShowTime object in the JSON file.
	 * It will update data by the showTimeID as the index.
	 * If updated return true, else return false.
	 * The Tickets object ticket's variables all must be set.
	 * @param showTimeID This is ShowTime's showTimeID.
	 * @param row This is ShowTime's row.
	 * @param column This is ShowTime's column.
	 * @param ticket This is Tickets's ticket.
	 * @return boolean
	 */
	public boolean updateSeat(int showTimeID, String row, int column,
			Tickets ticket) {
		loadData("ShowTime");
		for (int i = 0; i < this.showTimeList.size(); i++) {
			ShowTime st = this.showTimeList.get(i);
			if (st.getShowTimeID() == showTimeID) {
				List<Seat> seatList = st.getSeats();
				for (int x = 0; x < seatList.size(); x++) {
					Seat s = seatList.get(x);
					if (s.getRow().equals(row) && s.getColumn() == column) {
						s.setStatus(true);
						s.setTicket(ticket);
						seatList.set(x, s);
						st.setSeats(seatList);
						this.showTimeList.set(i, st);
						saveData("ShowTime");
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * This method update the ShowTime object in the JSON file.
	 * It will delete data by the showTimeID.
	 * If deleted return true, else return false.
	 * @param showTimeID This is the ShowTime's showTimeID.
	 * @return boolean
	 */
	public boolean delete(ShowTime st) {
		loadData("ShowTime");
		for (int i = 0; i < this.showTimeList.size(); i++) {
			ShowTime showtime = this.showTimeList.get(i);
			if (showtime.getShowTimeID() == st.getShowTimeID()) {
				this.showTimeList.remove(i);
				saveData("ShowTime");
				return true;
			}
		}
		return false;
	}

	/**
	 * Override readJSON method from parent class(DataHandler).
	 * arr contains the data retrieved from the JSON file.
	 * This method will format the arr JSONArray object and save it into the showTimeList.
	 * @param arr This is a JSONArray object.
	 */
	@Override
	protected void readJSON(JSONArray arr) {
		// TODO Auto-generated method stub
		this.showTimeList = new ArrayList<ShowTime>();
		int showTimeID;
		String time;
		String day;
		String date;
		int movieID;
		String cinemaCode;
		long temp;
		for (int i = 0; i < arr.size(); i++) {
			JSONObject stJson = (JSONObject) arr.get(i);
			temp = (long) stJson.get("showTimeID");
			showTimeID = (int) temp;
			time = (String) stJson.get("time");
			day = (String) stJson.get("day");
			date = (String) stJson.get("date");
			temp = (long) stJson.get("movieID");
			movieID = (int) temp;
			cinemaCode = (String)stJson.get("cinemaCode");
			List<Seat> seatList = new ArrayList<Seat>();
			JSONArray showTimeArr = (JSONArray) stJson.get("seats");
			for (int x = 0; x < showTimeArr.size(); x++) {
				JSONObject seatJson = (JSONObject) showTimeArr.get(x);
				JSONObject ticket = (JSONObject) seatJson.get("ticket");
				temp = (long) seatJson.get("column");
				int column = (int) temp;
				String row = (String) seatJson.get("row");
				String seatType = (String) seatJson.get("seatType");
				boolean status = (boolean) seatJson.get("status");
				temp = (long) ticket.get("ticketID");
				Tickets t = new Tickets((int) temp, (String) ticket.get("age"));
				Seat s = new Seat(row, column, seatType, status, t);
				seatList.add(s);
			}

			ShowTime st = new ShowTime(showTimeID, time, day, date, movieID,
					seatList,cinemaCode);
			this.showTimeList.add(st);
		}
	}

	/**
	 * Override saveDataToJSON method from parent class(DataHandler).
	 * This method will format the showTimeList into a JSONArray object and return it.
	 * @return JSONArray object.
	 */
	@Override
	protected JSONArray saveDataToJSON() {
		// TODO Auto-generated method stub
		JSONArray stArr = new JSONArray();
		for (ShowTime st : this.showTimeList) {
			JSONObject stJson = new JSONObject();
			
			JSONArray seatArr = new JSONArray();
			List<Seat> seatList = st.getSeats();
			for (Seat s : seatList) {
				JSONObject seatJson = new JSONObject();
				JSONObject ticketJson = new JSONObject();
				seatJson.put("row", s.getRow());
				seatJson.put("column", s.getColumn());
				seatJson.put("seatType", s.getSeatType());
				seatJson.put("status", s.getStatus());
				Tickets t = s.getTicket();
				if(t != null){
					ticketJson.put("ticketID", t.getTicketID());
					ticketJson.put("age", t.getAge());
				}
				else{
					ticketJson.put("ticketID", 0);
					ticketJson.put("age", null);
				}
				seatJson.put("ticket", ticketJson);
				seatArr.add(seatJson);
			}

			stJson.put("movieID", st.getMovieID());
			stJson.put("date", st.getDate());
			stJson.put("day", st.getDay());
			stJson.put("time", st.getTime());
			stJson.put("showTimeID", st.getShowTimeID());
			stJson.put("cinemaCode", st.getCinemaCode());
			stJson.put("seats", seatArr);
			stArr.add(stJson);
		}
		return stArr;
	}
	protected void readCSV(FileReader csvFile) {
		this.showTimeList = new ArrayList<ShowTime>();
		this.seats = new ArrayList<Seat>();
		
		List<String> date = new ArrayList<String>();
		List<String> showTimeID = new ArrayList<String>();
		List<String> cinemaCode = new ArrayList<String>();
		List<String> movieID = new ArrayList<String>();
		List<String> time = new ArrayList<String>();
		List<String> day = new ArrayList<String>();
		List<String> row = new ArrayList<String>();
		List<String> column = new ArrayList<String>();
		List<String> seatType = new ArrayList<String>();
		List<String> status = new ArrayList<String>();
		List<String> ticketID = new ArrayList<String>();
		List<String> age = new ArrayList<String>();
		
		BufferedReader br = null;
		BufferedReader seatsBr = null;
		String line;
		String seatsLine;
		String csvSplitBy = ",";
		
		try {
			br = new BufferedReader(csvFile);
			seatsBr = new BufferedReader(new FileReader("src/storage/Seats.csv"));
			String[] data;
			while ((line = br.readLine()) != null) {
				data = line.split(csvSplitBy);
				date.add(data[0]);
				showTimeID.add(data[1]);
				cinemaCode.add(data[2]);
				movieID.add(data[3]);
				time.add(data[4]);
				day.add(data[5]);
				while ((seatsLine = seatsBr.readLine()) != null) {
					String[] seatsData;
					seatsData = seatsLine.split(csvSplitBy);
					seatType.add(seatsData[1]);
					ticketID.add(seatsData[2]);
					age.add(seatsData[3]);
					column.add(seatsData[4]);
					row.add(seatsData[5]);
					status.add(seatsData[6]);
				}
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
					seatsBr.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		for (int i = 0; i < date.size(); i++) {
			for (int j = 0; j < ticketID.size(); j++) {
				Tickets t = new Tickets(Integer.valueOf(ticketID.get(j)), age.get(j));
				Seat s = new Seat(row.get(j), Integer.valueOf(column.get(j)), seatType.get(j), Boolean.valueOf(status.get(j)), t);
				seats.add(s);
			}
			ShowTime st = new ShowTime(Integer.valueOf(showTimeID.get(i)), time.get(i), day.get(i), date.get(i), Integer.valueOf(movieID.get(i)), seats, cinemaCode.get(i));
			this.showTimeList.add(st);
		}
	};
	
	protected void saveDataToCSV(String to) {
		try {
			FileWriter showTimeFile = new FileWriter(to);
			FileWriter seatsFile = new FileWriter("src/storage/Seats.csv");
			for (ShowTime st: showTimeList) {
				showTimeFile.append(st.getDate() + "," + st.getShowTimeID() + "," + st.getCinemaCode() + "," + st.getMovieID() + "," + st.getTime() + "," + st.getDay() + "\n");
				for (Seat s: seats) {
					seatsFile.append(st.getShowTimeID() + "," + s.getSeatType() + "," + s.getTicket().getTicketID() + "," + s.getTicket().getAge() + "," + s.getColumn() + "," + s.getRow() + "," + s.getStatus() + "\n");
				}
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	};
}
