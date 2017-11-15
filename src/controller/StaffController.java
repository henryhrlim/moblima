package controller;

import app.MoblimaApp;
import app.StaffMenu;
import entity.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/*
 * Class: StaffController
 * 
 * Class Methods:
 * - authenticate() : boolean
 * - staffMenuCineplex() : void
 * - displayCineplexList() : void
 * - addCineplex() : void
 * - addMovieToCineplex() : void
 * - listMovieSpecificToCineplex(Cineplex cineUserChoice) : void
 * - removeMovieFromCineplex() : void
 * - addShowTimeToCineplex() : void
 * - removeShowTimeFromCineplex() : void
 * - addCinemaToCineplex(Cineplex cineplexUserChoice) : void
 * - removeCinemaFromCineplex() : void
 * - listShowTimeSpecificToCineplex(Cineplex cineUserChoice) : void
 * - staffMenuCinema() : void
 * - displayCinemaList() : void
 * - updateCinemaInCineplex() : void
 * - holidayMain() : void
 * - retrieveList() : void
 * - staffMenuHoliday(int choice): void
 * - listHoliday(): void
 * - addHoliday(): void
 * - updateHoliday(): void
 * - deleteHoliday(): void
 * - printHolidayMenu(): void
 * - staffMenuMovie(): void
 * - displayMovieList(): void
 * - addMovieToList(): void
 * - updateMList(): void
 * - removeMList(): void
 * - showtimeMain(): void
 * - staffMenuShowTime() : void
 * - listShowTime(): void
 * - listbyMovie() : void
 * - listbyCinema() : void
 * - addShowTime(): void
 * - updateShowTime(): void
 * - printShowTimeMenu() : void
 * - updateSTDate(int selected) : void
 * - updateSTTime(int selected) : void
 * - validateDate() : void
 * - validateTime() : void
 * - retrieveCineplex() : void
 * - retrieveCinemaCode(int cineplexID) : String
 * - pricechartMain() : void
 * - staffMenuPriceChart(int pcChoice) : void
 * - printPriceChartMenu() : void
 * - ageGrpMenu() : String
 * - updatePriceChart() : void
 * - listPriceChart() : void
 * - filterAdult(String ageGrp) : List<PriceChart>
 * - filterListPriceChart(String ageGrp) : List<PriceChart>
 *
 */

<<<<<<< HEAD
public class StaffController {
    Scanner sc = new Scanner(System.in);
    private StaffMenu s_menu = new StaffMenu();
    private MoblimaApp app = new MoblimaApp();

    public static String wordWrap(String a) {
        StringBuilder sb = new StringBuilder(a);

        int i = 0;
        int x = 100;
        while (i + 100 < sb.length() && (i = sb.lastIndexOf(" ", i + x)) != -1) {
            sb.replace(i, i + 1, "\n                ");
            x = 115;
        }

        return sb.toString();
    }

    public static String retrieveList() {

        int cineplex;
        String cinemaCode;

        Scanner sc = new Scanner(System.in);

        CineplexController cineController = new CineplexController();
        MovieController movieController = new MovieController();


        List<Cineplex> cineplexList = cineController.retrieveCineplexList();


        int i = 0;
        for (Cineplex c : cineplexList) {
            System.out.format("|" + (i + 1) + ". Cineplex Name: %s  Location: %s", c.getCineplexName(), c.getLocation());
            System.out.println("\n");
            i++;
        }


        System.out.println("\nEnter Index: ");
        cineplex = sc.nextInt();


        List<Cinema> cine = cineplexList.get(cineplex - 1).getCinemas();


        int m = 0;
        for (Cinema c : cine) {
            System.out.format("|" + (m + 1) + ". Cinema Code: %s  Cinema Type: %s", c.getCinemaCode(), c.getCinemaType());
            System.out.println("\n");
            m++;
        }

        System.out.println("\nEnter Index: ");
        cinemaCode = sc.next();


        Cineplex cineUserChoice = cineplexList.get(cineplex - 1);
        List<Movie> movieList = movieController.retrieveMovieList(cineUserChoice.getMovie());


        int n = 0;
        for (Movie mm : movieList) {
            System.out.format("|" + (n + 1) + ". Movie Title: %s", mm.getTitle());
            System.out.println("\n");
            n++;
        }


        return cinemaCode;
    }


    public static void updateMList() {
        int movieOption, updateOption, statusNum;
        String title, movieType, movieRating, showingStatus, duration, synopsis, director, cast;

        MovieController movieController = new MovieController();
        List<Movie> movieList = movieController.retrieveMovieList();

        StaffController staffControl = new StaffController();

        Scanner scn = new Scanner(System.in);

        System.out.println("========== Movies ==========");
        for (Movie m : movieList) {
            System.out.format("|%s. Movie Title: %s", m.getMovieID(), m.getTitle());
            System.out.println("\n");
        }
        System.out.println("Select movie you wish to update: ");
        movieOption = scn.nextInt();

        for (Movie m : movieList) {
            if (movieOption == m.getMovieID()) {

                do {
                    System.out.println("========== Movie Info to Update==========");
                    System.out.println("1. Title");
                    System.out.println("2. Movie type");
                    System.out.println("3. Movie rating");
                    System.out.println("4. Showing status");
                    System.out.println("5. Duration");
                    System.out.println("6. Synopsis");
                    System.out.println("7. Director");
                    System.out.println("8. Cast");
                    System.out.println("9. Go Back");
                    System.out.println("10. Quit");
                    System.out.println("Enter the option that you wish to edit: ");
                    updateOption = scn.nextInt();

                    switch (updateOption) {

                        case 1:
                            scn = new Scanner(System.in);
                            System.out.println("Enter new title: ");
                            title = scn.nextLine();
                            m.setTitle(title);

                            break;

                        case 2:
                            scn = new Scanner(System.in);
                            System.out.println("Enter new movie type (2D, 3D): ");
                            movieType = scn.next();
                            m.setMovieType(movieType);

                            break;

                        case 3:
                            scn = new Scanner(System.in);
                            System.out.println("Enter new movie rating (PG, NC16, M18, R21): ");
                            movieRating = scn.next();
                            m.setMovieRating(movieRating);

                            break;


                        case 4:
                            scn = new Scanner(System.in);
                            System.out.println("Enter new showing status (1.Coming soon 2.Preview 3.Now showing 4.End of Showing): ");
                            statusNum = scn.nextInt();

                            if (statusNum == 1)
                                showingStatus = "Coming soon";
                            else if (statusNum == 2)
                                showingStatus = "Preview";
                            else if (statusNum == 3)
                                showingStatus = "Now Showing";
                            else
                                showingStatus = "End of Showing";

                            m.setMovieStatus(showingStatus);

                            break;

                        case 5:
                            scn = new Scanner(System.in);
                            System.out.println("Enter new movie duration: ");
                            duration = scn.next();
                            m.setDuration(duration);

                            break;

                        case 6:
                            scn = new Scanner(System.in);
                            System.out.println("Enter new synopsis: ");
                            synopsis = scn.nextLine();
                            m.setSynopsis(synopsis);

                            break;

                        case 7:
                            scn = new Scanner(System.in);
                            System.out.println("Enter new director: ");
                            director = scn.nextLine();
                            m.setDirector(director);

                            break;

                        case 8:
                            scn = new Scanner(System.in);
                            System.out.println("Enter new cast: ");
                            cast = scn.nextLine();
                            m.setCast(cast);

                            break;

                        case 9:
                            staffControl.staffMenuMovie();
                            break;

                        case 10:
                            System.out.println("Programm terminating...");
                            System.exit(0);
                            break;
                        default:
                            break;
                    }

                    if (movieController.updateMovie(m))
                        System.out.println("Movie updated successfully! ");
                    else
                        System.out.println("Update failed! ");

                } while (updateOption < 9);


            }
        }

    }


    public static void listShowTime() {
        int movie;
        String cinemaCode;

        Scanner sc = new Scanner(System.in);

        cinemaCode = retrieveList();

        System.out.println("\nEnter Movie Code: ");
        movie = sc.nextInt();

        ShowTimeController stc = new ShowTimeController();
        List<ShowTime> showTimeList = stc.retrieveShowTimeList(cinemaCode, movie);

        int stID = 1;
        String stHeader = String.format("%-15s %-15s %-15s %-12s %-15s %-10s", "Show Time Index", "Cinema Code", "Date", "Day", "Time", "MovieID");
        System.out.println(stHeader);
        System.out.println("---------------" + "    -----------" + "     ----------" + "      ---------" + "    ---------" + "       ---------");

        for (ShowTime st : showTimeList) {
            System.out.println(String.format("%-15s %-15s %-15s %-12s %-15s %-10s", stID, st.getCinemaCode(), st.getDate(), st.getDay(),
                    st.getTime(), st.getMovieID()));
            stID++;
        }

    }


    public static void listbyMovie() {
        int movie, stID;

        Scanner sc = new Scanner(System.in);

        StaffController staffControl = new StaffController();


        CineplexController cineController = new CineplexController();
        MovieController movieController = new MovieController();

        List<Cineplex> cineplexList = cineController.retrieveCineplexList();
        Cineplex cineplex = retrieveCineplex();
        Cinema cinema = retrieveCinemaCode(cineplex);


        List<Movie> movieList = movieController.retrieveMovieList(cineplex.getMovie());


        for (Movie m : movieList) {
            System.out.format("|%s  Movie Title: %s", m.getMovieID(), m.getTitle());
            System.out.println("\n");
        }
        System.out.println("\nEnter Movie Code: ");
        movie = sc.nextInt();

        ShowTimeController stc = new ShowTimeController();
        List<ShowTime> showTimeList = stc.retrieveShowTimeList(cinema.getCinemaCode(), movie);

        if (!showTimeList.isEmpty()) {
            String stHeader = String.format("%-15s %-15s %-15s %-12s %-15s", "Show Time Index", "Cinema Code", "Date", "Day", "Time", "Movie Status");
            System.out.println(stHeader);
            System.out.println(String.format("%-15s %-15s %-15s %-12s %-15s", "---------------", "-----------", "----------", "--------", "------", "---------"));

            for (ShowTime st : showTimeList) {
                System.out.println(String.format("%-15s %-15s %-15s %-12s %-15s", st.getShowTimeID(), st.getCinemaCode(), st.getDate(), st.getDay(),
                        st.getTime()));
            }
        } else {
            System.out.println("No show time to display!");
            staffControl.showtimeMain();
        }


    }


    public static void listbyCinema() {
        int stID;

        Scanner sc = new Scanner(System.in);


        CineplexController cineController = new CineplexController();
        MovieController movieController = new MovieController();

        List<Cineplex> cineplexList = cineController.retrieveCineplexList();
        Cineplex cineplex = retrieveCineplex();
        Cinema cinema = retrieveCinemaCode(cineplex);
        List<Movie> movieList = movieController.retrieveMovieList(cineplex.getMovie());

        ShowTimeController stc = new ShowTimeController();
        List<ShowTime> showTimeList = stc.retrieveShowTimeList(cinema.getCinemaCode());


        if (!showTimeList.isEmpty()) {
            String stHeader = String.format("%-15s %-20s %-15s %-12s %-10s", "Show Time ID", "Movie Title", "Date", "Day", "Time");
            System.out.println(stHeader);
            System.out.println(String.format("%-15s %-20s %-15s %-12s %-10s", "---------------", "------------", "----------", "--------", "------"));

            for (ShowTime st : showTimeList) {
                for (Movie m : movieList) {
                    if (st.getMovieID() == m.getMovieID()) {
                        System.out.println(String.format("%-15s %-20s %-15s %-12s %-10s", st.getShowTimeID(), m.getTitle(), st.getDate(), st.getDay(),
                                st.getTime()));
                    }
                }
            }
        } else {
            System.out.println("No show time available for this cinema");
        }


    }


