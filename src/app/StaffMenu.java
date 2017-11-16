package app;

import controller.StaffController;
import controller.TransactionController;

import java.util.Scanner;


public class StaffMenu {

    public void show() {
        TransactionController transController = new TransactionController();
        StaffController staffController = new StaffController();
        Scanner sc = new Scanner(System.in);
        MoblimaApp app = new MoblimaApp();
        
        int choice = 0;
        do {
	        System.out.println("======================== STAFF ========================");
	        System.out.println("|1.  Cineplexes                                       |");
	        System.out.println("|2.  Cinemas                                          |");
	        System.out.println("|3.  Movies                                           |");
	        System.out.println("|4.  Show Times                                       |");
	        System.out.println("|5.  Prices                                           |");
	        System.out.println("|6.  Holidays                                         |");
	        System.out.println("|7.  List Top 5 ranking by ticket sales               |");
	        System.out.println("|8.  List Top 5 movies by overall reviewer's ratings  |");
	        System.out.println("|9.  Back                                             |");
	        System.out.println("|10. Quit                                             |");
	        System.out.println("=======================================================");
            System.out.print("Please input your choice: ");
            
            choice = sc.nextInt();
            System.out.print("\n");
            
	        switch (choice) {
	            case 1:
	                staffController.staffMenuCineplex();
	                break;
	            case 2:
	                staffController.staffMenuCinema();
	                break;
	            case 3:
	                staffController.staffMenuMovie();
	                break;
	            case 4:
	                staffController.showtimeMain();
	                break;
	            case 5:
	                staffController.pricechartMain();
	                break;
	            case 6:
	                staffController.holidayMain();
	                break;
	            case 7:
	                transController.getTop5Sales(true);
	                break;
	            case 8:
	                transController.getTop5Rating(true);
	                break;
	            case 9:
	                app.main(null);
	                break;
	            case 10:
	            		sc.close();
	                System.out.println("Programm terminating...");
	                System.exit(0);
	                break;
	            default:
	                break;
	        }
	        
        } while (choice != 9);

    }

}
