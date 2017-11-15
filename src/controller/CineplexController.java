package controller;

import app.CustomerMenu;
import entity.*;
import handler.CineplexHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class: CineplexController
 * <p>
 * Class Methods:
 * - CineplexController()
 * - retrieveCineplexList() : List<Cineplex>
 * - addMovieToCineplex(Cineplex c) : boolean
 * - addShowTimeToCineplex(Cineplex c) : boolean
 * - booking() : void
 * - enterCustomerInfo() : Object[]
 * - listCineplex(): Cineplex
 * - listMovieSpecific(Cineplex cineUserChoice) : Movie
 * - listShowTime(Cineplex cineUserChoice, Movie m): ShowTime
 * - purchaseTicketAndAllocateSeat(int noOfTicks,Movie movie,ShowTime showtime, List<Cinema> cinemaList) : void
 * - showInfo(Cineplex cineUserChoice, Movie movUserChoice, ShowTime showTimeUserChoice) : void
 * - printSeatingArrangement(List<Seat> tempSeatList) : void
 */
public class CineplexController {
    private CustomerMenu c_menu = new CustomerMenu();

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


    /**
     * This method updates Cineplex object by CineplexHandler.
     * The Cineplex object c's variables all must be set
     *
     * @param c This is the object of Cineplex
     */
    public boolean updateCineplex(Cineplex c) {
        CineplexHandler handler = new CineplexHandler();
        if (handler.update(c)) {
            return true;
        }
        return false;
    }

    /**
     * This method creates Cineplex object by CineplexHandler.
     * The Cineplec object c's variables all must be set.
     */
    public void addCineplexToList(Cineplex c) {
        CineplexHandler handler = new CineplexHandler();
        handler.create(c);
    }

    /**
     * This method creates booking of movie tickets by users.
     */

    public void booking() {
        Scanner sc = new Scanner(System.in);

        Cineplex cineUserChoice = listCineplex();


        Movie movUserChoice = listMovieSpecific(cineUserChoice);
        if (movUserChoice != null) {

            ShowTime showTimeUserChoice = listShowTime(cineUserChoice, movUserChoice);

            if (showTimeUserChoice != null) {

                showInfo(cineUserChoice, movUserChoice, showTimeUserChoice);

                System.out.println(">> Number of tickets to purchase: ");

                int noOfTicks = sc.nextInt();

                purchaseTicketAndAllocateSeat(noOfTicks, movUserChoice, showTimeUserChoice, cineUserChoice.getCinemas());

            } else {
                System.out.println("Currently movie does not have show time.");
            }
        } else {
            System.out.println("No Movie Listings Now!");
        }

        c_menu.show();
    }


    /**
     * This method gets input of User Information.
     */

    public Object[] enterCustomerInfo() {
        Scanner sc = new Scanner(System.in);
        Object[] custInfo;
        custInfo = new Object[3];

        System.out.println("************CUSTOMER INFO************");
        System.out.println("Enter Name: ");
        String name = sc.next();
        custInfo[0] = name;

        sc = new Scanner(System.in);
        System.out.println("Enter Mobile Number: ");
        int mobileNo = sc.nextInt();
        custInfo[1] = mobileNo;

        System.out.println("Enter Email ID: ");
        String email = sc.next();
        custInfo[2] = email;

        return custInfo;
    }


    public Cineplex listCineplex() {
        Scanner sc = new Scanner(System.in);
        MovieController movieController = new MovieController();
        List<Cineplex> cineplexList = retrieveCineplexList();

        for (int i = 0; i < cineplexList.size(); i++) {
            Cineplex c = cineplexList.get(i);
            System.out.format("|" + (i + 1) + ". Cineplex Name: %s  Location: %s", c.getCineplexName(), c.getLocation());
            System.out.println("\n");
        }

        System.out.println(">> Choose Cineplex: -----------");
        int cineplexChoice = sc.nextInt();


        Cineplex cineUserChoice = cineplexList.get(cineplexChoice - 1);


        List<Movie> movieList = movieController.retrieveMovieList(cineUserChoice.getMovie());
        cineUserChoice.setMovie(movieList);

        return cineUserChoice;
    }

    public Movie listMovieSpecific(Cineplex cineUserChoice) {
        List<Movie> movieList = new ArrayList<Movie>();
        int movieChoice;
        Movie movUserChoice = null;
        Scanner sc = new Scanner(System.in);

        movieList = cineUserChoice.getMovie();

        if (!movieList.isEmpty()) {
            System.out.println("*************List of Movies: *************");
            int i = 0;
            for (Movie m : movieList) {
                System.out.format("|" + (i + 1) + ". Movie Title: %s", m.getTitle());
                System.out.println("\n");
                i++;
            }
            System.out.println(">>Select Movie Index: ---------------");
            movieChoice = sc.nextInt();

            movUserChoice = movieList.get(movieChoice - 1);

            System.out.println("Selected Movie: ");
            System.out.println("Title: " + movUserChoice.getTitle());
            System.out.println("Type: " + movUserChoice.getMovieType());
            System.out.println("Ratings: " + movUserChoice.getRatings());
        } else {
            System.out.println("No Movie Listing Now!");
        }
        return movUserChoice;
    }

