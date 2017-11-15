package entity;

/**
 * Review
 * @version 1.0
 * @since 10/10/2015
*/
public class Review {
	private String feedback;
	private int rating;
	
	/**
	 * Class Constructor
	 */
	public Review(){
		
	}

	/**
	 * Class Constructor
	 * @param feedback To pass in the string of feedback
	 * @param rating To pass in the integer of rating
	 */
	public Review(String feedback, int rating) {
		this.feedback = feedback;
		this.rating = rating;
	}

	/**
	 * Gets the feedback of the movie
	 * @return feedback.
	 */
	public String getFeedback() {
		return feedback;
	}

	/**
	 * Set the feedback
	 * @param feedback To pass string of feedback
	 * 
	 */
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	/**
	 * Gets the rating of the movie
	 * @return rating.
	 */
	public int getRating() {
		return rating;
	}

	/**
	 * Set the rating of movie
	 * @param rating To pass string of rating
	 *
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	
}
