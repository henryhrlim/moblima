package dataAccess;

import java.io.FileReader;
import java.util.ArrayList;
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
	protected void readCSV(FileReader arr){};
	protected void saveDataToCSV(String to) {};
}
