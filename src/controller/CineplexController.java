package controller;

import app.CustomerMenu;
import entity.*;
import handler.CineplexHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class CineplexController {
    private final CustomerMenu c_menu = new CustomerMenu();

    public CineplexController() {

    }

    public List<Cineplex> retrieveCineplexList() {
        CineplexHandler handler = new CineplexHandler();
        handler.retrieve();
        List<Cineplex> cineplexList = handler.getCineplexList();
        if (cineplexList == null) {
            cineplexList = new ArrayList<Cineplex>();
        }

        return cineplexList;
    }


    public void updateCineplex(Cineplex c) {
        CineplexHandler handler = new CineplexHandler();
        handler.update(c);
    }


    public void addCineplexToList(Cineplex c) {
        CineplexHandler handler = new CineplexHandler();
        handler.create(c);
    }


    public void booking() {
        Cineplex cineUserChoice = listCineplex();
        Movie movUserChoice = listMovieSpecific(cineUserChoice);
        Scanner sc = new Scanner(System.in);

        if (movUserChoice != null) {

            ShowTime showTimeUserChoice = listShowTime(cineUserChoice, movUserChoice);

            if (showTimeUserChoice != null) {

                showInfo(cineUserChoice, movUserChoice, showTimeUserChoice);

                System.out.print("Enter the number of tickets to purchase: ");
                int noOfTix = sc.nextInt();
                System.out.print("\n");
                purchaseTicketAndAllocateSeat(noOfTix, movUserChoice, showTimeUserChoice, cineUserChoice.getCinemas());

            } else {
                System.out.println("Selected movie does not have any show time currently.");
            }
        } else {
            System.out.println("Selected cineplex has no movies showing now.");
        }

        c_menu.show();
    }

    public Cineplex listCineplex() {
        Scanner sc = new Scanner(System.in);
        MovieController movieController = new MovieController();
        List<Cineplex> cineplexList = retrieveCineplexList();

        System.out.println("===== List of Cineplex =====");
        System.out.println("ID    Cineplex                  Location");
        for (int i = 0; i < cineplexList.size(); i++) {
            Cineplex c = cineplexList.get(i);
            System.out.format("%-5s %-25s %s\n", (i + 1), c.getCineplexName(), c.getLocation());
        }
        System.out.print("Enter Cineplex ID: ");
        int choice = sc.nextInt();
        Cineplex cineUserChoice = cineplexList.get(choice - 1);
        System.out.println("You have selected " + cineUserChoice.getCineplexName() + " at " + cineUserChoice.getLocation() + ".");
        System.out.print("\n");

        List<Movie> movieList = movieController.retrieveMovieList(cineUserChoice.getMovie());
        cineUserChoice.setMovie(movieList);

        return cineUserChoice;
    }

    private Movie listMovieSpecific(Cineplex cineUserChoice) {
        Movie movUserChoice = null;
        Scanner sc = new Scanner(System.in);
        List<Movie> movieList = new ArrayList<Movie>();
        int movieChoice;

        movieList = cineUserChoice.getMovie();

        if (!movieList.isEmpty()) {
            System.out.println("===== List of Movies =====");
            int i = 0;
            System.out.println("ID    Title");
            for (Movie m : movieList) {
                System.out.format("%-5s %s\n", (i + 1), m.getTitle());
                i++;
            }
            System.out.print("Enter Movie ID: ");
            movieChoice = sc.nextInt();

            movUserChoice = movieList.get(movieChoice - 1);

            System.out.println("You have selected " + movUserChoice.getTitle() + ".");
            System.out.print("\n");
        } else {
            System.out.println("No movie listings in " + cineUserChoice.getCineplexName() + " at " + cineUserChoice.getLocation() + ".");
        }
        return movUserChoice;
    }

    private ShowTime listShowTime(Cineplex cineUserChoice, Movie m) {
        List<ShowTime> showTimeList = new ArrayList<ShowTime>();
        Scanner sc = new Scanner(System.in);
        ShowTimeController stController = new ShowTimeController();

        showTimeList = stController.retrieveShowTimeList(cineUserChoice.getShowTime(), m.getMovieID());
        List<Cineplex.Cinema> cinemaList = cineUserChoice.getCinemas();
        System.out.println("===== Show Times =====");
        System.out.println("ID    Cinema Type  Date       Day       Time");
        int i = 1;
        for (ShowTime st : showTimeList) {
            String cinemaType = "";
            for (Cineplex.Cinema c : cinemaList) {
                if (c.getCinemaCode().equals(st.getCinemaCode())) {
                    cinemaType = c.getCinemaType();
                    break;
                }
            }
            System.out.format("%-5s %-12s %-10s %-9s %s\n", i, cinemaType, st.getDate(), st.getDay(), st.getTime());
            i++;
        }
        int showTimeChoice = 0;
        ShowTime showTimeUserChoice = null;
        if (showTimeList.size() != 0) {
            System.out.print("Enter Show Time ID to book: ");
            showTimeChoice = sc.nextInt();
            showTimeUserChoice = showTimeList.get(showTimeChoice - 1);
            System.out.println("You have selected show time " + showTimeChoice + ".");
            System.out.print("\n");
        }
        return showTimeUserChoice;
    }

    private void showInfo(Cineplex cineUserChoice, Movie movUserChoice, ShowTime showTimeUserChoice) {
        System.out.println("===== Show Information =====");
        System.out.println("Title      : " + movUserChoice.getTitle());
        System.out.println("Cineplex   : " + cineUserChoice.getCineplexName() + " (" + cineUserChoice.getLocation() + ")");

        List<Cineplex.Cinema> cineList = cineUserChoice.getCinemas();

        for (Cineplex.Cinema c : cineList) {
            if (c.getCinemaCode().equals(showTimeUserChoice.getCinemaCode())) {
                System.out.println("Cinema     : " + c.getCinemaCode());
                break;
            }
        }
        System.out.println("Date & Time: " + showTimeUserChoice.getDate() + " (" + showTimeUserChoice.getDay() + ") " + showTimeUserChoice.getTime());
    }

    private void purchaseTicketAndAllocateSeat(int noOfTicks, Movie movie, ShowTime showtime, List<Cineplex.Cinema> cinemaList) {
        Scanner sc = new Scanner(System.in);
        ShowTimeController stController = new ShowTimeController();
        TransactionController transController = new TransactionController();

        List<ShowTime> showTimeList = stController.retrieveShowTimeList();

        List<Cineplex.Seat> tempSeatList = showtime.getSeats();
        System.out.println("===== Cinema " + showtime.getCinemaCode() + " =====");
        printSeatingArrangement(tempSeatList);

        List<Transaction.Tickets> tixList = new ArrayList<Transaction.Tickets>();
        List<ShowTime> showList = new ArrayList<ShowTime>();

        String age = "";
        String movieType = movie.getMovieType();
        String cinemaType = "";
        for (Cineplex.Cinema c : cinemaList) {
            if (c.getCinemaCode().equals(showtime.getCinemaCode())) {
                cinemaType = c.getCinemaType();
            }
        }
        HolidayController hController = new HolidayController();

        String day = "";
        if (hController.isHoliday(showtime.getDate())) {
            day = "Holiday";
        } else {
            day = showtime.getDay();
        }

        double ticketPrice = 0;
        for (int x = 0; x < noOfTicks; x++) {
            boolean allocated = false;
            boolean validSeat = false;
            String rowSelectString;
            int colInt;

            do {
                System.out.print("Enter Seat: ");
                String seatSelect = sc.next().toUpperCase();

                char rowSelect = seatSelect.charAt(0);
                rowSelectString = String.valueOf(rowSelect);
                char colSelect = seatSelect.charAt(1);
                colInt = Character.getNumericValue(colSelect);

                for (Cineplex.Seat seat : tempSeatList) {
                    if ((seat.getRow().equals(rowSelectString)) && (seat.getColumn() == colInt)) {
                        validSeat = true;
                        if (seat.getStatus()) {
                            System.out.println("This seat is already taken. Please enter another seat.\n");
                            break;
                        } else {
                            System.out.print("\n");
                            allocated = true;
                        }

                    }

                }

                if (!validSeat) {
                    System.out.println("Invalid seat. Please enter another seat.\n");
                }

            } while (!allocated);


            for (Cineplex.Seat seat : tempSeatList) {
                if (seat.getRow().equals(rowSelectString) && seat.getColumn() == colInt) {

                    Transaction.Tickets t1 = seat.getTicket();
                    int user_age;
                    do {
                        System.out.println("===== Ticket " + (x + 1) + " =====");
                        System.out.println("1     Student");
                        System.out.println("2     Adult");
                        System.out.print("Enter ticket type: ");
                        user_age = sc.nextInt();
                        System.out.print("\n");

                        switch (user_age) {
                            case 1:
                                age = "Student";
                                break;
                            case 2:
                                age = "Adult";
                                break;
                            default:
                                System.out.println("Invalid input.");
                                break;
                        }

                    } while (user_age >= 5);

                    t1.setAge(age);
                    tixList.add(t1);
                    seat.setStatus(true);
                    seat.setTicket(t1);


                    PriceChartController pcController = new PriceChartController();
                    PriceChart pc = new PriceChart();
                    pc.setAge(age);
                    pc.setCinemaType(cinemaType);
                    pc.setDay(day);
                    pc.setMovieType(movieType);
                    ticketPrice += pcController.getTicketPrice(pc);

                    break;
                }

            }
            showtime.setSeats(tempSeatList);
        }


        System.out.print("Type \"yes\" to confirm booking: ");
        String confirm = sc.next().toLowerCase();
        System.out.print("\n");

        if (confirm.equals("yes")) {
            Object[] custInfo;
            custInfo = enterCustomerInfo();
            String name = (String) custInfo[0];
            int mobileNo = (int) custInfo[1];
            String email = (String) custInfo[2];

            Transaction t = new Transaction(showtime.getCinemaCode(), name, mobileNo, email, ticketPrice, showtime.getMovieID(),
                    showtime.getShowTimeID(), tixList);
            stController.updateShowTimeList(showtime);
            transController.addToTransaction(t);
            System.out.println("Total Ticket price: $" + ticketPrice);

            System.out.println("Booking successful.\n");
        } else {
            System.out.println("Booking cancelled.\n");
        }

    }

    private void printSeatingArrangement(List<Cineplex.Seat> tempSeatList) {
        System.out.println("\t       SCREEN");
        System.out.println("\t ———————————————————");
        System.out.print("\n");

        System.out.println("   1    2    3         4    5    6");
        for (Cineplex.Seat seat : tempSeatList) {
            if (seat.getColumn() == 1)
                System.out.print(seat.getRow() + " ");
            else if (seat.getColumn() == 4) {
                if (seat.getRow().equals("C"))
                    System.out.print(" Aisle ");
                else
                    System.out.print("       ");
            } else
                System.out.print("  ");
            if (seat.getStatus())
                System.out.print("[X]");
            else
                System.out.print("[O]");
            if (seat.getRow().equals("B") && seat.getColumn() == 6)
                System.out.println("\t===== Legend =====");
            else if (seat.getRow().equals("C") && seat.getColumn() == 6)
                System.out.println("\t[O]: Available");
            else if (seat.getRow().equals("D") && seat.getColumn() == 6)
                System.out.println("\t[X]: Taken");
            else if (seat.getColumn() == 6)
                System.out.print("\n");
        }
        System.out.print("\n");
    }

    private Object[] enterCustomerInfo() {
        Scanner sc = new Scanner(System.in);
        Object[] custInfo;
        custInfo = new Object[3];

        System.out.println("===== Customer Information =====");
        System.out.print("Enter your name: ");
        String name = sc.next();
        custInfo[0] = name;

        System.out.print("Enter your mobile number: ");
        int mobileNo = sc.nextInt();
        custInfo[1] = mobileNo;

        System.out.print("Enter your email: ");
        String email = sc.next();
        custInfo[2] = email;
        System.out.print("\n");

        return custInfo;
    }

}