    public static void addShowTime() {

        int movie, showTimeID, cineplexID;
        String cinemaCode, day, dateStr, timeStr;
        Date dateFmt, timeFmt;

        Scanner sc = new Scanner(System.in);


        CineplexController cineplexController = new CineplexController();
        MovieController movieController = new MovieController();
        ShowTimeController showTimeController = new ShowTimeController();

        List<Cineplex> cineplexList = cineplexController.retrieveCineplexList();
        Cineplex cineplex = retrieveCineplex();
        Cinema cinema = retrieveCinemaCode(cineplex);


        List<Movie> movieList = movieController.retrieveMovieList(cineplex.getMovie());
        int counter = 0;
        for (Movie m : movieList) {
            if (m.getMovieStatus().equals("Now Showing")) {
                counter++;
            }
        }
        if (counter != 0) {


            int i = 0;
            for (Movie m : movieList) {
                if (m.getMovieStatus().equals("Now Showing")) {
                    System.out.format("|%s.  Movie Title: %s", m.getMovieID(), m.getTitle());
                    System.out.println("\n");
                    i++;
                }

            }


            System.out.println("\nEnter Movie ID: ");
            movie = sc.nextInt();


            SimpleDateFormat formattedDate = new SimpleDateFormat("dd/MM/yyyy");
            DateFormat formattedTime = new SimpleDateFormat("HH:mm");
            SimpleDateFormat formattedDay = new SimpleDateFormat("EEEE");

            dateFmt = validateDate();
            dateStr = formattedDate.format(dateFmt);
            day = formattedDay.format(dateFmt);
            timeFmt = validateTime();
            timeStr = formattedTime.format(timeFmt);


            List<ShowTime> showTimeList = showTimeController.retrieveShowTimeList();

            ShowTime st = new ShowTime();
            st.setCinemaCode(cinema.getCinemaCode());
            if (showTimeList.size() == 0) {
                showTimeID = 1;
            } else {
                ShowTime showTime = showTimeList.get(showTimeList.size() - 1);
                showTimeID = showTime.getShowTimeID() + 1;
            }
            st.setShowTimeID(showTimeID);
            st.setTime(timeStr);
            st.setDay(day);
            st.setDate(dateStr);
            st.setMovieID(movie);
            showTimeController.createShowTime(st);

            List<ShowTime> stList = cineplex.getShowTime();
            stList.add(st);
            cineplex.setShowTime(stList);
            cineplexController.updateCineplex(cineplex);
            System.out.println("Show Time has been added successfully!");
        } else {
            System.out.println("Currently Cineplex does not have movie.");
        }
    }


    public static void updateShowTime() {
        listbyMovie();

        int stChoice, updateChoice;
        StaffController staffControl = new StaffController();
        Scanner sc = new Scanner(System.in);

        System.out.println("\nSelect a Show Time ID to update:");
        stChoice = sc.nextInt();

        System.out.println("\nUPDATE SHOW TIME");
        System.out.println("\nChoose to update:");
        System.out.println("|1. Date");
        System.out.println("|2. Time");
        System.out.println("|3. Go Back to Show Time Menu");
        System.out.println("|4. Quit");
        System.out.println("\nEnter your choice: ");
        updateChoice = sc.nextInt();

        switch (updateChoice) {
            case 1:
                updateSTDate(stChoice);
                System.out.println("Show Time Date has been updated successfully!");
                staffControl.showtimeMain();
                break;
            case 2:
                updateSTTime(stChoice);
                System.out.println("Show Time has been updated succesfully!");
                staffControl.showtimeMain();
                break;
            case 3:
                staffControl.showtimeMain();
                break;
            case 4:
                System.out.println("Program Terminating...");
                System.exit(0);
                break;
            default:
                System.out.println("Default");
                break;
        }


    }

    public static void updateSTDate(int selected) {

        String dateStr, day;
        Date dateF;

        Scanner sc = new Scanner(System.in);

        SimpleDateFormat formattedDate = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat formattedDay = new SimpleDateFormat("EEEE");

        ShowTimeController stc = new ShowTimeController();
        List<ShowTime> showTimeList = stc.retrieveShowTimeList();

        dateF = validateDate();
        dateStr = formattedDate.format(dateF);
        day = formattedDay.format(dateF);

        ShowTime stDate = showTimeList.get(selected - 1);
        stDate.setDate(dateStr);
        stDate.setDay(day);
        stc.updateShowTimeList(stDate);
    }


    public static void updateSTTime(int selected) {
        String timeStr;
        Date timeF;

        Scanner sc = new Scanner(System.in);

        DateFormat formattedTime = new SimpleDateFormat("HH:mm");

        ShowTimeController stc = new ShowTimeController();
        List<ShowTime> showTimeList = stc.retrieveShowTimeList();

        timeF = validateTime();
        timeStr = formattedTime.format(timeF);
        ShowTime stTime = null;
        for (ShowTime s : showTimeList) {
            if (s.getShowTimeID() == selected) {
                stTime = s;
            }
        }

        stTime.setTime(timeStr);
        stc.updateShowTimeList(stTime);
    }


    public static Date validateDate() {

        String date;
        boolean dateIncorrect = true;

        Scanner sc = new Scanner(System.in);

        Date currDate = new Date();
        Date dateFormatted = null;
        Date currDateFormatted = null;

        SimpleDateFormat formattedDate = new SimpleDateFormat("dd/MM/yyyy");
        formattedDate.setLenient(false);


        do {
            try {
                System.out.println("\nPlease enter the date (DD/MM/YYYY): ");
                date = sc.next();
                dateFormatted = formattedDate.parse(date);
                currDateFormatted = formattedDate.parse(formattedDate.format(currDate));
                if (dateFormatted.after(currDateFormatted)) {
                    dateIncorrect = false;
                } else if (dateFormatted.before(currDateFormatted)) {
                    System.out.println("Invalid Date!");
                }
            } catch (ParseException e) {
                System.out.println("Invalid!");
            }
        } while (dateIncorrect);
        return dateFormatted;
    }


    public static Date validateTime() {
        String time;
        Date timeF = null;
        boolean timeIncorrect = true;

        Scanner sc = new Scanner(System.in);

        DateFormat formattedTime = new SimpleDateFormat("HH:mm");
        formattedTime.setLenient(false);
        do {
            System.out.print("\nEnter Time of the Show Time (HH:mm): ");
            time = sc.next();
            try {
                timeF = formattedTime.parse(time);
                timeIncorrect = false;
            } catch (ParseException e) {
                System.out.println("Invalid Time!");
            }

        } while (timeIncorrect);

        return timeF;
    }


    public static Cineplex retrieveCineplex() {
        int index;

        Scanner sc = new Scanner(System.in);

        CineplexController cineplexController = new CineplexController();


        List<Cineplex> cineplexList = cineplexController.retrieveCineplexList();


        int i = 0;
        for (Cineplex c : cineplexList) {
            System.out.format("|" + (i + 1) + ". Cineplex Name: %s  Location: %s", c.getCineplexName(), c.getLocation());
            System.out.println("\n");
            i++;
        }

        System.out.println("\nEnter index: ");
        index = sc.nextInt();


        return cineplexList.get(index - 1);
    }


    public static Cinema retrieveCinemaCode(Cineplex cineplex) {

        int index;

        Scanner sc = new Scanner(System.in);


        List<Cinema> cinema = cineplex.getCinemas();


        int i = 0;
        for (Cinema c : cinema) {
            System.out.format("|" + (i + 1) + ". Cinema Code: %s  Cinema Type: %s", c.getCinemaCode(), c.getCinemaType());
            System.out.println("\n");
            i++;
        }

        System.out.println("\nEnter index: ");
        index = sc.nextInt();

        return cinema.get(index - 1);
    }


    public static String ageGrpMenu() {
        int ageChoice;
        String ageGrp = null;
        List<PriceChart> pList = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("Select an age group: ");
        System.out.println("|1. Student");
        System.out.println("|2. Adult");
        System.out.println("|3. Platinum");
        ageChoice = sc.nextInt();

        switch (ageChoice) {
            case 1:
                ageGrp = "Student";
                break;
            case 2:
                ageGrp = "Adult";
                break;
            case 3:
                ageGrp = "Platinum";
                break;
        }
        return ageGrp;
    }


    public static void updatePriceChart() {

        int priceID;
        double newPrice;
        String ageGrp = null;
        List<PriceChart> pList = null;
        Scanner sc = new Scanner(System.in);

        PriceChartController pcController = new PriceChartController();

        ageGrp = ageGrpMenu();

        switch (ageGrp) {
            case "Student":
                pList = filterListPriceChart(ageGrp);
                break;
            case "Adult":
                pList = filterAdult(ageGrp);
                break;
        }

        System.out.println("Select an ID to update: ");
        priceID = sc.nextInt();

        System.out.println("Enter the new ticket price: ");
        newPrice = sc.nextDouble();

        PriceChart pc = pList.get(priceID - 1);
        pc.setPrice(newPrice);
        pcController.updatePriceChart(pc);

        System.out.println("Price List has been updated successfully!");

        filterListPriceChart(ageGrp);
    }


    public static void listPriceChart() {
        String ageGrp;
        List<PriceChart> pList = null;
        ageGrp = ageGrpMenu();
        switch (ageGrp) {
            case "Platinum":
                pList = filterListPriceChartPlat(ageGrp);
                break;
            case "Student":
                pList = filterAdult(ageGrp);
                break;
            case "Adult":
                pList = filterAdult(ageGrp);
                break;
        }
    }


    public static List<PriceChart> filterAdult(String ageGrp) {

        int cinemaChoice, movieTChoice, i;
        String cineType = null, movType = null;

        Scanner sc = new Scanner(System.in);


        System.out.println("Select a movie type: ");
        System.out.println("|1. 2D");
        System.out.println("|2. 3D");
        movieTChoice = sc.nextInt();
        switch (movieTChoice) {
            case 1:
                movType = "2D";
                break;
            case 2:
                movType = "3D";
                break;
        }

        PriceChartController pcController = new PriceChartController();
        List<PriceChart> priceChartList = pcController.retrievePriceChartList(ageGrp, "Normal", movType);

        if (ageGrp == "Student") {
            i = 1;
            String header = String.format("%-5s %-12s %-10s %-10s %15s", "ID", "Day", "Price", "Cinema Type", "Movie Type");
            System.out.println(header);
            for (PriceChart p : priceChartList) {
                String toPrint = String.format("%-5s %-12s %-10s %-10s %10s", i, p.getDay(), p.getPrice(), p.getCinemaType(), p.getMovieType());
                System.out.println(toPrint);
                i++;
            }
        } else {
            i = 1;
            String header = String.format("%-5s %-12s %-10s %-10s %15s", "ID", "Day", "Price", "Cinema Type", "Movie Type");
            System.out.println(header);
            for (PriceChart p : priceChartList) {
                String toPrint = String.format("%-5s %-12s %-10s %-10s %10s", i, p.getDay(), p.getPrice(), p.getCinemaType(), p.getMovieType());
                System.out.println(toPrint);
                i++;
            }
        }
        return priceChartList;

    }


    public static List<PriceChart> filterListPriceChart(String ageGrp) {

        int i;

        PriceChartController pcController = new PriceChartController();
        List<PriceChart> priceChartList = pcController.retrievePriceChartList(ageGrp);

        if ((ageGrp == "Children") || (ageGrp == "Senior")) {
            i = 1;
            String header = String.format("%-5s %-12s", "ID", "Price");
            System.out.println(header);
            for (PriceChart p : priceChartList) {
                String toPrint = String.format("%-5s %-12s", i, p.getPrice());
                System.out.println(toPrint);
                i++;
            }
        }

        return priceChartList;
    }

    public static List<PriceChart> filterListPriceChartPlat(String cineType) {

        int i;

        PriceChartController pcController = new PriceChartController();
        List<PriceChart> priceChartList = pcController.retrievePriceChartListByCinemaType(cineType);

        i = 1;
        String header = String.format("%-5s %-12s %-10s %-10s", "ID", "Day", "Price", "Cinema Type");
        System.out.println(header);
        for (PriceChart p : priceChartList) {
            String toPrint = String.format("%-5s %-12s %-10s %-10s", i, p.getDay(), p.getPrice(), p.getCinemaType());
            System.out.println(toPrint);
            i++;
        }
        return priceChartList;
    }


    public boolean authenticate() {

        System.out.println("Username: ");
        String username = sc.nextLine();
        System.out.println("Password: ");
        String password = sc.nextLine();
        if (username.equals("admin") && password.equals("admin")) {
            System.out.println("Logged in.");
            return true;
        } else
            return false;
    }


    public void staffMenuCineplex() {
        int choice;

        do {
            System.out.println("================= Cineplex Menu =================\n"
                    + "1. List Cineplex\n"
                    + "2. Show Movies in Cineplex\n"
                    + "3. Add Cineplex\n"
                    + "4. Add Movies to Cineplex\n"
                    + "5. Remove Movies from Cineplex\n"
                    + "6. Back\n"
                    + "7. Quit\n"
                    + "Please select an option: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    displayCineplexList();
                    break;
                case 2:
                    StaffController staffControl = new StaffController();
                    CineplexController cineplexControl = new CineplexController();
                    Cineplex cineUserChoice = cineplexControl.listCineplex();
                    staffControl.listMovieSpecificToCineplex(cineUserChoice);
                    break;
                case 3:
                    addCineplex();
                    break;
                case 4:
                    addMovieToCineplex();
                    break;
                case 5:
                    removeMovieFromCineplex();
                    break;
                case 6:
                case 7:
                    break;
                default:
                    System.out.println("Invalid option. Please enter an integer between 1 to 7");
                    break;
            }
        } while (choice != 6 && choice != 7);

        if (choice == 6)
            s_menu.show();
        else if (choice == 7) {
            System.out.println("Exiting...");
            sc.close();
            System.exit(0);
        }
    }


