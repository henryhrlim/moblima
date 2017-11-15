package app;

import controller.CineplexController;
import controller.MovieController;
import controller.TransactionController;
import entity.Movie;

import java.util.Scanner;


public class CustomerMenu {


    public void show() {
        MovieController movieController = new MovieController();
        CineplexController cineplexController = new CineplexController();
        TransactionController transController = new TransactionController();
        MoblimaApp app = new MoblimaApp();

        Movie movUserChoice = null;
        int choice = 0;


        do {
            choice = CustomerMenu();

            switch (choice) {
                case 1:
                    movieController.listAllMovies();
                    break;

                case 2:
                    movieController.searchMovie();
                    break;

                case 3:
                    cineplexController.booking();
                    break;

                case 4:
                    transController.viewHistory();
                    break;

                case 5:
                    movieController.getRatingsFromUser();
                    break;

                case 6:
                    transController.getTop5Sales(false);
                    break;

                case 7:
                    transController.getTop5Rating(false);
                    break;


                case 8:
                    app.main(null);
                    break;


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
        System.out.println("|1. List of Movies");
        System.out.println("|2. Search Movie");

        System.out.println("|3. Book Ticket(s)");
        System.out.println("|4. View Booking History");
        System.out.println("|5. Create Ratings/Review");
        System.out.println("|6. List Top 5 ranking by ticket sales");
        System.out.println("|7. List Top 5 movies by overall reviewer's ratings");
        System.out.println("|8. Go Back");
        System.out.println("|9. Quit");

        System.out.println("Choice (1-8): ");
        choice = sc.nextInt();

        return choice;
    }


}
