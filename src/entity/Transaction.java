package entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Transaction
 *
 * @version 1.0
 * @since 10/10/2015
 */

public class Transaction {
    private String TID;
    private String name;
    private int mobileNum;
    private String email;
    private double amount;
    private int movieID;
    private int showTimeID;
    private List<Tickets> ticketList;

    /**
     * Class Constructor
     */
    public Transaction() {
    }


    /**
     * Class Constructor
     *
     * @param cinemaCode To pass in string of cinemaCode
     */
    public Transaction(String cinemaCode) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmss");
        String formattedDate = sdf.format(date);
        this.TID = cinemaCode + formattedDate;
    }


    /**
     * Class Constructor
     *
     * @param TID        To pass in string of TID
     * @param name       To pass in string of name
     * @param mobileNum  To pass in integer of mobileNum
     * @param email      To pass in string of email
     * @param amount     To pass in double of amount
     * @param ticketList To pass in a list of ticketList
     * @param MovieID    To pass in integer of MovieID
     * @param showTimeID To pass in integer of showTimeID
     */
    public Transaction(String TID, String name, int mobileNum, String email, double amount, List<Tickets> ticketList, int MovieID, int showTimeID) {
        this.TID = TID;
        this.movieID = MovieID;
        this.showTimeID = showTimeID;
        this.name = name;
        this.mobileNum = mobileNum;
        this.email = email;
        this.amount = amount;
        this.setTicketList(ticketList);
    }


    /**
     * Class Constructor
     *
     * @param cinemaCode To pass in string of cinemaCode
     * @param name       To pass in string of name
     * @param mobileNum  To pass in integer of mobileNum
     * @param email      To pass in string of email
     * @param amount     To pass in double of amount
     * @param ticketList To pass in a list of ticketList
     * @param movieID    To pass in integer of MovieID
     * @param showTimeID To pass in integer of showTimeID
     */
    public Transaction(String cinemaCode, String name, int mobileNum, String email, double amount,
                       int movieID, int showTimeID, List<Tickets> ticketList) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmss");
        String formattedDate = sdf.format(date);
        this.TID = cinemaCode + formattedDate;
        this.name = name;
        this.mobileNum = mobileNum;
        this.email = email;
        this.amount = amount;
        this.movieID = movieID;
        this.showTimeID = showTimeID;
        this.ticketList = ticketList;
    }

    /**
     * Gets the Show Time ID
     *
     * @return showTimeID.
     */
    public int getShowTimeID() {
        return showTimeID;
    }

    /**
     * Set the Show Time ID
     *
     * @param showTimeID To pass integer of showTimeID
     */
    public void setShowTimeID(int showTimeID) {
        this.showTimeID = showTimeID;
    }

    /**
     * Gets the movieID
     *
     * @return movieID.
     */
    public int getMovieID() {
        return movieID;
    }

    /**
     * Set the Movie ID
     *
     * @param movieID To pass integer of movieID
     */
    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    /**
     * Gets the Transaction ID
     *
     * @return TID.
     */
    public String getTID() {
        return TID;
    }

    /**
     * Set the Transaction ID
     *
     * @param TID To pass string of TID
     */
    public void setTID(String TID) {
        this.TID = TID;
    }

    /**
     * Gets the name
     *
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name
     *
     * @param name To pass string of name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the Mobile Number
     *
     * @return mobileNum.
     */
    public int getMobileNum() {
        return mobileNum;
    }

    /**
     * Set the Mobile Number
     *
     * @param mobileNum To pass integer of mobileNum
     */
    public void setMobileNum(int mobileNum) {
        this.mobileNum = mobileNum;
    }

    /**
     * Gets the Email
     *
     * @return email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the Email
     *
     * @param email To pass string of email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the list of tickets
     *
     * @return ticketList.
     */
    public List<Tickets> getTicketList() {
        return ticketList;
    }

    /**
     * Set the list of Tickets
     *
     * @param ticketList To pass list of ticketList
     */
    public void setTicketList(List<Tickets> ticketList) {
        this.ticketList = ticketList;
    }

    /**
     * Gets the amount of transaction
     *
     * @return amount.
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Set the amount of transaction
     *
     * @param amount To pass a double of amount
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }


}
