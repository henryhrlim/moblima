package controller;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import dataAccess.TransactionHandler;
import entity.Cinema;
import entity.Cineplex;
import entity.Movie;
import entity.Review;
import entity.Seat;
import entity.ShowTime;
import entity.Tickets;
import entity.TopSales;
import entity.Transaction;
import presentation.CustomerMenu;
import presentation.StaffMenu;


/*
 * Class: TransactionController
 * 
 * Class Methods:
 * - addToTransaction(Transaction t): boolean
 * - checkDuplicate(Transaction t): boolean
 * - retrieveTransactionList() : List<Transaction> 
 * - formatString(String str) : String[] 
 * - viewTransactionHistory(String input_name,int input_mobile) : void
 * - getTop5Rating() : void
 * - getTop5Sales() : void
 * - viewHistory(): void
 * - enterCustomerInfo(): Object[] 
 * 
 *
 */
public class TransactionController {
	private CustomerMenu c_menu = new CustomerMenu();
	private StaffMenu s_menu = new StaffMenu();
	
	public boolean addToTransaction(Transaction t){
		TransactionHandler handler = new TransactionHandler();	
		if(checkDuplicate(t)){		
			return false;	
		}else{
			handler.create(t);		
		}
		return true;
	}
	
	
	public boolean checkDuplicate(Transaction t){
		TransactionHandler handler = new TransactionHandler();
		List<Transaction> tList = new ArrayList<Transaction>();
		tList = handler.getTransactionList();
		if(retrieveTransactionList()!=null){
			tList = retrieveTransactionList();
			for(Transaction trans : tList){
				if(t.getTID().equals(trans.getTID())){
					System.out.println(t.getTID() + "!!!");
					System.out.println("Duplicate transaction");
					return true;
				}
			}
		}
		return false;
	}
	
				
	public List<Transaction> retrieveTransactionList(){
		TransactionHandler handler = new TransactionHandler();
		handler.retrieve();
		List<Transaction> tList =  handler.getTransactionList();
		if(tList == null)
			tList = new ArrayList<Transaction>();
		return tList;
	}
	
	
	/******************************************************************************************************************
	 * 												Relevant Methods							                      *
	 ******************************************************************************************************************/	

	// To format the TID code to date format // iff cinemaCode.length = 3
	public String[] formatString(String str){
		String[] strArr = new String[2];
	
		String year = str.substring(3,7);
		String month =str.substring(7,9);
		String day =str.substring(9,11);
		
		String dateF = day +"/" + month + "/" + year;
		strArr[0] = dateF;
		
		String hour = str.substring(11,13);
		String min  = str.substring(13,15);
		
		String timeF = hour +":" + min ;
		strArr[1] = timeF;
		
		return strArr;
		
	}
		
	public void viewTransactionHistory(String input_name,int input_mobile){
		double totalAmt = 0.0;
		List<Cineplex> cineplexList = new ArrayList<Cineplex>();
		List<Transaction> transList = new ArrayList<Transaction>();
		List<Movie> movieList = new ArrayList<Movie>();
		List<ShowTime> showList = new ArrayList<ShowTime>();
		
		CineplexController cineplexController = new CineplexController();
		MovieController movieController = new MovieController();
		ShowTimeController stController = new ShowTimeController();
		
		cineplexList = cineplexController.retrieveCineplexList();
		movieList = movieController.retrieveMovieList();
		showList = stController.retrieveShowTimeList();

		
		
		if(retrieveTransactionList()!=null){
			transList = retrieveTransactionList();
			System.out.println("============= \t Transaction History \t =============" );
			for(Transaction tList: transList){
				// Unique information to identify each user's purchase
				if(input_name.equals(tList.getName()) && input_mobile == tList.getMobileNum()){
					String movieName = null;
					String cineplexName = null;
					String cinemaCode = tList.getTID().substring(0, 3);
					List<Tickets> tixList = tList.getTicketList();
					List<Seat> seatList = null;
					
					for(Cineplex cineplex: cineplexList){
						
						List<Cinema> cima =cineplex.getCinemas();
						List<ShowTime> showTimeList = cineplex.getShowTime();				
						for(Cinema c: cima){
							if(c.getCinemaCode().equals(cinemaCode)){
								cineplexName = cineplex.getCineplexName();
								for(ShowTime st : showTimeList){
									if(st.getShowTimeID() == tList.getShowTimeID()){
										for(Movie m : movieList){
											if(m.getMovieID() == tList.getMovieID()){
												movieName = m.getTitle();
												cineplexName = cineplex.getCineplexName();				
												break;
											}
										
										}
									}
								}
							}
						}
					}
					
					System.out.println("Transaction ID: " + tList.getTID());
					String datePurchase[] = formatString(tList.getTID());
					System.out.println("Movie :" + movieName);
					System.out.println("Cineplex :"+ cineplexName);
					System.out.println("Cinema :" + cinemaCode);
					
					System.out.println("Date : " + datePurchase[0] + " Time: " + datePurchase[1]);
					
					for(Tickets tic : tixList){
						for(ShowTime st: showList){
								seatList = st.getSeats();
								for(Seat s: seatList){
									Tickets ticket = new Tickets();
									ticket = s.getTicket();
									
									if(ticket != null){
										// there is duplication is TicketID???????????????????????????????????
										if(ticket.getTicketID() == tic.getTicketID()){

											System.out.println("--------------------------------------------------------");
											System.out.println("Ticket ID : " + tic.getTicketID());
											System.out.println("Ticket Type : " + ticket.getAge());
											System.out.println("Seat :" + s.getRow()+s.getColumn());
											System.out.println("Seat Type :" + s.getSeatType());
								
										}
										
										
										
									}
									else{
										System.out.println("NULL");
									}
										
								}
								
						}
					}
				
					totalAmt += tList.getAmount();
					System.out.println("^^^^^^^^^^^^^^^^^^^^^^");
					System.out.println("Price : $" + String.format( "%.2f", tList.getAmount()));
					System.out.println("^^^^^^^^^^^^^^^^^^^^^^");
					System.out.println("\n");
				
					
				}
			}
			System.out.println("Total amount: $" + String.format( "%.2f", totalAmt));
		}
	}
	
	
	
