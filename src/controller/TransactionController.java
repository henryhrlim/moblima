package controller;


import app.CustomerMenu;
import app.StaffMenu;
import entity.*;
import handler.TransactionHandler;

import java.util.*;

public class TransactionController {
    private final CustomerMenu c_menu = new CustomerMenu();
    private final StaffMenu s_menu = new StaffMenu();

    public void addToTransaction(Transaction t) {
        TransactionHandler handler = new TransactionHandler();
        if (checkDuplicate(t)) {
        } else {
            handler.create(t);
        }
    }


    private boolean checkDuplicate(Transaction t) {
        TransactionHandler handler = new TransactionHandler();
        List<Transaction> tList = new ArrayList<Transaction>();
        tList = handler.getTransactionList();
        if (retrieveTransactionList() != null) {
            tList = retrieveTransactionList();
            for (Transaction trans : tList) {
                if (t.getTID().equals(trans.getTID())) {
                    System.out.println(t.getTID() + "!!!");
                    System.out.println("Duplicate transaction");
                    return true;
                }
            }
        }
        return false;
    }


    private List<Transaction> retrieveTransactionList() {
        TransactionHandler handler = new TransactionHandler();
        handler.retrieve();
        List<Transaction> tList = handler.getTransactionList();
        if (tList == null)
            tList = new ArrayList<Transaction>();
        return tList;
    }


    private String[] formatString(String str) {
        String[] strArr = new String[2];

        String year = str.substring(3, 7);
        String month = str.substring(7, 9);
        String day = str.substring(9, 11);

        String dateF = day + "/" + month + "/" + year;
        strArr[0] = dateF;

        String hour = str.substring(11, 13);
        String min = str.substring(13, 15);

        String timeF = hour + ":" + min;
        strArr[1] = timeF;

        return strArr;

    }