    public void displayCineplexList() {
        CineplexController cineplexControl = new CineplexController();
        List<Cineplex> cineplexList = cineplexControl.retrieveCineplexList();

        int i = 0;
        for (Cineplex c : cineplexList) {
            System.out.format("|" + (i + 1) + ". Cineplex Name: %s  Location: %s", c.getCineplexName(), c.getLocation());
            System.out.println("\n");
            i++;
        }
        if (cineplexList.size() == 0) {
            System.out.println("Currently there is no cineplex.");
        }

    }


    public void addCineplex() {
        int cineplexID, cinemaUser, countCineplex = 0;
        String name, location;
        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);

        CineplexController cineplexController = new CineplexController();
        List<Cineplex> cineplexList = cineplexController.retrieveCineplexList();
        List<Cinema> cinema = new ArrayList<Cinema>();
        List<Movie> movie = new ArrayList<Movie>();
        List<ShowTime> showTime = new ArrayList<ShowTime>();

        System.out.println("Enter Cineplex Name: ");
        name = sc.nextLine();

        System.out.println("Enter Cineplex Location: ");
        location = sc.nextLine();


        Cineplex cine = null;
        System.out.println("Do you want allocate cinemas for Cineplex " + name + " (1: yes, 2: no)?");
        cinemaUser = sc2.nextInt();

        if (cinemaUser == 1) {
            cine = new Cineplex(cineplexList.size() + 1, location, name, null);
            cine.setMovie(movie);
            cine.setShowTime(showTime);
            cine.setCinemas(new ArrayList<Cinema>());
            cineplexController.addCineplexToList(cine);
            addCinemaToCineplex(cine);

        } else {
            cine = new Cineplex(cineplexList.size() + 1, location, name, null);
            cine.setMovie(movie);
            cine.setShowTime(showTime);
            cineplexController.addCineplexToList(cine);
        }


    }


    public void addMovieToCineplex() {
        int movieChoice = 0;

        Scanner sc = new Scanner(System.in);

        MovieController movieController = new MovieController();
        List<Movie> movieListView = movieController.retrieveMovieList();

        CineplexController cineplexControl = new CineplexController();
        Cineplex cineplexUserChoice = cineplexControl.listCineplex();

        listMovieSpecificToCineplex(cineplexUserChoice);

        List<Movie> movieList = new ArrayList<Movie>();
        movieList = cineplexUserChoice.getMovie();

        int count = 0;
        if (!movieList.isEmpty()) {
            System.out.println("**************List of Movies Available*************************");
            for (Movie mov : movieListView) {
                if (mov.getMovieStatus().compareTo("Now Showing") == 0) {
                    System.out.format("|%s.  Movie Title: %s", mov.getMovieID(), mov.getTitle());
                    System.out.println("\n");
                    count++;
                }
            }
        }


        System.out.println("\n");


        if (count != movieList.size()) {
            boolean movieInside;
            do {
                movieInside = false;
                System.out.println("Enter Movie index to Add to Cineplex: ");
                movieChoice = sc.nextInt();

                for (Movie mm : movieList) {
                    if (movieChoice == mm.getMovieID()) {
                        System.out.println("Movie ID" + movieChoice + " is already added to the cineplex");
                        movieInside = true;
                        break;
                    }
                }


            } while (movieInside);

            Movie m = movieListView.get(movieChoice - 1);

            movieList.add(m);
            Collections.sort(movieList, new Comparator<Movie>() {

                @Override
                public int compare(Movie m1, Movie m2) {
                    return Double.compare(m1.getMovieID(), m2.getMovieID());
                }

            });
            cineplexUserChoice.setMovie(movieList);
            cineplexControl.updateCineplex(cineplexUserChoice);

            System.out.println("Movie '" + m.getTitle() + "' added successfully to Cineplex '" + cineplexUserChoice.getCineplexName() + "'");

        } else {
            System.out.println("No more movies to add!");
        }


    }


    public void removeMovieFromCineplex() {
        int movieChoice;

        Scanner sc = new Scanner(System.in);

        CineplexController cineplexControl = new CineplexController();
        Cineplex cineplexUserChoice = cineplexControl.listCineplex();

        listMovieSpecificToCineplex(cineplexUserChoice);

        List<Movie> movieList = new ArrayList<Movie>();
        movieList = cineplexUserChoice.getMovie();


        System.out.println("\n");

        System.out.println("Enter index to Remove from Cineplex: ");
        movieChoice = sc.nextInt();

        Movie m = movieList.get(movieChoice - 1);

        movieList.remove(m);
        cineplexUserChoice.setMovie(movieList);
        cineplexControl.updateCineplex(cineplexUserChoice);

        System.out.println("Movie '" + m.getTitle() + "' successfully removed from Cineplex '" + cineplexUserChoice.getCineplexName() + "'");
    }


    public void listMovieSpecificToCineplex(Cineplex cineUserChoice) {
        List<Movie> movieList = new ArrayList<Movie>();
        Scanner sc = new Scanner(System.in);

        movieList = cineUserChoice.getMovie();

        System.out.println("*************List of Movies Inside the Cineplex: *************");
        for (int i = 0; i < movieList.size(); i++) {
            Movie m = movieList.get(i);
            System.out.format("|" + (i + 1) + ". Movie Title: %s", m.getTitle());
            System.out.println("\n");
        }
    }


    public void listShowTimeSpecificToCineplex(Cineplex cineUserChoice) {
        List<ShowTime> showTimeList = new ArrayList<ShowTime>();
        Scanner sc = new Scanner(System.in);

        showTimeList = cineUserChoice.getShowTime();

        System.out.println("*************List of Show Times Inside the Cineplex: *************");
        int i = 0;
        for (ShowTime st : showTimeList) {
            System.out.format("|" + (i + 1) + ". Date: %s   Time: %ss", st.getDate(), st.getTime());
            System.out.println("\n");
            i++;
        }
        System.out.println("\n");
    }


    public void staffMenuCinema() {
        int staffChoiceCinema = 0;
        Scanner sc = new Scanner(System.in);
        CineplexController cineplexControl = new CineplexController();

        System.out.println("^^^^^^^^^^^^^^CINEMA MENU^^^^^^^^^^^^^");
        System.out.println("|1. List Cinema");
        System.out.println("|2. Create New Cinema");
        System.out.println("|3. Update Cinema");
        System.out.println("|4. Remove Cinema");

        System.out.println("|5. Go Back to Staff Menu");
        System.out.println("|6. Quit");

        System.out.println("Choice (1-6): ");
        staffChoiceCinema = sc.nextInt();

        switch (staffChoiceCinema) {

            case 1:
                displayCinemaList();
                staffMenuCinema();
                break;

            case 2:
                Cineplex cineplexUserChoice = cineplexControl.listCineplex();
                addCinemaToCineplex(cineplexUserChoice);
                staffMenuCinema();
                break;


            case 3:
                updateCinemaInCineplex();
                staffMenuCinema();
                break;


            case 4:
                removeCinemaFromCineplex();
                staffMenuCinema();
                break;

            case 5:
                s_menu.show();
                break;
            case 6:
                System.out.println("Program Terminating...");
                System.exit(0);
                break;

            default:
                break;
        }

        s_menu.show();

    }


    public void displayCinemaList() {
        CineplexController cineControl = new CineplexController();

        Cineplex cineUserChoice = cineControl.listCineplex();

        List<Cinema> cinemaList = cineUserChoice.getCinemas();

        int i = 0;
        for (Cinema c : cinemaList) {
            System.out.format("|" + (i + 1) + ". Cinema Code: %s  Cinema Type: %s", c.getCinemaCode(), c.getCinemaType());
            System.out.println("\n");
            i++;
        }

    }


    public void updateCinemaInCineplex() {
        int choice = 0;
        String userType = null;
        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);

        CineplexController cineplexControl = new CineplexController();
        Cineplex cineplexUserChoice = cineplexControl.listCineplex();
        int cineplexID = cineplexUserChoice.getCineplexID();

        List<Cinema> cinemaList = cineplexUserChoice.getCinemas();


        int i = 0;
        for (Cinema c : cinemaList) {
            System.out.format("|" + (i + 1) + ". Cinema Type: %s  Cinema Code: %s", c.getCinemaCode(), c.getCinemaType());
            System.out.println("\n");
            i++;
        }


        System.out.println("Enter index number to update: ");
        choice = sc.nextInt();

        Cinema c = cinemaList.get(choice - 1);

        int a = 0;

        do {
            System.out.println("Cinema " + (i + 1) + " type (1 - platinum, 2 - normal): ");
            a = sc.nextInt();
            switch (a) {
                case 1:
                    userType = "platinum";
                    break;
                case 2:
                    userType = "normal";
                    break;
                default:
                    System.out.println("Please re-enter 1 or 2.");

                    break;

            }
        } while (userType.equals(""));
        c.setCinemaType(userType);

        cineplexUserChoice.setCinemas(cinemaList);
        cineplexControl.updateCineplex(cineplexUserChoice);

        System.out.println(c.getCinemaCode() + " cinema has been successfully updated in the Cineplex");
    }


    public void addCinemaToCineplex(Cineplex cineplexUserChoice) {
        String code = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int numberToAdd = 0;
        Scanner sc = new Scanner(System.in);

        CineplexController cineplexControl = new CineplexController();

        int cineplexID = cineplexUserChoice.getCineplexID();

        List<Cinema> cinemaList = cineplexUserChoice.getCinemas();

        System.out.println("Enter the number of Cinema: ");
        numberToAdd = sc.nextInt();

        for (int i = 0; i < numberToAdd; i++) {
            sc = new Scanner(System.in);
            String cinemaType = "";
            String cinemaCode = "";
            int choice = 0;

            do {
                System.out.println("Cinema " + (i + 1) + " type (1 - platinum, 2 - normal): ");
                choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        cinemaType = "platinum";
                        break;
                    case 2:
                        cinemaType = "normal";
                        break;
                    default:
                        System.out.println("Please re-enter 1 or 2.");

                        break;

                }
            } while (cinemaType.equals(""));

            if (cinemaList.size() != 0) {
                String index = cinemaList.get(cinemaList.size() - 1).getCinemaCode().charAt(2) + "";
                int a = Integer.parseInt(index) + 1;
                cinemaCode = code.charAt(cineplexID - 1) + "0" + a;
            } else {
                cinemaCode = code.charAt(cineplexID - 1) + "01";
            }
            cinemaList.add(new Cinema(cinemaType, cinemaCode));
        }

        cineplexUserChoice.setCinemas(cinemaList);
        cineplexControl.updateCineplex(cineplexUserChoice);

        System.out.println(numberToAdd + " cinema(s) has been successfully added to the Cineplex");
    }


    public void removeCinemaFromCineplex() {
        int choice = 0;
        Scanner sc = new Scanner(System.in);

        CineplexController cineplexControl = new CineplexController();
        Cineplex cineplexUserChoice = cineplexControl.listCineplex();
        int cineplexID = cineplexUserChoice.getCineplexID();

        List<Cinema> cinemaList = cineplexUserChoice.getCinemas();

        int i = 0;
        for (Cinema c : cinemaList) {
            System.out.format("|" + (i + 1) + ". Cinema Code: %s  Cinema Type: %s", c.getCinemaCode(), c.getCinemaType());
            System.out.println("\n");
            i++;
        }

        System.out.println("Enter index number to remove: ");
        choice = sc.nextInt();

        Cinema c = cinemaList.get(choice - 1);
        cinemaList.remove(choice - 1);

        cineplexUserChoice.setCinemas(cinemaList);
        cineplexControl.updateCineplex(cineplexUserChoice);

        System.out.println(c.getCinemaCode() + " cinema has been successfully removed from Cineplex");
    }


    public void holidayMain() {
        Scanner sc = new Scanner(System.in);

        System.out.println("^^^^^^^^^^^^^^HOLIDAY^^^^^^^^^^^^^");
        System.out.println("|1. List Public Holiday");
        System.out.println("|2. Create Public Holiday");
        System.out.println("|3. Update Public Holiday");
        System.out.println("|4. Remove Public Holiday");
        System.out.println("|5. Go Back to Staff Menu");

        System.out.println("Enter your choice: ");
        int staffChoiceHoliday = sc.nextInt();


        switch (staffChoiceHoliday) {
            case 1:
                listHoliday();
                holidayMain();
                break;
            case 2:
                addHoliday();
                holidayMain();
                break;
            case 3:
                updateHoliday();
                holidayMain();
                break;
            case 4:
                deleteHoliday();
                holidayMain();
                break;
            case 5:
                s_menu.show();
                break;
            case 6:
                System.out.println("Program Terminating...");
                System.exit(0);
                break;
            default:
                break;
        }
        s_menu.show();

    }


    public void printHolidayMenu() {
        System.out.println("^^^^^^^^^^^^^^HOLIDAY^^^^^^^^^^^^^");
        System.out.println("|1. List Public Holiday");
        System.out.println("|2. Create Public Holiday");
        System.out.println("|3. Update Public Holiday");
        System.out.println("|4. Remove Public Holiday");
        System.out.println("|5. Go Back to Staff Menu");

    }


    public void listHoliday() {
        int holID;

        HolidayController hc = new HolidayController();
        List<Holiday> holidayList = hc.retrieveHolidayList();


        if (!holidayList.isEmpty()) {
            int n = 0;
            for (Holiday h : holidayList) {
                System.out.format("|" + (n + 1) + ". Holiday Name: %s  Date: %s", h.getHolidayName(), h.getDate());
                System.out.println("\n");
                n++;
            }
        } else {
            System.out.println("No Public Holidays has been set!");
        }


    }


    public void addHoliday() {

        String holidayName, holidayDate;
        Date dateF = null;
        Scanner sc = new Scanner(System.in);

        System.out.println("Name of Holiday: ");
        holidayName = sc.nextLine();

        SimpleDateFormat formattedDate = new SimpleDateFormat("dd/MM/yyyy");

        dateF = validateDate();
        holidayDate = formattedDate.format(dateF);

        Holiday hol = new Holiday(holidayDate, holidayName);
        HolidayController hc = new HolidayController();
        hc.createHoliday(hol);
        System.out.println("Public Holiday has been added successfully!");
    }


    public void updateHoliday() {
        listHoliday();

        int holID, holChoice;
        String holidayName, holidayDate;
        Date dateFmt = null;

        Scanner sc = new Scanner(System.in);
        Scanner scStr = new Scanner(System.in);

        System.out.println("Select an ID to update:");
        holID = sc.nextInt();

        HolidayController hc = new HolidayController();
        List<Holiday> holidayList = hc.retrieveHolidayList();

        System.out.println("UPDATE PUBLIC HOLIDAY");
        System.out.println("Choose to update: ");
        System.out.println("|1. Holiday Name");
        System.out.println("|2. Holiday Date");
        holChoice = sc.nextInt();

        switch (holChoice) {
            case 1:
                System.out.println("Enter New Holiday Name: ");
                holidayName = scStr.nextLine();
                Holiday holName = holidayList.get(holID - 1);
                holName.setHolidayName(holidayName);
                hc.updateHoliday(holName);
                System.out.println("Public Holiday Name has been updated successfully!");
                break;
            case 2:
                SimpleDateFormat formattedDate = new SimpleDateFormat("dd/MM/yyyy");

                dateFmt = validateDate();
                holidayDate = formattedDate.format(dateFmt);

                Holiday holDate = holidayList.get(holID - 1);
                holDate.setDate(holidayDate);
                hc.updateHoliday(holDate);
                System.out.println("Public Holiday Date has been updated successfully!");
                break;
        }
        listHoliday();
    }


    public void deleteHoliday() {
        listHoliday();
        int holID;
        Scanner sc = new Scanner(System.in);
        System.out.println("Select an ID to delete");
        holID = sc.nextInt();
        HolidayController hc = new HolidayController();
        List<Holiday> holidayList = hc.retrieveHolidayList();
        Holiday h = holidayList.get(holID - 1);
        hc.deleteHoliday(h);
        System.out.println("Public Holiday has been deleted successfully!");
    }


    public void staffMenuMovie() {
        int staffChoiceMovie = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("^^^^^^^^^^^^^^MOVIE MENU^^^^^^^^^^^^^");
        System.out.println("|1. List Movies");
        System.out.println("|2. Create New Movie");
        System.out.println("|3. Update Movie");
        System.out.println("|4. Remove Movie");

        System.out.println("|5. Go Back to Staff menu");
        System.out.println("|6. Quit");

        System.out.println("Choice (1-5): ");
        staffChoiceMovie = sc.nextInt();

        switch (staffChoiceMovie) {
            case 1:
                displayMovieList();
                staffMenuMovie();
                break;

            case 2:
                addMovieToList();
                staffMenuMovie();
                break;

            case 3:
                updateMList();
                staffMenuMovie();
                break;

            case 4:
                removeMList();
                staffMenuMovie();
                break;
            case 5:
                s_menu.show();
                break;
            case 6:
                System.out.println("Program Terminating...");
                System.exit(0);
                break;

            default:
                break;
        }
        s_menu.show();

    }


    public void displayMovieList() {
        StaffController staffControl = new StaffController();
        MovieController movieController = new MovieController();
        List<Movie> movieList = movieController.retrieveMovieList();

        for (Movie m : movieList) {
            System.out.println("Movie Title   : " + m.getTitle());
            System.out.println("Movie type    : " + m.getMovieType());
            System.out.println("Movie rating  : " + m.getMovieRating());
            System.out.println("Showing Status: " + m.getMovieStatus());
            System.out.println("Duration      : " + m.getDuration());
            System.out.println("Synopsis      : " + staffControl.wordWrap(m.getSynopsis()));
            System.out.println("Director      : " + m.getDirector());
            System.out.println("Cast          : " + m.getCast());

            List<Review> reviewList = m.getReviews();

            if (reviewList.size() > 1)
                System.out.println("Overall Reviewer Ratings: " + m.getRatings());

            if (reviewList.size() > 0) {
                System.out.println("****************Past reviewers rating**************** ");

                for (Review r : reviewList) {
                    System.out.println("Past Rating   : " + r.getRating());
                    System.out.println("Past feedback : " + staffControl.wordWrap(r.getFeedback()));
                }
            }
            System.out.println("\n\n");
        }
    }


    public void addMovieToList() {
        int statusNum, movieID;
        String title, movieRating, duration, synopsis, director, cast, movieType, showingStatus;

        Scanner scn = new Scanner(System.in);

        System.out.println("Enter movie title: ");
        title = scn.nextLine();

        scn = new Scanner(System.in);

        System.out.println("Enter movie type: ");
        movieType = scn.next();

        scn = new Scanner(System.in);

        System.out.println("Enter movie rating: ");
        movieRating = scn.next();

        scn = new Scanner(System.in);
        System.out.println("Enter showing status (1.Coming soon 2.Preview 3.Now showing): ");
        statusNum = scn.nextInt();

        if (statusNum == 1) showingStatus = "Coming soon";
        else if (statusNum == 2) showingStatus = "Preview";
        else showingStatus = "Now Showing";

        scn = new Scanner(System.in);
        System.out.println("Enter movie duration: ");
        duration = scn.nextLine();

        scn = new Scanner(System.in);
        System.out.println("Enter movie synopsis: ");
        synopsis = scn.nextLine();

        scn = new Scanner(System.in);
        System.out.println("Enter movie director: ");
        director = scn.nextLine();

        scn = new Scanner(System.in);
        System.out.println("Enter movie cast: ");
        cast = scn.nextLine();

        MovieController movieController = new MovieController();
        List<Movie> movieList = movieController.retrieveMovieList();
        if (movieList == null) {
            movieID = 0;
        } else {
            movieID = movieList.size() + 1;
        }
        Movie m = new Movie(movieID, 0.0, movieRating, duration, title, synopsis, director, cast, movieType, showingStatus);

        movieController.createMovie(m);
        System.out.println("\nMovie successfully added into list! \n");

    }


    public void removeMList() {
        int movieOption;
        MovieController movieController = new MovieController();
        List<Movie> movieList = movieController.retrieveMovieList();

        Scanner scn = new Scanner(System.in);

        System.out.println("========== Movies ==========");
        for (Movie m : movieList) {
            if (m.getMovieStatus().compareTo("End of Showing") != 0)
                System.out.println(m.getMovieID() + ". " + m.getTitle());
        }
        System.out.println("Select Movie ID you wish to remove(changing showing status to 'End of Showing'): ");
        movieOption = scn.nextInt();

        if (movieController.removeMovie(movieOption))
            System.out.println("Movie removed from movie list! ");
        else
            System.out.println("Deletion failed! ");
    }


    public void showtimeMain() {
        Scanner sc = new Scanner(System.in);

        System.out.println("^^^^^^^^^^^^^^SHOW TIMES^^^^^^^^^^^^^");
        System.out.println("|1. List Show Times");
        System.out.println("|2. Create Show Times");
        System.out.println("|3. Update Show Times");
        System.out.println("|4. Go Back to Staff Menu");
        System.out.println("|5. Quit");


        System.out.println("Enter your choice: ");
        int staffChoiceST = sc.nextInt();


        switch (staffChoiceST) {
            case 1:
                listbyCinema();
                showtimeMain();
                break;
            case 2:
                addShowTime();
                showtimeMain();
                break;
            case 3:
                updateShowTime();
                showtimeMain();
                break;
            case 4:
                s_menu.show();
                break;
            case 5:
                System.out.println("Program Terminating...");
                System.exit(0);
                break;
        }

        s_menu.show();

    }


    public void pricechartMain() {
        Scanner sc = new Scanner(System.in);

        System.out.println("^^^^^^^^^^^^^^PRICE CHART^^^^^^^^^^^^^");
        System.out.println("|1. List Price Chart");
        System.out.println("|2. Update Price Chart");
        System.out.println("|3. Go Back to Staff Menu");
        System.out.println("|4. Quit");

        System.out.println("Enter your choice: ");
        int staffChoiceST = sc.nextInt();

        switch (staffChoiceST) {
            case 1:
                listPriceChart();
                pricechartMain();
                break;
            case 2:
                updatePriceChart();
                pricechartMain();
                break;
            case 3:
                s_menu.show();
                break;
            case 4:
                System.out.println("Program Terminating...");
                System.exit(0);
                break;
            default:
                break;
        }
        s_menu.show();

    }


    public void staffMenuPriceChart(int pcChoice) {

    }


    public void printPriceChartMenu() {

    }

