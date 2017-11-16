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
        Scanner sc = new Scanner(System.in);

        int choice = 0;
        do {
            System.out.println("====================== CUSTOMER =======================");
            System.out.println("|1. List of Movies                                    |");
            System.out.println("|2. Search Movie                                      |");
            System.out.println("|3. Book Ticket(s)                                    |");
            System.out.println("|4. View Booking History                              |");
            System.out.println("|5. Rate and Review                                   |");
            System.out.println("|6. List Top 5 ranking by ticket sales                |");
            System.out.println("|7. List Top 5 movies by overall reviewer's ratings   |");
            System.out.println("|8. Back                                              |");
            System.out.println("|9. Quit                                              |");
            System.out.println("=======================================================");
            System.out.print("Please input your choice: ");

            choice = sc.nextInt();
            System.out.print("\n");

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
                    MoblimaApp.main(null);
                    break;
                case 9:
                    sc.close();
                    System.out.println("Program Terminating...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Incorrect choice, please choose (1-9) only!");
                    break;
            }

        } while (choice != 8);

    }

}