	/******************************************************************************************************************
	 * 												Getting Top Rating 					    		                  *
	 ******************************************************************************************************************/	

		// movieID 1-7 sequential order
		public void getTop5Rating(boolean comingFromStaff){
			MovieController controller = new MovieController();
			List<Movie> movieList = controller.retrieveMovieList();
			
		
			
			Collections.sort(movieList, new Comparator<Movie>(){

				@Override
			    public int compare(Movie m1, Movie m2) {
			        return Double.compare(m1.getRatings(), m2.getRatings());
			    }
				
			});
			Collections.reverse(movieList);
			if(movieList.size()<5){
				System.out.println("==========================================");
				System.out.println("Movie listings are less than 5.");
				System.out.println("Current Movie Rating : ");
				System.out.println("==========================================");
				System.out.println("");
				for(int i=0; i<movieList.size();i++){
					String heading1 = (i+1)+". "+movieList.get(i).getTitle();
					String heading2 = "||  Rating: " +movieList.get(i).getRatings(); ;
					System.out.println(heading1);
					System.out.println(heading2);
					System.out.println("");
				}
			}else{
				for(int i=0; i<5;i++){
					String heading1 = (i+1)+". "+movieList.get(i).getTitle();
					String heading2 = "||  Rating: "+movieList.get(i).getRatings();
					System.out.println(heading1);
					System.out.println(heading2);
					System.out.println("");
				}
			}
			System.out.println("");
			
			if(comingFromStaff){
				s_menu.show();
			}else{
				c_menu.show();
			}
		}
		

	
		
		/******************************************************************************************************************
		 * 												Getting Top Overall Sales 					    		          *
		 ******************************************************************************************************************/	

		public void getTop5Sales(boolean comingFromStaff){
			TransactionController controller = new TransactionController();
			List<Transaction> transList = controller.retrieveTransactionList();
			
			List<TopSales> tsList = new ArrayList<TopSales>();
			
			MovieController movie = new MovieController();
			List<Movie> movieList = movie.retrieveMovieList();
			
			for(Movie m : movieList){
				TopSales ts = new TopSales(m.getMovieID(),0,m.getTitle());
				tsList.add(ts);
			}
			
			
			
			for(Transaction sc : transList){
				for(int i = 0; i < tsList.size(); i++){
					TopSales ts = tsList.get(i);
					double totalAmount = ts.getTotalAmount();
					if(ts.getMovieID() == sc.getMovieID()){
						totalAmount += sc.getAmount();
						ts.setTotalAmount(totalAmount);
					}
					tsList.set(i, ts);
				}
			}
			
			Collections.sort(tsList, new Comparator<TopSales>(){

				@Override
			    public int compare(TopSales ts1, TopSales ts2) {
			        return Double.compare(ts1.getTotalAmount(),ts2.getTotalAmount());
			    }
				
			});
			// Need to reverse the sorting list ?
			Collections.reverse(tsList);
			int size = tsList.size();
			if(size > 5){
				size = 5;
			}
			if(tsList.size()<5){
				System.out.println("==========================================");
				System.out.println("Movie listings are less than 5.");
				System.out.println("Current Sales : ");
				System.out.println("==========================================");
				System.out.println("");
				for(int i=0; i<tsList.size();i++){
					TopSales ts = tsList.get(i);
					String heading1 = (i+1)+ ". " +ts.getTitle();
					String heading2 = "||  $"+ts.getTotalAmount();
					System.out.println(heading1);
					System.out.println(heading2);
					System.out.println("");
				}
			}else{
				for(int i=0; i<size;i++){
					TopSales ts = tsList.get(i);
					String heading1 =  (i+1)+ ". " +ts.getTitle();
					String heading2 = "||  $"+ts.getTotalAmount();
					System.out.println(heading1);
					System.out.println(heading2);
					System.out.println("");
				}
			}
			System.out.println("");
			
			if(comingFromStaff){
				s_menu.show();
			}else{
				c_menu.show();
			}
		}
		


		/******************************************************************************************************************
		 * 												VIEW BOOKING HISTORY 					    		              *
		 ******************************************************************************************************************/	
				
		public void viewHistory(){
			Object[] custInfo;
			custInfo = new Object[2];
			custInfo = enterCustomerInfo();
			String name = (String)custInfo[0];
			int mobileNo = (int)custInfo[1];
			
			TransactionController transController = new TransactionController();
			transController.viewTransactionHistory(name, mobileNo);
			
			c_menu.show();
		}
		
		public Object[] enterCustomerInfo(){
			Scanner sc = new Scanner(System.in);
			Object[] custInfo;
			
			custInfo = new Object[2];
			
			System.out.println("************CUSTOMER INFO************");
			System.out.println("Enter Name: ");
			String name = sc.next();
			custInfo[0] = name;
			
			sc = new Scanner(System.in);
			System.out.println("Enter Mobile Number: ");
			int mobileNo = sc.nextInt();
			custInfo[1] = mobileNo;
			
			return custInfo;
		}
		
		
	
	/**
	 * 
	 *  End of Class
	 */
	
	
	
}
	
	