=======
public class StaffController{
	private StaffMenu s_menu = new StaffMenu();
	private MoblimaApp app = new MoblimaApp();
	
	/*
	 * *****************************************************************************************************************
	 * 												Staff Credential					    		                  *
	 ******************************************************************************************************************/	
	
	/**
	 * This method ensures that staff is login credentials are verified
	 */
	public boolean authenticate(){ 
		boolean auth = false;
		String username, password;
		Scanner scn =new Scanner(System.in);
		System.out.println("------------------Staff Login---------------");
		System.out.println("Enter username: ");
		username = scn.next();
		System.out.println("Enter password: ");
		password = scn.next();

		// set your username & password here //
		if(username.equals("staff1") && password.equals("password")) {
			System.out.println("Logged in successfully! ");
			auth = true;
		}

		else {
			System.out.println("Incorrect username and password! Please try again.");
			auth = false;
		}
		return auth;

	}
	
	public static String wordWrap(String a){
		StringBuilder sb = new StringBuilder(a);
		
		int i=0;
		int x=100;
		while(i+100 < sb.length() && (i = sb.lastIndexOf(" ",i+x))!=-1){
			sb.replace(i, i+1, "\n                ");
			x=115;
		}
		
		return sb.toString();
	}
	
	
	/*
	 * *****************************************************************************************************************
	 * 												Staff CINEPLEX MENU 					    		                  *
	 ******************************************************************************************************************/
	
	
	/**
	 * This method prints the menu for Cineplex
	 */
	public void staffMenuCineplex() { 
		int staffChoiceCineplex = 0;
		Scanner sc = new Scanner(System.in);
		CineplexController cineplexControl = new CineplexController();
		
		System.out.println("^^^^^^^^^^^^^^CINEPLEX MENU^^^^^^^^^^^^^");
		System.out.println("|1. List Cineplex");
		System.out.println("|2. Show Movies in Cineplex"); 
		System.out.println("|3. Add Cineplex"); 
		System.out.println("|4. Add Movies to Cineplex");
		System.out.println("|5. Remove Movies from Cineplex"); //make end of showing
		System.out.println("|6. Go Back to Staff menu");
		System.out.println("|7. Quit");
		
		System.out.println("Choice (1-7): ");
		staffChoiceCineplex = sc.nextInt();
		
		switch(staffChoiceCineplex){
				/**List Cineplex*/
			case 1: //DONE
				displayCineplexList();
				staffMenuCineplex();
				break;
				
				/**Show Movies in Cineplex*/
			case 2:
				StaffController staffControl = new StaffController();
				
				
				CineplexController cineplexController = new CineplexController();
				Cineplex cineplexUserChoice = cineplexController.listCineplex();
				
				staffControl.listMovieSpecificToCinplex(cineplexUserChoice);
				staffMenuCineplex();
				break;
				/**Add Cineplex*/
			case 3:
				addCineplex();
				staffMenuCineplex();
				break;
				
				/**Add Movies to a Cineplex*/
			case 4: //DONE
				addMovieToCineplex();
				staffMenuCineplex();
				break;
				
				/**Remove Movies from a Cineplex*/
			case 5:  //DONE
				removeMovieFromCineplex();
				staffMenuCineplex();
				break;
			
				
				/**Return to staff menu*/
			case 6:
				s_menu.show();
				break;
				
				/**Quit the application*/
			case 7:
				System.out.println("Program Terminating...");
				System.exit(0);
				break;
			
			default:
				break;
				
		}
		
		s_menu.show();
		
		
		
	}
	
