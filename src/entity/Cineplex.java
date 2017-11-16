package entity;

import java.util.ArrayList;
import java.util.List;


public class Cineplex {
    private int cineplexID;
    private String location;
    private String cineplexName;
    private List<Cinema> cinemas;
    private List<Movie> movie;
    private List<ShowTime> showTime;


    public Cineplex() {
    }


    public Cineplex(int cineplexID, String location, String cineplexName, List<Cinema> cinemas) {
        this.cineplexID = cineplexID;
        this.location = location;
        this.cineplexName = cineplexName;
        if (cinemas == null) {
            this.cinemas = new ArrayList<Cinema>();
            String code = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            this.cinemas.add(new Cinema("normal", code.charAt(cineplexID - 1) + "01"));
            this.cinemas.add(new Cinema("normal", code.charAt(cineplexID - 1) + "02"));
            this.cinemas.add(new Cinema("normal", code.charAt(cineplexID - 1) + "03"));
        } else {
            this.cinemas = cinemas;
        }

    }


    public int getCineplexID() {
        return cineplexID;
    }


    public void setCineplexID(int cineplexID) {
        this.cineplexID = cineplexID;
    }


    public String getLocation() {
        return location;
    }


    public void setLocation(String location) {
        this.location = location;
    }


    public String getCineplexName() {
        return cineplexName;
    }


    public void setCineplexName(String cineplexName) {
        this.cineplexName = cineplexName;
    }


    public List<Cinema> getCinemas() {
        return cinemas;
    }


    public void setCinemas(List<Cinema> cinemas) {
        this.cinemas = cinemas;
    }


    public List<Movie> getMovie() {
        return movie;
    }


    public void setMovie(List<Movie> movie) {
        this.movie = movie;
    }


    public List<ShowTime> getShowTime() {
        return showTime;
    }


    public void setShowTime(List<ShowTime> showTime) {
        this.showTime = showTime;
    }

    public static class Cinema {
        private String cinemaCode;
        private String cinemaType;


        public Cinema() {

        }


        public Cinema(String cinemaType, String cinemaCode) {
            this.cinemaType = cinemaType;
            this.cinemaCode = cinemaCode;
        }


        public String getCinemaCode() {
            return cinemaCode;
        }


        public void setCinemaCode(String cinemaCode) {
            this.cinemaCode = cinemaCode;
        }


        public String getCinemaType() {
            return cinemaType;
        }


        public void setCinemaType(String cinemaType) {
            this.cinemaType = cinemaType;
        }


    }

    public static class Seat {
        private String row;
        private int column;
        private String seatType;
        private boolean status;
        private Transaction.Tickets ticket;


        public Seat() {
        }


        public Seat(String row, int column, String seatType, boolean status,
                    Transaction.Tickets ticket) {
            this.row = row;
            this.column = column;
            this.seatType = seatType;
            this.status = status;
            this.ticket = ticket;
        }


        public String getRow() {
            return row;
        }


        public void setRow(String row) {
            this.row = row;
        }


        public int getColumn() {
            return column;
        }


        public void setColumn(int column) {
            this.column = column;
        }


        public String getSeatType() {
            return seatType;
        }


        public void setSeatType(String seatType) {
            this.seatType = seatType;
        }


        public boolean getStatus() {
            return status;
        }


        public void setStatus(boolean status) {
            this.status = status;
        }


        public Transaction.Tickets getTicket() {
            return ticket;
        }


        public void setTicket(Transaction.Tickets ticket) {
            this.ticket = ticket;
        }


    }
}