    private void viewTransactionHistory(String input_name, int input_mobile) {
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


        if (retrieveTransactionList() != null) {
            transList = retrieveTransactionList();
            System.out.println("===== Booking History =====");
            for (Transaction tList : transList) {

                if (input_name.equals(tList.getName().toLowerCase()) && input_mobile == tList.getMobileNum()) {
                    String movieName = null;
                    String cineplexName = null;
                    String cineplexLocation = null;
                    String cinemaCode = tList.getTID().substring(0, 3);
                    List<Transaction.Tickets> tixList = tList.getTicketList();
                    List<Cineplex.Seat> seatList = null;

                    for (Cineplex cineplex : cineplexList) {

                        List<Cineplex.Cinema> cima = cineplex.getCinemas();
                        List<ShowTime> showTimeList = cineplex.getShowTime();
                        for (Cineplex.Cinema c : cima) {
                            if (c.getCinemaCode().equals(cinemaCode)) {
                                cineplexName = cineplex.getCineplexName();
                                cineplexLocation = cineplex.getLocation();
                                for (ShowTime st : showTimeList) {
                                    if (st.getShowTimeID() == tList.getShowTimeID()) {
                                        for (Movie m : movieList) {
                                            if (m.getMovieID() == tList.getMovieID()) {
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
                    System.out.println("Movie         : " + movieName);
                    System.out.println("Cineplex      : " + cineplexName + "(" + cineplexLocation + ")");
                    System.out.println("Cinema        : " + cinemaCode);
                    System.out.println("Date & Time   : " + datePurchase[0] + " " + datePurchase[1]);

                    for (Transaction.Tickets tic : tixList) {
                        for (ShowTime st : showList) {
                            seatList = st.getSeats();
                            for (Cineplex.Seat s : seatList) {
                                Transaction.Tickets ticket = new Transaction.Tickets();
                                ticket = s.getTicket();

                                if (ticket != null) {
                                    if (ticket.getTicketID() == tic.getTicketID()) {
                                        System.out.println("Ticket ID  Ticket Type  Seat  Seat Type");
                                        System.out.format("%-10s %-12s %-4s %s\n", tic.getTicketID(), ticket.getAge(), (s.getRow() + s.getColumn()), s.getSeatType());
                                    }
                                } else {
                                    System.out.println("No transaction records.");
                                }
                            }
                        }
                    }

                    totalAmt += tList.getAmount();
                    System.out.println("Total for above transaction: $" + String.format("%.2f", tList.getAmount()));
                    System.out.print("\n");


                }
            }
            System.out.println("Total spent: $" + String.format("%.2f", totalAmt) + "\n");
        }
    }


    public void getTop5Rating(boolean isStaff) {
        MovieController controller = new MovieController();
        List<Movie> movieList = controller.retrieveMovieList();
        int i = 0;

        Collections.sort(movieList, new Comparator<Movie>() {
            @Override
            public int compare(Movie m1, Movie m2) {
                return Double.compare(m1.getRatings(), m2.getRatings());
            }
        });
        Collections.reverse(movieList);

        System.out.println("===== Top 5 Movies by Rating =====");
        System.out.println("Rank  Movie                                         Rating");

        for (i = 0; i < movieList.size(); i++) {
            System.out.format("%-5s %-45s %s\n", (i + 1), movieList.get(i).getTitle(), movieList.get(i).getRatings());
            if (i == 4)
                break;
        }
        if (i < 4)
            System.out.println("There are less than 5 movie listings");

        System.out.print("\n");

        if (isStaff) {
            s_menu.show();
        } else {
            c_menu.show();
        }
    }


    public void getTop5Sales(boolean isStaff) {
        TransactionController controller = new TransactionController();
        List<Transaction> transList = controller.retrieveTransactionList();

        List<Sales> tsList = new ArrayList<Sales>();

        MovieController movie = new MovieController();
        List<Movie> movieList = movie.retrieveMovieList();
        int i = 0;

        for (Movie m : movieList) {
            Sales ts = new Sales(m.getMovieID(), 0, m.getTitle());
            tsList.add(ts);
        }

        for (Transaction sc : transList) {
            for (i = 0; i < tsList.size(); i++) {
                Sales ts = tsList.get(i);
                double totalAmount = ts.getTotalAmount();
                if (ts.getMovieID() == sc.getMovieID()) {
                    totalAmount += sc.getAmount();
                    ts.setTotalAmount(totalAmount);
                }
                tsList.set(i, ts);
            }
        }

        Collections.sort(tsList, new Comparator<Sales>() {
            @Override
            public int compare(Sales ts1, Sales ts2) {
                return Double.compare(ts1.getTotalAmount(), ts2.getTotalAmount());
            }
        });
        Collections.reverse(tsList);

        System.out.println("===== Top 5 Movies by Ticket Sales =====");
        System.out.println("Rank  Movie                                         Sales");
        for (i = 0; i < tsList.size(); i++) {
            Sales ts = tsList.get(i);
            System.out.format("%-5s %-45s $%s\n", (i + 1), ts.getTitle(), ts.getTotalAmount());
            if (i == 4)
                break;
        }
        if (i < 4)
            System.out.println("There are less than 5 movie listings");

        System.out.print("\n");

        if (isStaff) {
            s_menu.show();
        } else {
            c_menu.show();
        }
    }


    public void viewHistory() {
        Object[] custInfo;
        custInfo = new Object[2];
        custInfo = enterCustomerInfo();
        String name = (String) custInfo[0];
        int mobileNo = (int) custInfo[1];

        TransactionController transController = new TransactionController();
        transController.viewTransactionHistory(name.toLowerCase(), mobileNo);

        c_menu.show();
    }

    private Object[] enterCustomerInfo() {
        Scanner sc = new Scanner(System.in);
        Object[] custInfo;

        custInfo = new Object[2];

        System.out.println("===== Customer Information =====");
        System.out.print("Enter your name: ");
        String name = sc.next();
        custInfo[0] = name;

        System.out.print("Enter your mobile number: ");
        int mobileNo = sc.nextInt();
        custInfo[1] = mobileNo;
        System.out.print("\n");

        return custInfo;
    }


}

	