	/**
	 * This method displays the list of cineplexes
	 */
	public void displayCineplexList(){
		CineplexController cineplexControl = new CineplexController();
		List<Cineplex> cineplexList = cineplexControl.retrieveCineplexList();

		int i=0;
		for(Cineplex c: cineplexList) {
			System.out.format("|"+ (i+1) +". Cineplex Name: %s  Location: %s",c.getCineplexName(),c.getLocation());
			System.out.println("\n");
			i++;
		}
		if(cineplexList.size() == 0){
			System.out.println("Currently there is no cineplex.");
		}
		
	}

	/**
	 * This method adds a new cineplex
	 */
	public void addCineplex() {
		int cineplexID, cinemaUser, countCineplex = 0;
		String name, location;
		Scanner sc = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);
		
		CineplexController cineplexController = new CineplexController();
		List<Cineplex> cineplexList = cineplexController.retrieveCineplexList();	
		List<Cinema> cinema = new ArrayList<Cinema>();
		List<Movie> movie = new ArrayList<Movie>();
		List<ShowTime> showTime = new ArrayList<ShowTime>();
		
		System.out.println("Enter Cineplex Name: ");
		name = sc.nextLine();
		
		System.out.println("Enter Cineplex Location: ");
		location = sc.nextLine();
		
		
		Cineplex cine = null;
		System.out.println("Do you want allocate cinemas for Cineplex " + name + " (1: yes, 2: no)?");
		cinemaUser = sc2.nextInt();
		