    public ShowTime listShowTime(Cineplex cineUserChoice, Movie m) {
        List<ShowTime> showTimeList = new ArrayList<ShowTime>();
        Scanner sc = new Scanner(System.in);
        ShowTimeController stController = new ShowTimeController();

        showTimeList = stController.retrieveShowTimeList(cineUserChoice.getShowTime(), m.getMovieID());
        List<Cinema> cinemaList = cineUserChoice.getCinemas();
        System.out.println("***********Show Times**********");
        int i = 1;
        for (ShowTime st : showTimeList) {
            String cinemaType = "";
            for (Cinema c : cinemaList) {
                if (c.getCinemaCode().equals(st.getCinemaCode())) {
                    cinemaType = c.getCinemaType();
                    break;
                }
            }
            System.out.println(i + ": ");
            System.out.println("Cinema Type: " + cinemaType);
            System.out.println("Day: " + st.getDay());

            System.out.println("Time: " + st.getTime());
            System.out.println("-----------------");
            i++;
        }
        int showTimeChoice = 0;
        ShowTime showTimeUserChoice = null;
        if (showTimeList.size() != 0) {
            System.out.println(">>Select Show Time to Book: ");

            showTimeChoice = sc.nextInt();
            showTimeUserChoice = showTimeList.get(showTimeChoice - 1);
        }
        return showTimeUserChoice;
    }

    public void purchaseTicketAndAllocateSeat(int noOfTicks, Movie movie, ShowTime showtime, List<Cinema> cinemaList) {
        Scanner sc = new Scanner(System.in);
        ShowTimeController stController = new ShowTimeController();
        TransactionController transController = new TransactionController();

        List<ShowTime> showTimeList = stController.retrieveShowTimeList();

        List<Seat> tempSeatList = showtime.getSeats();
        printSeatingArrangement(tempSeatList);

        List<Tickets> tixList = new ArrayList<Tickets>();
        List<ShowTime> showList = new ArrayList<ShowTime>();

        String age = "";
        String movieType = movie.getMovieType();
        String cinemaType = "";
        for (Cinema c : cinemaList) {
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
            boolean seatStat = true;
            boolean validSeat = false;
            String rowSelectString;
            int colInt;

            do {
                System.out.println(">> Enter Seat: ");
                sc = new Scanner(System.in);

                String seatSelect = sc.nextLine().toUpperCase();
                char rowSelect = seatSelect.charAt(0);
                rowSelectString = String.valueOf(rowSelect);
                char colSelect = seatSelect.charAt(1);
                colInt = Character.getNumericValue(colSelect);

                for (Seat seat : tempSeatList) {
                    if ((seat.getRow().equals(rowSelectString)) && (seat.getColumn() == colInt)) {
                        validSeat = true;
                        if (seat.getStatus()) {
                            System.out.println("This seat is already taken. Please enter another seat.");
                            break;
                        } else {
                            seatStat = false;
                        }

                    }

                }

                if (!validSeat) {
                    System.out.println("You have entered an invalid seat! Look at the seating arrange and enter a valid seat!");
                    printSeatingArrangement(tempSeatList);
                }

            } while (seatStat);


            for (Seat seat : tempSeatList) {
                if (seat.getRow().equals(rowSelectString) && seat.getColumn() == colInt) {

                    Tickets t1 = seat.getTicket();
                    int user_age;
                    do {
                        System.out.println(">> Ticket " + (x + 1) + "- Enter age (1) Student, 2) Adult : ");
                        sc = new Scanner(System.in);
                        user_age = sc.nextInt();

                        switch (user_age) {
                            case 1:
                                age = "Student";
                                break;
                            case 2:
                                age = "Adult";
                                break;
                            default:
                                System.out.println("Wrong input, try again!");
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


        System.out.println("Confirmation to buy ticket: (1: yes, 2: no)");
        sc = new Scanner(System.in);
        int confirm = sc.nextInt();


        if (confirm == 1) {
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

            System.out.println("Booking Successful");
        } else {
            System.out.println("Booking Cancelled");
        }

    }


    public void showInfo(Cineplex cineUserChoice, Movie movUserChoice, ShowTime showTimeUserChoice) {
        System.out.println("*********SHOW INFO*********");
        System.out.println("Movie Title: " + movUserChoice.getTitle());
        System.out.println("Cineplex: " + cineUserChoice.getCineplexName() + "(" + cineUserChoice.getLocation() + ")");


        List<Cinema> cineList = cineUserChoice.getCinemas();

        for (Cinema c : cineList) {
            if (c.getCinemaCode().equals(showTimeUserChoice.getCinemaCode())) {
                System.out.println("Cinema: " + c.getCinemaCode());
                break;
            }
        }

        System.out.println("Day & Time: " + showTimeUserChoice.getDay() + " " + showTimeUserChoice.getTime());

    }


    public void printSeatingArrangement(List<Seat> tempSeatList) {
        System.out.println("*********SEATING AVAILABILITY*********");
        System.out.println("                SCREEN                  ");
        System.out.println("             _____________              ");
        System.out.println("\n");

        for (Seat seat : tempSeatList) {
            if (seat.getStatus() == true) {

                System.out.print("\t[X] ");

            } else {
                System.out.print("\t[");
                System.out.print(seat.getRow());
                System.out.print(seat.getColumn());
                System.out.print("]");
            }
            if (seat.getColumn() == 4) {
                System.out.println("\n");
            }
        }
    }


}
