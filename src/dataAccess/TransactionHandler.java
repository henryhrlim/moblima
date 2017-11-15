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
	 * Gets the transactionList
	 * @return transactionList.
	 */
	public List<Transaction> getTransactionList() {
		return transactionList;
	}
	protected void readCSV(FileReader arr){};
	protected void saveDataToCSV(String to) {};
}
