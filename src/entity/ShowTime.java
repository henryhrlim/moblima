package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * ShowTime
 *
 * @version 1.0
 * @since 10/10/2015
 */

public class ShowTime {
    private static final String row[] = {"A", "B", "C", "D", "E", "F", "G", "H"};
    private static int ticketid;
    private String cinemaCode;
    private int showTimeID;
    private String time;
    private String day;
    private String date;
    private int movieID;
    private List<Seat> seats;


    /**
     * Class Constructor
     */
    public ShowTime() {

        seats = new ArrayList<Seat>();
        for (int i = 0; i < row.length; i++) {
            for (int a = 0; a < 6; a++) {
                Tickets ticket = new Tickets();
                ticketid++;
                ticket.setTicketID(ticketid);
                seats.add(new Seat(row[i], a + 1, "normal", false, ticket));
            }
        }
    }

    /**
     * Class Constructor
     *
     * @param showTimeID integer of showTimeID
     * @param time       string of time
     * @param day        string of day
     * @param date       string of date
     * @param movieID    integer of movieID
     * @param seats      list of seats
     * @param cinemaCode string of cinemaCode
     */
    public ShowTime(int showTimeID, String time, String day, String date, int movieID,
                    List<Seat> seats, String cinemaCode) {
        this.showTimeID = showTimeID;
        this.time = time;
        this.day = day;
        this.date = date;
        this.movieID = movieID;
        this.cinemaCode = cinemaCode;
        if (seats == null) {
            seats = new ArrayList<Seat>();
            for (int i = 0; i < row.length; i++) {
                for (int a = 0; a < 6; a++) {
                    Tickets ticket = new Tickets();
                    ticketid++;
                    ticket.setTicketID(ticketid);
                    seats.add(new Seat(row[i], a + 1, "normal", false, ticket));
                }
            }
        } else {
            this.seats = seats;
        }

    }


    /**
     * Gets the Cinema Code
     *
     * @return cinemaCode.
     */
    public String getCinemaCode() {
        return cinemaCode;
    }

    /**
     * Set the Cinema Code
     *
     * @param cinemaCode To pass string of cinemaCode
     */
    public void setCinemaCode(String cinemaCode) {
        this.cinemaCode = cinemaCode;
    }

    /**
     * Gets the Show TimeID
     *
     * @return showTimeID.
     */
    public int getShowTimeID() {
        return showTimeID;
    }

    /**
     * Set the showTimeID
     *
     * @param showTimeID To pass string of showTimeID
     */
    public void setShowTimeID(int showTimeID) {
        this.showTimeID = showTimeID;
    }

    /**
     * Gets the time of Show Time
     *
     * @return time.
     */
    public String getTime() {
        return time;
    }

    /**
     * Set the time of Show Time
     *
     * @param time To pass string of time
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Gets the day of Show Time
     *
     * @return day.
     */
    public String getDay() {
        return day;
    }

    /**
     * Set the day of Show Time
     *
     * @param day To pass string of day
     */
    public void setDay(String day) {
        this.day = day;
    }

    /**
     * Gets the movieID of Show Time
     *
     * @return movieID.
     */
    public int getMovieID() {
        return movieID;
    }

    /**
     * Set the movieID
     *
     * @param movieID To pass integer of movieID
     */
    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    /**
     * Gets the list of seats
     *
     * @return seats.
     */
    public List<Seat> getSeats() {
        return seats;
    }

    /**
     * Set the list of seats
     *
     * @param seats To pass list of seats
     */
    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    /**
     * Gets the Date of Show Time
     *
     * @return date.
     */
    public String getDate() {
        return date;
    }

    /**
     * Set the date of Show Time
     *
     * @param date To pass string of date
     */
    public void setDate(String date) {
        this.date = date;
    }


}
