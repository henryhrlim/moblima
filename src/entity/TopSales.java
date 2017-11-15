package entity;


/**
 * TopSales
 * @version 1.0
 * @since 10/10/2015
*/

public class TopSales {
	private int movieID;
	private String title;
	private double totalAmount;
	
	

	/**
	 * Class Constructor
	 *@param movieID To pass in integer of movieID
	 *@param totalAmount To pass in double of totalAmount
	 *@param title To pass in string of title
	 * 
	 */
	public TopSales(int movieID, double totalAmount, String title) {
		this.movieID = movieID;
		this.totalAmount = totalAmount;
		this.title = title;
	}

	/**
	 * Gets the movieID
	 * @return movieID.
	 */
	public int getMovieID() {
		return movieID;
	}
	
	/**
	 * Set the Movie ID
	 * @param movieID To pass integer of movieID
	 * 
	 */
	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}

	/**
	 * Gets the Total Amount of sales
	 * @return totalAmount.
	 */
	public double getTotalAmount() {
		return totalAmount;
	}

	/**
	 * Set the Total Amount
	 * @param totalAmount To pass double of totalAmount
	 * 
	 */
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	/**
	 * Gets the Title of Movie
	 * @return title.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Set the Title of the Movie
	 * @param title To pass string of title
	 * 
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
