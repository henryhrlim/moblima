package objects;
public class Transaction {
	private String TID;
	private String name;
	private int mobileNum;
	private String email;
	private double amount;
	private int movieID;
	private int showTimeID;
	private List<Tickets> ticketList;
	
	public Transaction(){
		
	}
	
	public Transaction(String cinemaCode){
		
	}
	
	public Transaction(String TID, String name, int mobileNum, String email, double amount, int movieID, int showTimeID, List<Tickets> ticketList){
		this.TID = TID;
		this.name = name;
		this. mobileNum = mobileNum;
		this.email = email;
		this.amount = amount;
		this.movieID = movieID;
		this.showTimeID = showTimeID;
		this.ticketList = ticketList;
	}
	
	public String getTID(){
		return TID;
	}
	
	public void setTID(String TID){
		this.TID = TID;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public int getMobileNum(){
		return mobileNum;
	}
	
	public void setMobileNum(int mobileNum){
		this.mobileNum = mobileNum;
	}
	
	public String getEmail(){
		return email;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public double getAmount(){
		return amount;
	}
	
	public void setAmount(double amount){
		this.amount = amount;
	}
	
	public int getMovieID(){
		return movieID;
	}
	
	public void setMovieID(int movieID){
		this.movieID = movieID;
	}
	
	public int getShowTimeID(){
		return showTimeID;
	}
	
	public void setShowTimeID(int showTimeID){
		this.showTimeID = showTimeID;
	}
	
	public List<Tickets> getTicketList(){
		return ticketList;
	}
	
	public void setTicketList(List<Tickets> ticketList){
		this.ticketList = ticketList;
	}

}
