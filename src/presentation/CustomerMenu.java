package presentation;

import java.util.Scanner;

import controller.CineplexController;
import controller.MovieController;
import controller.TransactionController;
import entity.Movie;


public class CustomerMenu {

	

	public void show() {
		MovieController movieController = new MovieController();
		CineplexController cineplexController = new CineplexController();
		TransactionController transController = new TransactionController();
		MoblimaApp app = new MoblimaApp();
		
		Movie movUserChoice = null;
		int choice = 0;
		
		
		do{
			choice = CustomerMenu();
			
			switch(choice) {

					/**
					 * list all Movies
					 * user can select to view details of a particular movie inside this 
					 * */
				case 1://DONE
					movieController.listAllMovies();
					break;
		
					/**Search Movie*/
				case 2://DONE
					movieController.searchMovie();
					break;
		
					/**book tickets**/	
				case 3:
					cineplexController.booking();
					break;
		
					/**view booking history*/
				case 4://WEIJUN TO DO??????????????????????????????????
					transController.viewHistory();
					break;
				
					/**Customer Review*/
				case 5://MAYBELELE GOT A COOL REVIEW
					movieController.getRatingsFromUser();
					break;
				
					/**list top 5 ranking by ticket sales*/
				case 6:
					transController.getTop5Sales(false);
					break;
		
				
					/**list top 5 movies by overall reviewer's ratings*/
				case 7:
					transController.getTop5Rating(false);
					break;
		
				
					/**return to main menu*/
				case 8:
					app.main(null);
					break;
				
					/**Quit the application*/
				case 9:
					System.out.println("Program Terminating...");
					System.exit(0);
					break;
					
				default:
					System.out.println("Incorrect choice, please choose (1-8) only!");
					choice = CustomerMenu();
					break;
			}
			
		} while (choice != 8);
		
		System.out.println("Program Terminating...");
		System.exit(0);


	}
	
	public int CustomerMenu() {
		int choice = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("^^^^^^^^^^^^^CUSTOMER MENU^^^^^^^^^^^");
		System.out.println("|1. List of Movies"); //allow to view details inside this option
		System.out.println("|2. Search Movie"); //not done
		//is checking seat another menu
		System.out.println("|3. Book Ticket(s)"); //almost done
		System.out.println("|4. View Booking History"); //done
		System.out.println("|5. Create Ratings/Review"); //not done
		System.out.println("|6. List Top 5 ranking by ticket sales"); //not done
		System.out.println("|7. List Top 5 movies by overall reviewer's ratings"); //not done
		System.out.println("|8. Go Back");
		System.out.println("|9. Quit");

		System.out.println("Choice (1-8): ");
		choice = sc.nextInt();
		
		return choice;
	}


}
