package objects;
public class Tickets {
	private int ticketID;
	private String age;
	
	public Tickets(){
		
	}
	
	public Tickets(int ticketID, String age){
		this.ticketID = ticketID;
		this.age = age;
	}
	
	public int getTicketID(){
		return ticketID;
	}
	
	public void setTicketID(int ticketID){
		this.ticketID = ticketID;
	}
	
	public String getAge(){
		return age;
	}
	
	public void setAge(String age){
		this.age = age;
	}

}
