package entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class Transaction {
    private String TID;
    private String name;
    private int mobileNum;
    private String email;
    private double amount;
    private int movieID;
    private int showTimeID;
    private List<Tickets> ticketList;


    public Transaction() {
    }


    public Transaction(String cinemaCode) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmss");
        String formattedDate = sdf.format(date);
        this.TID = cinemaCode + formattedDate;
    }


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


    public int getShowTimeID() {
        return showTimeID;
    }


    public void setShowTimeID(int showTimeID) {
        this.showTimeID = showTimeID;
    }


    public int getMovieID() {
        return movieID;
    }


    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }


    public String getTID() {
        return TID;
    }


    public void setTID(String TID) {
        this.TID = TID;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public int getMobileNum() {
        return mobileNum;
    }


    public void setMobileNum(int mobileNum) {
        this.mobileNum = mobileNum;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public List<Tickets> getTicketList() {
        return ticketList;
    }


    private void setTicketList(List<Tickets> ticketList) {
        this.ticketList = ticketList;
    }


    public double getAmount() {
        return amount;
    }


    public void setAmount(double amount) {
        this.amount = amount;
    }


}
