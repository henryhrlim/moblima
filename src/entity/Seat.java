package entity;


public class Seat {
    private String row;
    private int column;
    private String seatType;
    private boolean status;
    private Tickets ticket;


    public Seat() {
    }


    public Seat(String row, int column, String seatType, boolean status,
                Tickets ticket) {
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


    public Tickets getTicket() {
        return ticket;
    }


    public void setTicket(Tickets ticket) {
        this.ticket = ticket;
    }


}
