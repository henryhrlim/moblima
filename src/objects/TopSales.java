package objects;
public class TopSales {
	private int movieID;
	private String title;
	private double totalAmount;
	
	public TopSales() {
		
	}
	public TopSales(int movieID, double totalAmount, String title) {
		this.movieID = movieID;
		this.totalAmount = totalAmount;
		this.title = title;
		System.out.println("the movie: " + title + " Grossed a total of: " + totalAmount);
	}
	public int getMovieID() {
		return movieID;
	}
	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
}
