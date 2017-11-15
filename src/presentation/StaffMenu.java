package presentation;

import java.util.Scanner;

import controller.StaffController;
import controller.TransactionController;


public class StaffMenu { 
	
	public void show(){
		TransactionController transController = new TransactionController();
		StaffController staffController = new StaffController();
		
		int choice = 0;
		Scanner sc = new Scanner(System.in);
		MoblimaApp app = new MoblimaApp();
		System.out.println("^^^^^^^^^^^^^^STAFF MENU^^^^^^^^^^^^^");
		System.out.println("Choose the option to create/update:");
		System.out.println("|1. Cineplex");
		System.out.println("|2. Cinema");
		System.out.println("|3. Movie");
		System.out.println("|4. Show Time");
		System.out.println("|5. Price");
		System.out.println("|6. Holiday");
		System.out.println("|7. List Top 5 ranking by ticket sales");
		System.out.println("|8. List Top 5 movies by overall reviewer's ratings");
		System.out.println("|9. Go Back to Main Menu");
		System.out.println("|10. Quit");

		System.out.println("Choice (1-10): ");

		choice = sc.nextInt();


		switch(choice){
				/**CRUD for Cineplex*/
			case 1:
				staffController.staffMenuCineplex();
				break;
	
				/**CRUD for Cinema*/
			case 2:
				staffController.staffMenuCinema();
				break;
	
				/**CRUD for Movie*/
			case 3:
				staffController.staffMenuMovie();
				
				break;
				
				/**CRUD for ShowTime*/
			case 4:
				staffController.showtimeMain();
				break;
	
				/**CRUD for Price*/
			case 5:
				staffController.pricechartMain();
				break;
	
				/**CRUD for Holiday*/
			case 6:
				staffController.holidayMain();
				break;
	
				/**List Top 5 ranking by ticket sales*/
			case 7:
				transController.getTop5Sales(true);
				break;
	
				/**List Top 5 movies by overall reviewer's ratings*/
			case 8:
				transController.getTop5Rating(true);
				break;
				
			case 9:
				app.main(null);
				break;
	
			case 10:
				System.out.println("Programm terminating...");
				System.exit(0);
				break;
	
			default:
				break;
		}


	}

}
