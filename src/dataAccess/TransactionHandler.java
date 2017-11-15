package dataAccess;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import entity.Tickets;
import entity.Transaction;

/**
 * TransactionHandler
 * @version 1.0
 * @since 10/10/2015
*/


public class TransactionHandler extends DataHandler {
	/**
	 * The list of Transactions.
	 */
	private List<Transaction> transactionList;

	/**
	 * Writes a new Transaction into the JSON file.
	 * The Transaction object t's variables all must be set.
	 * @param t This is the object of Transaction.
	 */
	public void create(Transaction t) {
		loadData("Transaction");
		if (this.transactionList == null)
			this.transactionList = new ArrayList<Transaction>();
		if (t != null)
			this.transactionList.add(t);
			System.out.println("Successfully added to transaction");
		saveData("Transaction");
	}

	/**
	 * Call loadData method from parent class.
	 * Retrieve data from JSON file and save in the transactionList.
	 */
	public void retrieve() {
		loadData("Transaction");
	}

	/**
	 * Override readJSON method from parent class(DataHandler).
	 * arr contains the data retrieved from the JSON file.
	 * This method will format the arr JSONArray object and save it into the transactionList.
	 * @param arr This is a JSONArray object.
	 */
	@Override
	protected void readJSON(JSONArray arr) {
		// TODO Auto-generated method stub
		this.transactionList = new ArrayList<Transaction>();
		String TID;
		int MovieID;
		int showTimeID;
		String name;
		int mobileNum;
		String email;
		double amount;
		long tempShow;
		long tempMovie;
		long temp;
		for (int i = 0; i < arr.size(); i++) {
			JSONObject transactionJson = (JSONObject) arr.get(i);

			TID = (String) transactionJson.get("TID");
			name = (String) transactionJson.get("name");
			temp = (long) transactionJson.get("mobileNum");
			mobileNum = (int) temp;
			email = (String) transactionJson.get("email");
			amount = (double) transactionJson.get("amount");
			tempMovie = (long) transactionJson.get("MovieID"); 
			tempShow = (long) transactionJson.get("showTimeID");
			MovieID = (int) tempMovie;
			showTimeID = (int) tempShow;
			
			
			List<Tickets> ticketList = new ArrayList<Tickets>();
			JSONArray ticketArr = (JSONArray) transactionJson.get("ticketID");
			for (int x = 0; x < ticketArr.size(); x++) {
				Tickets t = new Tickets();
				temp = (long) ticketArr.get(x);
				t.setTicketID((int) temp);
				ticketList.add(t);
			}
			Transaction transaction = new Transaction(TID, name, mobileNum,
					email, amount, ticketList,MovieID,showTimeID);
			this.transactionList.add(transaction);

		}

	}

	/**
	 * Override saveDataToJSON method from parent class(DataHandler).
	 * This method will format the transactionList into a JSONArray object and return it.
	 * @return JSONArray object.
	 */
	@Override
	protected JSONArray saveDataToJSON() {
		// TODO Auto-generated method stub
		JSONArray transactionArr = new JSONArray();
		for (Transaction t : this.transactionList) {
			JSONObject transactionJson = new JSONObject();
			List<Tickets> ticketList = t.getTicketList();
			JSONArray ticketArr = new JSONArray();
			for (Tickets ticket : ticketList) {
				ticketArr.add(ticket.getTicketID());
			}
			transactionJson.put("ticketID", ticketArr);

			transactionJson.put("TID", t.getTID());
			transactionJson.put("name", t.getName());
			transactionJson.put("mobileNum", t.getMobileNum());
			transactionJson.put("email", t.getEmail());
			transactionJson.put("amount", t.getAmount());
			transactionJson.put("MovieID", t.getMovieID());
			transactionJson.put("showTimeID", t.getShowTimeID());

			transactionArr.add(transactionJson);
		}
		return transactionArr;
	}

	/**
	 * Gets the transactionList
	 * @return transactionList.
	 */
	public List<Transaction> getTransactionList() {
		return transactionList;
	}

	protected void readCSV(FileReader csvFile){
		this.transactionList = new ArrayList<Transaction>();
		List<String> TID = new ArrayList<>();
		List<Integer> movieID = new ArrayList<>();
		List<Integer> showTimeID = new ArrayList<>();
		List<String> name = new ArrayList<>();
		List<Integer> mobileNum = new ArrayList<>();
		List<String> email = new ArrayList<>();
		List<Double> amount = new ArrayList<>();
		List<List> ticketID = new ArrayList<>();

		BufferedReader br = null;
		String line;
		String cvsSplitBy = ",(?=(?:[^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)";

		try {
			br = new BufferedReader(csvFile);
			String[] data;
			while ((line = br.readLine()) != null) {
				data = line.split(cvsSplitBy);
				showTimeID.add(Integer.valueOf(data[0]));
				mobileNum.add(Integer.valueOf(data[1]));
				amount.add(Double.valueOf(data[2]));
				movieID.add(Integer.valueOf(data[3]));
				name.add(data[4]);
				ticketID.add(Arrays.asList(data[5].split(",")));
				TID.add(data[6]);
				email.add(data[7]);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}


		for(int i = 0; i<TID.size(); i++){
			List<Tickets> ticketList = new ArrayList<>();
			String temp = ticketID.get(i).toString().replace("\"","");
			List<String> temp2 = new ArrayList<>(Arrays.asList(temp.split("-")));
			for(int j = 0; j<temp2.size() - 1; j++){
				String temp3 = temp2.get(j);
				Tickets t = new Tickets();
				temp3 = temp3.replace("[","");
				temp3 = temp3.replace("]","");
				t.setTicketID(Integer.valueOf(temp3));
				ticketList.add(t);
			}
			Transaction transaction = new Transaction(TID.get(i), name.get(i), mobileNum.get(i), email.get(i), amount.get(i), ticketList, movieID.get(i), showTimeID.get(i));
			this.transactionList.add(transaction);
		}
	}
	protected void saveDataToCSV(String to) {
		try (FileWriter file = new FileWriter(to)) {
			for (Transaction t : this.transactionList) {
				file.append(t.getShowTimeID() + "," + t.getMobileNum() + "," + t.getAmount() + "," + t.getMovieID() +
							"," + t.getName() + ",\"");
				List<Tickets> ticketList = t.getTicketList();
				for (Tickets ticket : ticketList) {
					file.append(ticket.getTicketID() + "-");
				}
				file.append("\"," + t.getTID() + "," + t.getEmail());
			}
			file.flush();
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	};
}
