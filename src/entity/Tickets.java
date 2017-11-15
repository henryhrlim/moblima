package entity;


/**
 * Tickets
 * @version 1.0
 * @since 10/10/2015
*/

public class Tickets {
	private int ticketID;
	private String age;

	/**
	 * Class Constructor
	 */
	public Tickets() {

	}

	/**
	 * Class Constructor
	 * @param ticketID To pass integer of ticketID
	 * @param age To pass string of age
	 */
	public Tickets(int ticketID, String age) {
		this.ticketID = ticketID;
		this.age = age;
	}

	/**
	 * Gets the ticketID
	 * @return ticketID.
	 */
	public int getTicketID() {
		return ticketID;
	}

	/**
	 * Set the ticketID
	 * @param ticketID To pass integer of ticketID
	 * 
	 */
	public void setTicketID(int ticketID) {
		this.ticketID = ticketID;
	}

	/**
	 * Gets the age
	 * @return age.
	 */
	public String getAge() {
		return age;
	}

	/**
	 * Set the age
	 * @param age To pass string of age
	 * 
	 */
	public void setAge(String age) {
		this.age = age;
	}

}