		if(cinemaUser == 1){
			cine = new Cineplex(cineplexList.size()+1,location, name, null);
			cine.setMovie(movie);
			cine.setShowTime(showTime);
			cine.setCinemas(new ArrayList<Cinema>());
			cineplexController.addCineplexToList(cine);
			addCinemaToCineplex(cine);
			
		}
		else{
			cine = new Cineplex(cineplexList.size()+1,location, name, null);
			cine.setMovie(movie);
			cine.setShowTime(showTime);
			cineplexController.addCineplexToList(cine);
		}
		
		
	}

	/**
	 * This method adds movie to cineplex
	 */
	public void addMovieToCineplex(){
		int movieChoice = 0;
		//String[] movieUserList = null;
		Scanner sc = new Scanner(System.in);
		
		MovieController movieController = new MovieController();
		List<Movie> movieListView = movieController.retrieveMovieList();
		
		CineplexController cineplexControl = new CineplexController();
		Cineplex cineplexUserChoice = cineplexControl.listCineplex();
		
		listMovieSpecificToCinplex(cineplexUserChoice);
		
		List<Movie> movieList = new ArrayList<Movie>();
		movieList = cineplexUserChoice.getMovie();	
		
		int count = 0;
		if(!movieList.isEmpty()){
			System.out.println("**************List of Movies Available*************************");
			for(Movie mov: movieListView) {
				if(mov.getMovieStatus().compareTo("Now Showing") == 0) {
					System.out.println(mov.getMovieID() + ".  Movie Title: " + mov.getTitle());
					count++;
				}
			}
		} 
		
		
		
		System.out.println("\n");
	
		
		if(count != movieList.size()){
			boolean movieInside;
			do{
				movieInside = false;
				System.out.println("Enter Movie index to Add to Cineplex: ");
				movieChoice = sc.nextInt();
				
				for(Movie mm: movieList){
					if(movieChoice == mm.getMovieID()){
						System.out.println("Movie ID " + movieChoice + " - " + mm.getTitle() + " is already added to the cineplex");
						movieInside = true;
						break;
					}
				}
				
				
			}while(movieInside);
			
			Movie m = movieListView.get(movieChoice-1);
			
			movieList.add(m);
			Collections.sort(movieList, new Comparator<Movie>(){

				@Override
			    public int compare(Movie m1, Movie m2) {
			        return Double.compare(m1.getMovieID(), m2.getMovieID());
			    }
				
			});
			cineplexUserChoice.setMovie(movieList);
			cineplexControl.updateCineplex(cineplexUserChoice);
			
			System.out.println("Movie '" + m.getTitle() + "' added successfully to Cineplex '" + cineplexUserChoice.getCineplexName() + "'");
			
		} else {
			System.out.println("No more movies to add!");
		}
		
		
		
		
		
	}
	
	/**
	 * This method removes movie from cineplex
	 */
	public void removeMovieFromCineplex(){
		int movieChoice;
		//String[] movieUserList = null;
		Scanner sc = new Scanner(System.in);
		
		CineplexController cineplexControl = new CineplexController();
		Cineplex cineplexUserChoice = cineplexControl.listCineplex();
		
		listMovieSpecificToCinplex(cineplexUserChoice);
		
		List<Movie> movieList = new ArrayList<Movie>();
		movieList = cineplexUserChoice.getMovie();	
		
		
		System.out.println("\n");
		
		System.out.println("Enter index to Remove from Cineplex: ");
		movieChoice = sc.nextInt();
		
		Movie m = movieList.get(movieChoice-1);
		
		movieList.remove(m);
		cineplexUserChoice.setMovie(movieList);
		cineplexControl.updateCineplex(cineplexUserChoice);
		
		System.out.println("Movie '" + m.getTitle() + "' successfully removed from Cineplex '" + cineplexUserChoice.getCineplexName() + "'");
	}
	
	
	/**
	 * This method lists movies specific to a cineplex
	 */
	public void listMovieSpecificToCinplex(Cineplex cineUserChoice){
		List<Movie> movieList = new ArrayList<Movie>();
		Scanner sc = new Scanner(System.in);
		
		movieList = cineUserChoice.getMovie();	
		
		System.out.println("*************List of Movies Inside the Cineplex: *************");
		for (int i = 0; i< movieList.size();i++) {
			Movie m = movieList.get(i);
			System.out.format("|"+ (i+1) +". Movie Title: %s",m.getTitle());
			System.out.println("\n");
		}
	}
	
	/**
	 * This method lists show times specific to a cineplex
	 */
	public void listShowTimeSpecificToCinplex(Cineplex cineUserChoice){
		List<ShowTime> showTimeList = new ArrayList<ShowTime>();
		Scanner sc = new Scanner(System.in);
		
		showTimeList = cineUserChoice.getShowTime();	
		
		System.out.println("*************List of Show Times Inside the Cineplex: *************");
		int i=0;
		for (ShowTime st: showTimeList) {
			System.out.format("|"+ (i+1) +". Date: %s   Time: %ss",st.getDate(), st.getTime());
			System.out.println("\n");
			i++;
		}
		System.out.println("\n");
	}
	
	
	
	
	
	
	/*
	 * *****************************************************************************************************************
	 * 												Staff CINEMA MENU 					    		                  *
	 ******************************************************************************************************************/
	
	/**
	 * This method prints the menu for Cinema
	 */
	public void staffMenuCinema() { //done
		int staffChoiceCinema = 0;
		Scanner sc = new Scanner(System.in);
		CineplexController cineplexControl = new CineplexController();
		
		System.out.println("^^^^^^^^^^^^^^CINEMA MENU^^^^^^^^^^^^^");
		System.out.println("|1. List Cinema");
		System.out.println("|2. Create New Cinema");
		System.out.println("|3. Update Cinema");
		System.out.println("|4. Remove Cinema");
		//Need an option to return to main menu
		System.out.println("|5. Go Back to Staff Menu");
		System.out.println("|6. Quit");
		
		System.out.println("Choice (1-6): ");
		staffChoiceCinema = sc.nextInt();
		
		switch(staffChoiceCinema){
				/**List cinemas*/
			case 1: 
				displayCinemaList();
				staffMenuCinema();
				break;
	
				/**Create new cinema*/
			case 2: 
				Cineplex cineplexUserChoice = cineplexControl.listCineplex();
				addCinemaToCineplex(cineplexUserChoice);
				staffMenuCinema();
				break;
	
				/**Update specific cinema in the list*/
			case 3: 
				updateCinemaInCineplex();
				staffMenuCinema();
				break;
	
				/**Remove cinema from the list*/
			case 4: 
				removeCinemaFromCineplex();
				staffMenuCinema();
				break;
				
			case 5:
				s_menu.show();
				break;
			case 6:
				System.out.println("Program Terminating...");
				System.exit(0);
				break;
	
			default:
				break;
		}
		
		s_menu.show();
		
	}
	
	/**
	 * This method displays the cinema list
	 */
	
	public void displayCinemaList(){
		CineplexController cineControl = new CineplexController();
		
		Cineplex cineUserChoice = cineControl.listCineplex();
		
		List<Cinema> cinemaList = cineUserChoice.getCinemas();
		
		int i=0;
		for(Cinema c: cinemaList) {
			System.out.format("|"+ (i+1) +". Cinema Code: %s  Cinema Type: %s",c.getCinemaCode(),c.getCinemaType());
			System.out.println("\n");
			i++;
		}
		
	}
	
	/**
	 * This method updates the cinema in the cineplex
	 */
	public void updateCinemaInCineplex(){
		int choice = 0;
		String userType = null;
		Scanner sc = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);
		
		CineplexController cineplexControl = new CineplexController();
		Cineplex cineplexUserChoice = cineplexControl.listCineplex();
		int cineplexID = cineplexUserChoice.getCineplexID();
		
		List<Cinema> cinemaList = cineplexUserChoice.getCinemas();
		
		
		int i=0;
		for(Cinema c: cinemaList) {
			System.out.format("|"+ (i+1) +". Cinema Type: %s  Cinema Code: %s",c.getCinemaCode(),c.getCinemaType());
			System.out.println("\n");
			i++;
		}
		
		
		System.out.println("Enter index number to update: ");
		choice = sc.nextInt();
		
		Cinema c = cinemaList.get(choice-1);
		
		int a = 0;
		
		do {
			System.out.println("Cinema " + (i + 1) + " type (1 - platinum, 2 - normal): ");
			a = sc.nextInt();
			switch(a){
				case 1:
					userType = "platinum";
					break;
				case 2:
					userType = "normal";
					break;
				default:
					System.out.println("Please re-enter 1 or 2.");
					
					break;
				
			}
		}while(userType.equals(""));
		c.setCinemaType(userType);
		
		cineplexUserChoice.setCinemas(cinemaList);
		cineplexControl.updateCineplex(cineplexUserChoice);

		System.out.println(c.getCinemaCode()+" cinema has been successfully updated in the Cineplex");
	}
	
	/**
	 * This method adds cinema to cineplex
	 * @param cineplexUserChoice which is the cineplex chosen by the user
	 */
	public void addCinemaToCineplex(Cineplex cineplexUserChoice){
		String code = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		int numberToAdd = 0;
		Scanner sc = new Scanner(System.in);
		
		CineplexController cineplexControl = new CineplexController();
		//cineplexUserChoice = cineplexControl.listCineplex();
		int cineplexID = cineplexUserChoice.getCineplexID();
		
		List<Cinema> cinemaList = cineplexUserChoice.getCinemas();
		
		System.out.println("Enter the number of Cinema: ");
		numberToAdd = sc.nextInt();
		
		for(int i = 0; i < numberToAdd; i++){
			sc = new Scanner(System.in);
			String cinemaType = "";
			String cinemaCode = "";
			int choice = 0;
			
			do {
				System.out.println("Cinema " + (i + 1) + " type (1 - platinum, 2 - normal): ");
				choice = sc.nextInt();
				switch(choice){
					case 1:
						cinemaType = "platinum";
						break;
					case 2:
						cinemaType = "normal";
						break;
					default:
						System.out.println("Please re-enter 1 or 2.");
						
						break;
					
				}
			}while(cinemaType.equals(""));
			
			if(cinemaList.size() != 0){
				String index = cinemaList.get(cinemaList.size()-1).getCinemaCode().charAt(2)+"";
				int a = Integer.parseInt(index)+1;
				cinemaCode = code.charAt(cineplexID-1)+"0"+a;
			}
			else{
				cinemaCode = code.charAt(cineplexID-1)+"01";
			}
			cinemaList.add(new Cinema(cinemaType, cinemaCode));
		}
		
		cineplexUserChoice.setCinemas(cinemaList);
		cineplexControl.updateCineplex(cineplexUserChoice);
		
		System.out.println(numberToAdd+" cinema(s) has been successfully added to the Cineplex");
	}
	
	/**
	 * This method removes cinema from cineplex
	 */
	public void removeCinemaFromCineplex(){
		int choice = 0;
		Scanner sc = new Scanner(System.in);
		
		CineplexController cineplexControl = new CineplexController();
		Cineplex cineplexUserChoice = cineplexControl.listCineplex();
		int cineplexID = cineplexUserChoice.getCineplexID();
		
		List<Cinema> cinemaList = cineplexUserChoice.getCinemas();
		
		int i=0;
		for(Cinema c: cinemaList) {
			System.out.format("|"+ (i+1) +". Cinema Code: %s  Cinema Type: %s",c.getCinemaCode(),c.getCinemaType());
			System.out.println("\n");
			i++;
		}
		
		System.out.println("Enter index number to remove: ");
		choice = sc.nextInt();
		
		Cinema c = cinemaList.get(choice-1);
		cinemaList.remove(choice-1);
		
		cineplexUserChoice.setCinemas(cinemaList);
		cineplexControl.updateCineplex(cineplexUserChoice);

		System.out.println(c.getCinemaCode()+" cinema has been successfully removed from Cineplex");
	}
	
	
	/*
	 * *****************************************************************************************************************
	 * 												Staff HOLIDAY MENU 					    		                  *
	 ******************************************************************************************************************/	
	
	/**
	 * This method is the main method for holiday
	 */
	public void holidayMain(){
		Scanner sc = new Scanner(System.in);
		
		System.out.println("^^^^^^^^^^^^^^HOLIDAY^^^^^^^^^^^^^");
		System.out.println("|1. List Public Holiday");
		System.out.println("|2. Create Public Holiday");
		System.out.println("|3. Update Public Holiday");
		System.out.println("|4. Remove Public Holiday");
		System.out.println("|5. Go Back to Staff Menu");
		
		System.out.println("Enter your choice: ");
		int staffChoiceHoliday = sc.nextInt();
		

		switch(staffChoiceHoliday){
			case 1:
				listHoliday();
				holidayMain();
				break;
			case 2:
				addHoliday();
				holidayMain();
				break;
			case 3:
				updateHoliday();
				holidayMain();
				break;
			case 4:
				deleteHoliday();
				holidayMain();
				break;
			case 5:
				s_menu.show();
				break;
			case 6:
				System.out.println("Program Terminating...");
				System.exit(0);
				break;
			default:
				break;
		}
		s_menu.show();
		
	}
	
	
	public static String retrieveList(){
		
		int cineplex;
		String cinemaCode;

		Scanner sc = new Scanner(System.in);

		CineplexController cineController = new CineplexController();
		MovieController movieController = new MovieController();
		
		//Retrieve CineplexList
		List<Cineplex> cineplexList = cineController.retrieveCineplexList();
		
//		String cxHeader = String.format("%-15s %-20s %-20s","Cineplex ID","Cineplex Name","Location");
//		System.out.println(cxHeader);
//		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		
		int i=0;
		for (Cineplex c : cineplexList) {
			System.out.format("|"+ (i+1) +". Cineplex Name: %s  Location: %s",c.getCineplexName(), c.getLocation());
			System.out.println("\n");
			i++;
		}
		
		
//		for (Cineplex c : cineplexList) {
//			System.out.println(String.format("%-15s %-20s %-20s", c.getCineplexID(),c.getCineplexName(), c.getLocation()));
//		}
//	
		System.out.println("\nEnter Index: ");
		cineplex = sc.nextInt();

		//Retrieve CinemaList		
		List <Cinema> cine = cineplexList.get(cineplex-1).getCinemas();

//		System.out.println("CINEMA CODE \t CINEMA TYPE");
//		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//		for (Cinema c : cine){
//			System.out.println(String.format(c.getCinemaCode()+"\t\t "+c.getCinemaType()));
//		}
		
		
		int m=0;
		for (Cinema c : cine){
			System.out.format("|"+ (m+1) +". Cinema Code: %s  Cinema Type: %s",c.getCinemaCode(),c.getCinemaType());
			System.out.println("\n");
			m++;
		}
				
		System.out.println("\nEnter Index: ");
		cinemaCode = sc.next();
		
		//Retrieve MovieList
//		System.out.println("MOVIE CODE \tMOVIE TITLE");
//		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		Cineplex cineUserChoice = cineplexList.get(cineplex-1);
		List<Movie> movieList = movieController.retrieveMovieList(cineUserChoice.getMovie());
		
		
		int n=0;
		for(Movie mm: movieList) {
			System.out.format("|"+ (n+1) +". Movie Title: %s",mm.getTitle());
			System.out.println("\n");
			n++;
		}
		
//		for(Movie m: movieList) {
//			System.out.println(m.getMovieID()+"\t\t"+m.getTitle());
//		}
		return cinemaCode;
	}
	
	
	
	/**
	 * This method prints out Holiday Menu for users to make a choice.
	 */
	// print out
	public void printHolidayMenu(){
		System.out.println("^^^^^^^^^^^^^^HOLIDAY^^^^^^^^^^^^^");
		System.out.println("|1. List Public Holiday");
		System.out.println("|2. Create Public Holiday");
		System.out.println("|3. Update Public Holiday");
		System.out.println("|4. Remove Public Holiday");
		System.out.println("|5. Go Back to Staff Menu");
		
	}
	
	/**
	 * This method lists Holiday object.
	 */
	// case 1
	public void listHoliday() {
		int holID;

		HolidayController hc = new HolidayController();
		List<Holiday> holidayList = hc.retrieveHolidayList();

		
		//System.out.println(String.format("%-5s %-15s %-15s","ID","Holiday Name","Date"));
//		for (Holiday h : holidayList) {
//			System.out.println(String.format("%-5s %-15s %-15s",holID,h.getHolidayName(),h.getDate()));
//			holID++;
//		}
		
		if(!holidayList.isEmpty()){
			int n=0;
			for (Holiday h : holidayList) {
				System.out.format("|"+ (n+1) +". Holiday Name: %s  Date: %s",h.getHolidayName(),h.getDate());
				System.out.println("\n");
				n++;
			}
		} else {
			System.out.println("No Public Holidays has been set!");
		}
		
		
		
	}
	
	/**
	 * This method creates Holiday object.
	 * User inputs holiday name and date.
	 */
	// case 2
	public void addHoliday() {

		String holidayName, holidayDate;
		Date dateF = null;
		Scanner sc = new Scanner(System.in);

		System.out.println("Name of Holiday: ");
		holidayName = sc.nextLine();

		SimpleDateFormat formattedDate = new SimpleDateFormat("dd/MM/yyyy");		

		dateF = validateDate();
		holidayDate = formattedDate.format(dateF);

		Holiday hol = new Holiday(holidayDate, holidayName);
		HolidayController hc = new HolidayController();
		hc.createHoliday(hol);
		System.out.println("Public Holiday has been added successfully!");
	}
	
	/**
	 * This method updates Holiday Name or Holiday Date of Holiday object.
	 */
	// case 3
	public void updateHoliday() {
		listHoliday();

		int holID, holChoice;
		String holidayName, holidayDate;
		Date dateFmt = null;

		Scanner sc = new Scanner(System.in);
		Scanner scStr = new Scanner(System.in);
		
		System.out.println("Select an ID to update:");
		holID = sc.nextInt();

		HolidayController hc = new HolidayController();
		List<Holiday> holidayList = hc.retrieveHolidayList();
		
		System.out.println("UPDATE PUBLIC HOLIDAY");
			System.out.println("Choose to update: ");
			System.out.println("|1. Holiday Name");
			System.out.println("|2. Holiday Date");
			holChoice = sc.nextInt();

		switch (holChoice) {
		case 1:
			System.out.println("Enter New Holiday Name: ");
			holidayName = scStr.nextLine();
			Holiday holName = holidayList.get(holID-1);
			holName.setHolidayName(holidayName);
			hc.updateHoliday(holName);
			System.out.println("Public Holiday Name has been updated successfully!");
			break;
		case 2:
			SimpleDateFormat formattedDate = new SimpleDateFormat("dd/MM/yyyy");		
			
			dateFmt = validateDate();
			holidayDate = formattedDate.format(dateFmt);
			
			Holiday holDate = holidayList.get(holID-1);
			holDate.setDate(holidayDate);
			hc.updateHoliday(holDate);
			System.out.println("Public Holiday Date has been updated successfully!");
			break;
		}			
		listHoliday();
	}
	
	/**
	 * This method deletes Holiday object.
	 */
	// case 4
	public void deleteHoliday() {
		listHoliday();
		int holID;
		Scanner sc = new Scanner(System.in);
		System.out.println("Select an ID to delete");
		holID = sc.nextInt();
		HolidayController hc = new HolidayController();
		List<Holiday> holidayList = hc.retrieveHolidayList();
		Holiday h = holidayList.get(holID-1);
		hc.deleteHoliday(h);
		System.out.println("Public Holiday has been deleted successfully!");
	}	
	
	
	
	/*
	 * *****************************************************************************************************************
	 * 												Staff MOVIE MENU 					    		                  *
	 ******************************************************************************************************************/

	/**
	 * This method displays the menu for movie option selected by the staff.
	 * It displays all the movie operations that can be done by the staff.
	 * This includes displaying all movies, create a new movie, update the movie information, and remove movie (change status to "End of Showing")
	 */
	public void staffMenuMovie() { //done
		int staffChoiceMovie = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("^^^^^^^^^^^^^^MOVIE MENU^^^^^^^^^^^^^");
		System.out.println("|1. List Movies");
		System.out.println("|2. Create New Movie");
		System.out.println("|3. Update Movie");
		System.out.println("|4. Remove Movie");
		//Need an option to return to main menu
		System.out.println("|5. Go Back to Staff menu");
		System.out.println("|6. Quit");
		
		System.out.println("Choice (1-5): ");
		staffChoiceMovie = sc.nextInt();
		
		switch(staffChoiceMovie){
			case 1: //List movies
				displayMovieList();
				staffMenuMovie();
				break;
	
			case 2: //Create new movie
				addMovieToList();
				staffMenuMovie();
				break;
	
			case 3: //update specific movie in movie list
				updateMList();
				staffMenuMovie();
				break;
	
			case 4: //remove a movie from list
				removeMList();
				staffMenuMovie();
				break;
			case 5:
				s_menu.show();
				break;
			case 6:
				System.out.println("Program Terminating...");
				System.exit(0);
				break;
	
			default:
				break;
		}
		s_menu.show();
		
	}
	
	
	/**
	 * This method will display all the Movies in the Movie.json file by calling the retrieveMovieList() method in the MovieController. 
	 * Overall ratings of a movie will only be displayed if there are more than 1 individual rating .
	 */
	public void displayMovieList() {
		StaffController staffControl = new StaffController();
		MovieController movieController = new MovieController();
		List<Movie> movieList = movieController.retrieveMovieList();

		for(Movie m: movieList) {
			System.out.println("Movie Title   : " + m.getTitle());
			System.out.println("Movie type    : " + m.getMovieType());
			System.out.println("Movie rating  : " + m.getMovieRating());
			System.out.println("Showing Status: " + m.getMovieStatus());
			System.out.println("Duration      : " + m.getDuration());
			System.out.println("Synopsis      : " + staffControl.wordWrap(m.getSynopsis()));
			System.out.println("Director      : " + m.getDirector());
			System.out.println("Cast          : " + m.getCast());

			List<Review> reviewList =  m.getReviews();
			//overall ratings will only be displayed when there are more than 1 individual rating
			if(reviewList.size()>1)
				System.out.println("Overall Reviewer Ratings: " + m.getRatings());
			
			if(reviewList.size()>0) {
				System.out.println("****************Past reviewers rating**************** ");

				for(Review r: reviewList) {
					System.out.println("Past Rating   : " + r.getRating());
					System.out.println("Past feedback : " + staffControl.wordWrap(r.getFeedback()));
				}
			}
			System.out.println("\n\n");
		}
	}
	
	
	/**
	 * This method creates a new movie object by prompting the staff for input for each movie fields except for overall ratings and reviews. 
	 * It then adds the movie object into the Movie.json file by calling the createMovie() method in the MovieController.
	 */
	public void addMovieToList() {
		int statusNum, movieID;
		String title, movieRating, duration, synopsis, director, cast, movieType, showingStatus;

		Scanner scn = new Scanner(System.in);

		System.out.println("Enter movie title: ");
		title = scn.nextLine();

		scn = new Scanner(System.in);
		//e.g 2D, 3D
		System.out.println("Enter movie type: ");
		movieType = scn.next();

		scn = new Scanner(System.in);
		//e.g PG, NC16
		System.out.println("Enter movie rating: ");
		movieRating = scn.next();

		scn = new Scanner(System.in);		
		System.out.println("Enter showing status (1.Coming Soon 2.Preview 3.Now Showing): ");
		statusNum = scn.nextInt();

		if(statusNum == 1) showingStatus = "Coming Soon";
		else if(statusNum == 2) showingStatus = "Preview";
		else showingStatus = "Now Showing";

		scn = new Scanner(System.in);
		System.out.println("Enter movie duration: ");
		duration = scn.nextLine();

		scn = new Scanner(System.in);
		System.out.println("Enter movie synopsis: ");
		synopsis = scn.nextLine();

		scn = new Scanner(System.in);
		System.out.println("Enter movie director: ");
		director = scn.nextLine();

		scn = new Scanner(System.in);
		System.out.println("Enter movie cast: ");
		cast = scn.nextLine();

		MovieController movieController = new MovieController();
		List<Movie> movieList = movieController.retrieveMovieList();
		if(movieList==null){
			movieID = 0;
		}else{
			movieID = movieList.size() + 1;
		}
		Movie m = new Movie(movieID, 0.0, movieRating, duration, title, synopsis, director, cast, movieType, showingStatus);

		movieController.createMovie(m);
		System.out.println("\nMovie successfully added into list! \n");
		
	}
		
	/**
	 * This method displays the movies and ask for staff input to select the movie to be updated. 
	 * Each movie field is displayed and the staff will choose which fields he/she wants to update. 
	 * The updated movie object will be passed to updateMovie() method in the MovieController.  
	 */
	public static void updateMList() {
		int movieOption, updateOption, statusNum;
		String title, movieType, movieRating, showingStatus, duration, synopsis, director, cast;

		MovieController movieController = new MovieController();
		List<Movie> movieList = movieController.retrieveMovieList();

		StaffController staffControl = new StaffController();
		
		Scanner scn = new Scanner(System.in);
		//List all the movies in list and ask user to input the movieID to start update
		System.out.println("========== Movies ==========");
		for(Movie m: movieList) {
			System.out.format("|%s. Movie Title: %s",m.getMovieID(),m.getTitle());
			System.out.println("\n");
		}
		System.out.println("Select movie you wish to update: ");
		movieOption = scn.nextInt();

		for(Movie m: movieList)
		{
			if(movieOption == m.getMovieID())
			{

				do{
					System.out.println("========== Movie Info to Update==========");
					System.out.println("1. Title");
					System.out.println("2. Movie type");
					System.out.println("3. Movie rating");
					System.out.println("4. Showing status");
					System.out.println("5. Duration");
					System.out.println("6. Synopsis");
					System.out.println("7. Director");
					System.out.println("8. Cast");
					System.out.println("9. Go Back");
					System.out.println("10. Quit");
					System.out.println("Enter the option that you wish to edit: ");
					updateOption = scn.nextInt();

					switch(updateOption) {

					case 1:
						scn = new Scanner(System.in);
						System.out.println("Enter new title: ");
						title = scn.nextLine();
						m.setTitle(title);
						//System.out.println("Update successful! ");
						break;

					case 2:
						scn = new Scanner(System.in);
						System.out.println("Enter new movie type (2D, 3D): ");
						movieType = scn.next();
						m.setMovieType(movieType);
						//System.out.println("Update successful! ");
						break;

					case 3:
						scn = new Scanner(System.in);
						System.out.println("Enter new movie rating (PG, NC16, M18, R21): ");
						movieRating = scn.next();
						m.setMovieRating(movieRating);
						//System.out.println("Update successful! ");
						break;


					case 4:
						scn = new Scanner(System.in);
						System.out.println("Enter new showing status (1.Coming soon 2.Preview 3.Now showing 4.End of Showing): ");
						statusNum = scn.nextInt();

						if(statusNum == 1)
							showingStatus = "Coming soon";
						else if(statusNum == 2)
							showingStatus = "Preview";
						else if(statusNum == 3)
							showingStatus = "Now Showing";
						else
							showingStatus = "End of Showing";

						m.setMovieStatus(showingStatus);
						//System.out.println("Update successful! ");
						break;

					case 5:
						scn = new Scanner(System.in);
						System.out.println("Enter new movie duration: ");
						duration = scn.next();
						m.setDuration(duration);
						//System.out.println("Update successful! ");
						break;

					case 6:
						scn = new Scanner(System.in);
						System.out.println("Enter new synopsis: ");
						synopsis = scn.nextLine();
						m.setSynopsis(synopsis);
						//System.out.println("Update successful! ");
						break;

					case 7:
						scn = new Scanner(System.in);
						System.out.println("Enter new director: ");
						director = scn.nextLine();
						m.setDirector(director);
						//System.out.println("Update successful! ");
						break;

					case 8:
						scn = new Scanner(System.in);
						System.out.println("Enter new cast: ");
						cast = scn.nextLine();
						m.setCast(cast);
						//System.out.println("Update successful! ");
						break;

					case 9:
						staffControl.staffMenuMovie();
						break;
						
					case 10:
						System.out.println("Programm terminating...");
						System.exit(0);
						break;
					default:
						break;
					}
					
					if(movieController.updateMovie(m))
						System.out.println("Movie updated successfully! ");
					else
						System.out.println("Update failed! ");
					
				}while(updateOption < 9);

				
			}
		}

	}
	
	/**
	 * This method will ask for staff input of the movie. 
	 * The showing status field of the selected movie will be changed to "End of Showing", 
	 * by calling the removeMovie() method in MovieController.
	 */
	public void removeMList() {
		int movieOption;
		MovieController movieController = new MovieController();
		List<Movie> movieList = movieController.retrieveMovieList();

		Scanner scn = new Scanner(System.in);
		//List all the movies in list and ask user to input the movieID to remove
		System.out.println("========== Movies ==========");
		for(Movie m: movieList)
		{
			if(m.getMovieStatus().compareTo("End of Showing") != 0)
				System.out.println(m.getMovieID() + ". " + m.getTitle());
		}
		System.out.println("Select Movie ID you wish to remove(changing showing status to 'End of Showing'): ");
		movieOption = scn.nextInt();

		if(movieController.removeMovie(movieOption))
			System.out.println("Movie removed from movie list! ");
		else
			System.out.println("Deletion failed! ");
	}
	
	/*
	 * *****************************************************************************************************************
	 * 												Staff SHOWTIME MENU 					    		                  *
	 ******************************************************************************************************************/
	
	/**
	 * This method is the main method for showTime
	 */
	public void showtimeMain(){
		Scanner sc = new Scanner(System.in);
		
		System.out.println("^^^^^^^^^^^^^^SHOW TIMES^^^^^^^^^^^^^");
		System.out.println("|1. List Show Times");
		System.out.println("|2. Create Show Times");
		System.out.println("|3. Update Show Times");
		System.out.println("|4. Go Back to Staff Menu");
		System.out.println("|5. Quit");
		
		
		System.out.println("Enter your choice: ");
		int staffChoiceST = sc.nextInt();
		
		
		switch(staffChoiceST){
			case 1:
				listbyCinema();
				showtimeMain();
				break;
			case 2:
				addShowTime();
				showtimeMain();
				break;
			case 3:
				updateShowTime();
				showtimeMain();
				break;
			case 4:
				s_menu.show();
				break;
			case 5: 
				System.out.println("Program Terminating...");
				System.exit(0);
				break;
			}

		s_menu.show();
		
	}	
	

	
	
	// case 1 
	public static void listShowTime(){
		int movie;
		String cinemaCode;
		
		Scanner sc = new Scanner(System.in);
		
		cinemaCode = retrieveList();
		
		System.out.println("\nEnter Movie Code: ");
		movie = sc.nextInt();

		ShowTimeController stc = new ShowTimeController();
		List<ShowTime> showTimeList = stc.retrieveShowTimeList(cinemaCode, movie);

		int stID = 1;
		String stHeader = String.format("%-15s %-15s %-15s %-12s %-15s %-10s", "Show Time Index","Cinema Code","Date","Day","Time","MovieID");
		System.out.println(stHeader);
		System.out.println("---------------"+"    -----------"+"     ----------"+"      ---------"+"    ---------"+"       ---------");
		
		for (ShowTime st : showTimeList) {
				System.out.println(String.format("%-15s %-15s %-15s %-12s %-15s %-10s", stID, st.getCinemaCode(), st.getDate(),st.getDay(), 
						st.getTime(), st.getMovieID()));
				stID++;
		}
		
	}
	
	/**
	 * This method prints the list of showtime by cineplex ID, cinema code and movie ID.
	 */
	// case 1
	//List Showtime by Movie
	public static void listbyMovie(){
		int movie, stID;

		Scanner sc = new Scanner(System.in);

		StaffController staffControl = new StaffController();
		
		//Controllers
		CineplexController cineController = new CineplexController();
		MovieController movieController = new MovieController();

		List<Cineplex> cineplexList = cineController.retrieveCineplexList();
		Cineplex cineplex = retrieveCineplex();
		Cinema cinema = retrieveCinemaCode(cineplex);

		//Retrieve MovieList
		List<Movie> movieList = movieController.retrieveMovieList(cineplex.getMovie());	
//		System.out.println("MOVIE CODE \tMOVIE TITLE");
//		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		//movieID = 1;
		for(Movie m: movieList) {
			System.out.format("|%s  Movie Title: %s",m.getMovieID(),m.getTitle());
			System.out.println("\n");
		}
		System.out.println("\nEnter Movie Code: ");
		movie = sc.nextInt();

		ShowTimeController stc = new ShowTimeController();
		List<ShowTime> showTimeList = stc.retrieveShowTimeList(cinema.getCinemaCode(), movie);

		if(!showTimeList.isEmpty()){
			String stHeader = String.format("%-15s %-15s %-15s %-12s %-15s", "Show Time Index","Cinema Code","Date","Day","Time","Movie Status");
			System.out.println(stHeader);
			System.out.println(String.format("%-15s %-15s %-15s %-12s %-15s","---------------","-----------","----------","--------","------","---------"));

			for (ShowTime st : showTimeList) {
				System.out.println(String.format("%-15s %-15s %-15s %-12s %-15s", st.getShowTimeID(), st.getCinemaCode(), st.getDate(),st.getDay(), 
						st.getTime()));
			}
		} else {
			System.out.println("No show time to display!");
			staffControl.showtimeMain();
		}
		
		
	}

	/**
	 * This method prints the list of showtime by cineplex ID and cinema code.
	 */
	//List ShowTime by Cinema
	public static void listbyCinema(){
		int stID;

		Scanner sc = new Scanner(System.in);

		//Controllers
		CineplexController cineController = new CineplexController();
		MovieController movieController = new MovieController();

		List<Cineplex> cineplexList = cineController.retrieveCineplexList();
		Cineplex cineplex = retrieveCineplex();
		Cinema cinema = retrieveCinemaCode(cineplex);
		List<Movie> movieList = movieController.retrieveMovieList(cineplex.getMovie());	

		ShowTimeController stc = new ShowTimeController();
		List<ShowTime> showTimeList = stc.retrieveShowTimeList(cinema.getCinemaCode());

		
		if(!showTimeList.isEmpty()) {
			String stHeader = String.format("%-15s %-20s %-15s %-12s %-10s", "Show Time ID","Movie Title","Date","Day","Time");
			System.out.println(stHeader);
			System.out.println(String.format("%-15s %-20s %-15s %-12s %-10s", "---------------","------------","----------","--------","------"));

			for (ShowTime st : showTimeList) {
				for(Movie m: movieList){
					if (st.getMovieID()==m.getMovieID()){
						System.out.println(String.format("%-15s %-20s %-15s %-12s %-10s", st.getShowTimeID(), m.getTitle(), st.getDate(),st.getDay(), 
								st.getTime()));
					}
				}
			}
		} else {
			System.out.println("No show time available for this cinema");
		}
		
		
	}

	/**
	 * This method creates ShowTime object.
	 */
	//case 2
	public static void addShowTime() {
		
		int movie, showTimeID, cineplexID;
		String cinemaCode,day, dateStr, timeStr;
		Date dateFmt, timeFmt;

		Scanner sc = new Scanner(System.in);

		// Controllers
		CineplexController cineplexController = new CineplexController();
		MovieController movieController = new MovieController();
		ShowTimeController showTimeController = new ShowTimeController();

		List<Cineplex> cineplexList = cineplexController.retrieveCineplexList();	
		Cineplex cineplex = retrieveCineplex();
		Cinema cinema = retrieveCinemaCode(cineplex);

		//*Retrieve Movie Status = Now Showing
		List<Movie> movieList = movieController.retrieveMovieList(cineplex.getMovie());
		int counter = 0;
		for(Movie m: movieList) {
			if(m.getMovieStatus().equals("Now Showing")){
				counter++;
			}
		}
		if(counter != 0){
//			System.out.println("MOVIE CODE \tMOVIE TITLE");
//			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//	
//			for(Movie m: movieList) {
//				if(m.getMovieStatus().equals("Now Showing")){
//					System.out.println(m.getMovieID()+"\t\t"+m.getTitle());
//				}
//			}
			
			int i=0;
			for(Movie m: movieList) {
				if(m.getMovieStatus().equals("Now Showing")){
					System.out.format("|%s.  Movie Title: %s",m.getMovieID(),m.getTitle());
					System.out.println("\n");
					i++;
				}
				
			}
			
			
	
			System.out.println("\nEnter Movie ID: ");
			movie = sc.nextInt();
	
			// Get input date and Time
			SimpleDateFormat formattedDate = new SimpleDateFormat("dd/MM/yyyy");
			DateFormat formattedTime = new SimpleDateFormat("HH:mm");
			SimpleDateFormat formattedDay = new SimpleDateFormat("EEEE");
	
			dateFmt = validateDate();
			dateStr = formattedDate.format(dateFmt);
			day = formattedDay.format(dateFmt);
			timeFmt = validateTime();
			timeStr = formattedTime.format(timeFmt);
	
			// Create ShowTime
			List<ShowTime> showTimeList = showTimeController.retrieveShowTimeList();
	
			ShowTime st = new ShowTime();
			st.setCinemaCode(cinema.getCinemaCode());
			if (showTimeList.size() == 0) {
				showTimeID = 1;
			} else {
				ShowTime showTime = showTimeList.get(showTimeList.size() - 1);
				showTimeID = showTime.getShowTimeID() + 1;
			}
			st.setShowTimeID(showTimeID);
			st.setTime(timeStr);
			st.setDay(day);
			st.setDate(dateStr);
			st.setMovieID(movie);
			showTimeController.createShowTime(st);
			
			List<ShowTime> stList = cineplex.getShowTime();
			stList.add(st);
			cineplex.setShowTime(stList);
			cineplexController.updateCineplex(cineplex);
			System.out.println("Show Time has been added successfully!");
		}
		else{
			System.out.println("Currently Cineplex does not have movie.");
		}
	}
	
	/**
	 * This method allows user to choose between updating the showtime's date or time.
	 */
	// case 3
	public static void updateShowTime() {
		listbyMovie();

		int stChoice, updateChoice;
		StaffController staffControl = new StaffController();
		Scanner sc = new Scanner(System.in);

		System.out.println("\nSelect a Show Time ID to update:");
		stChoice = sc.nextInt();

		System.out.println("\nUPDATE SHOW TIME");
		System.out.println("\nChoose to update:");
		System.out.println("|1. Date");
		System.out.println("|2. Time");
		System.out.println("|3. Go Back to Show Time Menu");
		System.out.println("|4. Quit");
		System.out.println("\nEnter your choice: ");
		updateChoice = sc.nextInt();

		switch (updateChoice) {
			case 1:
				updateSTDate(stChoice);
				System.out.println("Show Time Date has been updated successfully!");
				staffControl.showtimeMain();
				break;
			case 2:
				updateSTTime(stChoice);
				System.out.println("Show Time has been updated succesfully!");
				staffControl.showtimeMain();
				break;
			case 3:
				staffControl.showtimeMain();
				break;
			case 4:
				System.out.println("Program Terminating...");
				System.exit(0);
				break;
			default:
				System.out.println("Default");
				break;
		}

		
	}
	
	
	/**
	 * This method updates the Showtime Object of Date.
	 */
	public static void updateSTDate(int selected) {
		
		String dateStr, day;
		Date dateF;
		
		Scanner sc = new Scanner(System.in);
		
		SimpleDateFormat formattedDate = new SimpleDateFormat("dd/MM/yyyy");		
		DateFormat formattedDay = new SimpleDateFormat("EEEE");

		ShowTimeController stc = new ShowTimeController();
		List<ShowTime> showTimeList = stc.retrieveShowTimeList();

		dateF = validateDate();
		dateStr = formattedDate.format(dateF);
		day = formattedDay.format(dateF);

		ShowTime stDate = showTimeList.get(selected - 1);
		stDate.setDate(dateStr);
		stDate.setDay(day);
		stc.updateShowTimeList(stDate);
	}
	
	/**
	 * This method updates the Showtime Object of Time.
	 */
	public static void updateSTTime(int selected){
		String timeStr;
		Date timeF;
		
		Scanner sc = new Scanner(System.in);

		DateFormat formattedTime = new SimpleDateFormat("HH:mm");

		ShowTimeController stc = new ShowTimeController();
		List<ShowTime> showTimeList = stc.retrieveShowTimeList();

		timeF = validateTime();
		timeStr = formattedTime.format(timeF);
		ShowTime stTime = null;
		for(ShowTime s : showTimeList){
			if(s.getShowTimeID() == selected){
				stTime = s;
			}
		}
		
		stTime.setTime(timeStr);
		stc.updateShowTimeList(stTime);
	}
	
	/**
	 * This method validates for the correct date input.
	 * Input date of showtime should be more than current date and
	 * within the range of days and months.
	 */
	//Check for valid date
	public static Date validateDate() {
		
		String date;
		boolean dateIncorrect = true;
		
		Scanner sc = new Scanner(System.in);

		Date currDate = new Date();
		Date dateFormatted = null;
		Date currDateFormatted = null;
		
		SimpleDateFormat formattedDate = new SimpleDateFormat("dd/MM/yyyy");
		formattedDate.setLenient(false);			

		// Formatting of Date
		do {
			try {
				System.out.println("\nPlease enter the date (DD/MM/YYYY): ");
				date = sc.next();
				dateFormatted = formattedDate.parse(date);
				currDateFormatted = formattedDate.parse(formattedDate.format(currDate));
				if (dateFormatted.after(currDateFormatted)) {
					dateIncorrect = false;
				}
				else if (dateFormatted.before(currDateFormatted)){
					System.out.println("Invalid Date!");
				}
			} catch (ParseException e) {
				System.out.println("Invalid!");
			}
		} while (dateIncorrect);
		return dateFormatted;
	}

	/**
	 * This method validates for the correct time input.
	 * Input time of showtime should be within the hours and minutes format.
	 */
	// Check for valid time
	public static Date validateTime() {
			String time;
			Date timeF = null;
			boolean timeIncorrect = true;

			Scanner sc = new Scanner(System.in);

			DateFormat formattedTime = new SimpleDateFormat("HH:mm");
			formattedTime.setLenient(false);
			do {
				System.out.print("\nEnter Time of the Show Time (HH:mm): ");
				time = sc.next();
				try {
					timeF = formattedTime.parse(time);
					timeIncorrect = false;
				} catch (ParseException e) {
					System.out.println("Invalid Time!");
				}

			} while (timeIncorrect);

			return timeF;
		} 
	
	/**
	 * This method prints the list of Cineplex.
	 * @return The cineplex ID of cineplex.
	 */
	public static Cineplex retrieveCineplex(){
		int index;
		
		Scanner sc = new Scanner(System.in);
		
		CineplexController cineplexController = new CineplexController();


		List<Cineplex> cineplexList = cineplexController.retrieveCineplexList();

//		String cxHeader = String.format("%-15s %-20s %-20s","Cineplex ID","Cineplex Name","Location");
//		System.out.println(cxHeader);
//		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//
//		for (int i =0; i< cineplexList.size();i++) {
//			Cineplex c = cineplexList.get(i);
//			System.out.println((i+1)+") "+String.format("%-15s %-20s %-20s", c.getCineplexID(),c.getCineplexName(), c.getLocation()));
//		}
		
		
		int i=0;
		for(Cineplex c: cineplexList) {
			System.out.format("|"+ (i+1) +". Cineplex Name: %s  Location: %s",c.getCineplexName(),c.getLocation());
			System.out.println("\n");
			i++;
		}

		System.out.println("\nEnter index: ");
		index = sc.nextInt();
		

		return cineplexList.get(index-1);
	}

	/**
	 * This method prints the list of cinema by cineplex ID.
	 * @return The cinema code of cinema.
	 */
	//2) Get Cinema Code
	public static Cinema retrieveCinemaCode(Cineplex cineplex){

		int index;

		Scanner sc = new Scanner(System.in);


		List<Cinema> cinema = cineplex.getCinemas();

//		System.out.println("CINEMA CODE \t CINEMA TYPE");
//		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//		for (int i = 0; i < cinema.size(); i++){
//			Cinema c = cinema.get(i);
//			System.out.println((i+1)+") "+String.format(c.getCinemaCode()+"\t\t "+c.getCinemaType()));
//		}

		
		int i=0;
		for(Cinema c: cinema) {
			System.out.format("|"+ (i+1) +". Cinema Code: %s  Cinema Type: %s",c.getCinemaCode(),c.getCinemaType());
			System.out.println("\n");
			i++;
		}
		
		System.out.println("\nEnter index: ");
		index = sc.nextInt();

		return cinema.get(index-1);
	}
	
	
	/*
	 * *****************************************************************************************************************
	 * 												Staff PRICE MENU					    		                  *
	 ******************************************************************************************************************/

	/**
	 * This method is the main method for price chart
	 */
	public void pricechartMain(){
		Scanner sc = new Scanner(System.in);
		
		System.out.println("^^^^^^^^^^^^^^PRICE CHART^^^^^^^^^^^^^");
		System.out.println("|1. List Price Chart");
		System.out.println("|2. Update Price Chart");
		System.out.println("|3. Go Back to Staff Menu");
		System.out.println("|4. Quit");
		
		System.out.println("Enter your choice: ");
		int staffChoiceST = sc.nextInt();
		
		switch(staffChoiceST){
			case 1:
				listPriceChart();
				pricechartMain();
				break;
			case 2:
				updatePriceChart();
				pricechartMain();
				break;
			case 3:
				s_menu.show();
				break;
			case 4:
				System.out.println("Program Terminating...");
				System.exit(0);
				break;
			default:
				break;
		}
		s_menu.show();
		
	}
	
	/**
	 * This method prints the menu for price chart
	 */
	public void staffMenuPriceChart(int pcChoice){
		
	}
	
	/**
	 * This method prints out Price Chart Menu for users to make a choice.
	 */
	public void printPriceChartMenu(){
		
	}
	
	/**
	 * This method lists the age group for users to select a category
	 * @return The specific category of age group.
	 */
	//Get the category: Children/Student/Adult/Senior Citizen
	public static String ageGrpMenu(){
		int ageChoice;
		String ageGrp = null;
		List<PriceChart> pList = null;
		Scanner sc = new Scanner(System.in);
		System.out.println("Select an age group: ");
		System.out.println("|1. Student");
		System.out.println("|2. Adult");
		System.out.println("|3. Platinum");
		ageChoice = sc.nextInt();
		
		switch(ageChoice){
		case 1:
			ageGrp = "Student";
			break;
		case 2:
			ageGrp = "Adult";
			break;
		case 3:
			ageGrp = "Standard";
			break;
		}
		return ageGrp;
	}
	
	/**
	 * This method updates the PriceChart object.
	 */
	//Update price accordingly
	public static void updatePriceChart(){

		int priceID;
		double newPrice;
		String ageGrp = null;
		List<PriceChart> pList = null;
		Scanner sc = new Scanner(System.in);		

		PriceChartController pcController = new PriceChartController();

		ageGrp = ageGrpMenu();

		switch(ageGrp){
		case "Student":
			pList = filter(ageGrp);
			break;
		case "Adult":
			pList = filter(ageGrp);
			break;
		case "Standard":
			pList = filter(ageGrp);
			break;
		}

		System.out.println("Select an ID to update: ");
		priceID = sc.nextInt();

		System.out.println("Enter the new ticket price: ");
		newPrice = sc.nextDouble();

		PriceChart pc = pList.get(priceID-1);
		pc.setPrice(newPrice);
		pcController.updatePriceChart(pc);

		System.out.println("Price List has been updated successfully!");

		filter(ageGrp);
	}

	/**
	 * This method lists PriceChart object according to the age group.
	 */
	public static void listPriceChart() {
		String ageGrp;
		List<PriceChart> pList = null;
		ageGrp = ageGrpMenu();
		switch(ageGrp){
			case "Student":
				pList = filter(ageGrp);
				break;
			case "Adult":
				pList = filter(ageGrp);
				break;
			case "Standard":
				pList = filter(ageGrp);
				break;
		}
	}

	/**
	 * This method lists PriceChart object for Adult age group.
	 * Prints out the details of the PriceChart object
	 * @param ageGrp Specifies Adult as the age group
	 */
	public static List<PriceChart> filter(String ageGrp) {

		int movieTChoice, i;
		String cineType = null, movType = null;

		Scanner sc = new Scanner(System.in);
		
		if (ageGrp == "Standard") {
			cineType = "Platinum";
			movType = "Any";
		}
		else {
			cineType = "Normal";
			System.out.println("Select a movie type: ");
			System.out.println("|1. 2D");
			System.out.println("|2. 3D");
			movieTChoice = sc.nextInt();
			switch (movieTChoice) {
			case 1:
				movType = "2D";
				break;
			case 2:
				movType = "3D";
				break;
			}
		}
	
		PriceChartController pcController = new PriceChartController();
		List<PriceChart> priceChartList = pcController.retrievePriceChartList(ageGrp, cineType, movType);

		i = 1;
		String header = String.format("%-5s %-12s %-10s %-10s %15s","ID","Day","Price","Cinema Type","Movie Type");
		System.out.println(header);
		for (PriceChart p : priceChartList) {
			String toPrint = String.format("%-5s %-12s %-10s %-10s %10s", i, p.getDay(), p.getPrice(), p.getCinemaType(), p.getMovieType());
			System.out.println(toPrint);
			i++;
		}
		
		return priceChartList;

	}

	/**
	 * 
	 * END OF STAFF PROGRAM
	 */
>>>>>>> nic

}
