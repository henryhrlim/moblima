package handler;

import entity.Tickets;
import entity.Transaction;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * TransactionHandler
 *
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
     *
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
     *
     * @return transactionList.
     */
    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    protected void readCSV(FileReader csvFile) {
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


        for (int i = 0; i < TID.size(); i++) {
            List<Tickets> ticketList = new ArrayList<>();
            String temp = ticketID.get(i).toString().replace("\"", "");
            List<String> temp2 = new ArrayList<>(Arrays.asList(temp.split("-")));
            for (int j = 0; j < temp2.size() - 1; j++) {
                String temp3 = temp2.get(j);
                Tickets t = new Tickets();
                temp3 = temp3.replace("[", "");
                temp3 = temp3.replace("]", "");
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
                file.append("\"," + t.getTID() + "," + t.getEmail() + "\n");
            }
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ;
}
