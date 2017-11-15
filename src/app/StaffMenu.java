package app;

import controller.StaffController;
import controller.TransactionController;

import java.util.Scanner;


public class StaffMenu {

    public void show() {
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
                System.out.println("Programm terminating...");
                System.exit(0);
                break;

            default:
                break;
        }


    }

}
