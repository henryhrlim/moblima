package entity;


/**
 * Seat
 * @version 1.0
 * @since 10/10/2015
*/

public class Seat {
	private String row;
	private int column;
	private String seatType;
	private boolean status;
	private Tickets ticket;
	
	/**
	 * Class Constructor
	 */
	public Seat() {
	}

	
	/**
	 * Class Constructor
	 * @param row To pass a string of seat row
	 * @param column To pass a integer of seat column
	 * @param seatType To pass a string of seatType
	 * @param status To set the status of seat in boolean
	 * @param ticket To pass in a object of Tickets 
	 */
	public Seat(String row, int column, String seatType, boolean status,
			Tickets ticket) {
		this.row = row;
		this.column = column;
		this.seatType = seatType;
		this.status = status;
		this.ticket = ticket;
	}

	/**
	 * Gets the row of seat
	 * @return row.
	 */
	public String getRow() {
		return row;
	}

	/**
	 * Set the row of seat
	 * @param row To pass string of row
	 * 
	 */
	public void setRow(String row) {
		this.row = row;
	}

	/**
	 * Gets the column of seat
	 * @return column.
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * Set the column of seat
	 * @param column To pass integer of column
	 * 
	 */
	public void setColumn(int column) {
		this.column = column;
	}

	/**
	 * Gets the Seat Type for the movie
	 * @return seatType.
	 */
	public String getSeatType() {
		return seatType;
	}

	/**
	 * Set the Seat Type
	 * @param seatType To pass string of seatType
	 *
	 */
	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}

	/**
	 * Gets the seat's status (true = occupied, false = non-occupied)
	 * @return status.
	 */
	public boolean getStatus() {
		return status;
	}

	/**
	 * Set the seat's status (true = occupied, false = non-occupied)
	 * @param status To pass boolean of status
	 * 
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * Gets the object of Ticket
	 * @return status.
	 */
	public Tickets getTicket() {
		return ticket;
	}

	/**
	 * Set the ticket object 
	 * @param ticket To pass object of ticket
	 * 
	 */
	public void setTicket(Tickets ticket) {
		this.ticket = ticket;
	}
	
	
}
