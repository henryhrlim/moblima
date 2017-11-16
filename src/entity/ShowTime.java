package entity;

import java.util.ArrayList;
import java.util.List;


public class ShowTime {
    private static final String row[] = {"A", "B", "C", "D", "E", "F", "G", "H"};
    private static int ticketid;
    private String cinemaCode;
    private int showTimeID;
    private String time;
    private String day;
    private String date;
    private int movieID;
    private List<Cineplex.Seat> seats;


    public ShowTime() {

        seats = new ArrayList<Cineplex.Seat>();
        for (int i = 0; i < row.length; i++) {
            for (int a = 0; a < 6; a++) {
                Transaction.Tickets ticket = new Transaction.Tickets();
                ticketid++;
                ticket.setTicketID(ticketid);
                seats.add(new Cineplex.Seat(row[i], a + 1, "normal", false, ticket));
            }
        }
    }


    public ShowTime(int showTimeID, String time, String day, String date, int movieID,
                    List<Cineplex.Seat> seats, String cinemaCode) {
        this.showTimeID = showTimeID;
        this.time = time;
        this.day = day;
        this.date = date;
        this.movieID = movieID;
        this.cinemaCode = cinemaCode;
        if (seats == null) {
            seats = new ArrayList<Cineplex.Seat>();
            for (int i = 0; i < row.length; i++) {
                for (int a = 0; a < 6; a++) {
                    Transaction.Tickets ticket = new Transaction.Tickets();
                    ticketid++;
                    ticket.setTicketID(ticketid);
                    seats.add(new Cineplex.Seat(row[i], a + 1, "normal", false, ticket));
                }
            }
        } else {
            this.seats = seats;
        }

    }


    public String getCinemaCode() {
        return cinemaCode;
    }


    public void setCinemaCode(String cinemaCode) {
        this.cinemaCode = cinemaCode;
    }


    public int getShowTimeID() {
        return showTimeID;
    }


    public void setShowTimeID(int showTimeID) {
        this.showTimeID = showTimeID;
    }


    public String getTime() {
        return time;
    }


    public void setTime(String time) {
        this.time = time;
    }


    public String getDay() {
        return day;
    }


    public void setDay(String day) {
        this.day = day;
    }


    public int getMovieID() {
        return movieID;
    }


    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }


    public List<Cineplex.Seat> getSeats() {
        return seats;
    }


    public void setSeats(List<Cineplex.Seat> seats) {
        this.seats = seats;
    }


    public String getDate() {
        return date;
    }


    public void setDate(String date) {
        this.date = date;
    }


}
