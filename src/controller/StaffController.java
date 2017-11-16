package controller;

import app.MoblimaApp;
import app.StaffMenu;
import entity.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

        System.out.println("==== Cineplex List ====");
        System.out.println("ID  Name      Location");
        int i = 0;
        for (Cineplex c : cineplexList) {
            System.out.println((i + 1) + "\t" + c.getCineplexName() + "\t\t\t" + c.getLocation());
            i++;
        }


        System.out.println("\nEnter Index: ");
        cineplex = sc.nextInt();


        List<Cinema> cine = cineplexList.get(cineplex - 1).getCinemas();

        System.out.println("==== Cinema List ====");
        System.out.println("ID  Code    Type");
        int m = 0;
        for (Cinema c : cine) {
            System.out.println((m + 1) + "\t" + c.getCinemaCode() +"    " + c.getCinemaType());
            m++;
        }

        System.out.println("\nEnter Index: ");
        cinemaCode = sc.next();


        Cineplex cineUserChoice = cineplexList.get(cineplex - 1);
        List<Movie> movieList = movieController.retrieveMovieList(cineUserChoice.getMovie());

        System.out.println("==== Movie List ====");
        System.out.println("ID  Title");
        int n = 0;
        for (Movie mm : movieList) {
            System.out.println((n + 1) + "\t" + mm.getTitle());
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
        System.out.println("ID  Title");
        for (Movie m : movieList) {
            System.out.println(m.getMovieID()+"\t"+ m.getTitle());
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

        System.out.println("ID  Title");
        for (Movie m : movieList) {
            System.out.println(m.getMovieID() +"\t"+ m.getTitle());
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
            System.out.println("ID  Title");
            for (Movie m : movieList) {
                if (m.getMovieStatus().equals("Now Showing")) {
                    System.out.println(m.getMovieID()+"\t" + m.getTitle());
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

        System.out.print("\nSelect a Show Time ID to update:");
        stChoice = sc.nextInt();

        System.out.println("\n==== Update Showtime ====");
        System.out.println("\nChoose to update:");
        System.out.println("1. Date");
        System.out.println("2. Time");
        System.out.println("3. Go Back to Show Time Menu");
        System.out.println("4. Quit");
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

        System.out.println("==== Cineplex List ====");
        System.out.println("ID  Name    Location");
        int i = 0;
        for (Cineplex c : cineplexList) {
            System.out.println((i + 1) + "\t" + c.getCineplexName() +"\t"+ c.getLocation());
            i++;
        }

        System.out.println("\nEnter ID: ");
        index = sc.nextInt();


        return cineplexList.get(index - 1);
    }


    public static Cinema retrieveCinemaCode(Cineplex cineplex) {

        int index;

        Scanner sc = new Scanner(System.in);


        List<Cinema> cinema = cineplex.getCinemas();

        System.out.println("==== Cinema List ====");
        System.out.println("ID  Code    Type");
        int i = 0;
        for (Cinema c : cinema) {
            System.out.println((i + 1) + "\t" +c.getCinemaCode() +"     " +c.getCinemaType());
            i++;
        }

        System.out.println("\nEnter index: ");
        index = sc.nextInt();

        return cinema.get(index - 1);
    }

    public boolean authenticate() {
    		System.out.println("===== Staff Login =====");
        System.out.print("Username: ");
        String username = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();
        if (username.equals("admin") && password.equals("admin")) {
            System.out.println("Logged in.\n");
            return true;
        } else
            return false;
    }


    public void staffMenuCineplex() {
        int choice;

        do {
            System.out.println("====================== CINEPLEX =======================");
            System.out.println("|1. List Cineplex                                     |");
            System.out.println("|2. Add Cineplex                                      |");
            System.out.println("|3. Show Movies in Cineplex                           |");
            System.out.println("|4. Add Movies to Cineplex                            |");
            System.out.println("|5. Remove Movies from Cineplex                       |");
            System.out.println("|6. Back                                              |");
            System.out.println("|7. Quit                                              |");
            System.out.println("=======================================================");
            System.out.print("Please input your choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    displayCineplexList();
                    break;
                case 2:
                    addCineplex();
                    break;
                case 3:
                    StaffController staffControl = new StaffController();
                    CineplexController cineplexControl = new CineplexController();
                    Cineplex cineUserChoice = cineplexControl.listCineplex();
                    staffControl.listMovieSpecificToCineplex(cineUserChoice);
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
        System.out.println("==== Cineplex List ====");
        System.out.println("ID  Name    Location");
        int i = 0;
        for (Cineplex c : cineplexList) {
            System.out.println((i + 1) + "\t" + c.getCineplexName() +"\t\t"+c.getLocation());
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
            System.out.println("==== List of Movies to be added ====");
            System.out.println("ID      Movie Title                 ");
            for (Movie mov : movieListView) {
                if (mov.getMovieStatus().compareTo("Now Showing") == 0) {
                    System.out.println(mov.getMovieID()+ "\t\t" + mov.getTitle());
                    count++;
                }
            }
        }

        if (count != movieList.size()) {
            boolean movieInside;
            do {
                movieInside = false;
                System.out.print("Enter Movie ID to add to Cineplex: ");
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


        System.out.print("Enter ID to remove from Cineplex: ");
        movieChoice = sc.nextInt();

        Movie m = movieList.get(movieChoice - 1);

        movieList.remove(m);
        cineplexUserChoice.setMovie(movieList);
        cineplexControl.updateCineplex(cineplexUserChoice);

        System.out.println("Movie '" + m.getTitle() + "' successfully removed from " + cineplexUserChoice.getCineplexName());
    }


    public void listMovieSpecificToCineplex(Cineplex cineUserChoice) {
        List<Movie> movieList = new ArrayList<Movie>();
        Scanner sc = new Scanner(System.in);

        movieList = cineUserChoice.getMovie();

        System.out.println("==== List of Movies Available: ====");
        System.out.println("ID      Movie Title                ");
        for (int i = 0; i < movieList.size(); i++) {
            Movie m = movieList.get(i);
            System.out.println((i + 1) + "\t\t" + m.getTitle());
        }
    }


    public void staffMenuCinema() {
        int staffChoiceCinema = 0;
        Scanner sc = new Scanner(System.in);
        CineplexController cineplexControl = new CineplexController();

        System.out.println("======================= CINEMA ========================");
        System.out.println("|1. List Cinema                                       |");
        System.out.println("|2. Create New Cinema                                 |");
        System.out.println("|3. Update Cinema                                     |");
        System.out.println("|4. Remove Cinema                                     |");
        System.out.println("|5. Back                                              |");
        System.out.println("|6. Quit                                              |");
        System.out.println("=======================================================");
        System.out.print("Please input your choice: ");
        staffChoiceCinema = sc.nextInt();
        System.out.println();
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
        System.out.println("==== List of Cinemas ====");
        System.out.println("Code      Type");
        for (Cinema c : cinemaList) {
            System.out.println(c.getCinemaCode()+ "\t\t" + c.getCinemaType());
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

        System.out.println("==== List of Cinemas ====");
        System.out.println("ID  Code    Type");
        int i = 0;
        for (Cinema c : cinemaList) {
            System.out.println((i + 1) + "\t" + c.getCinemaCode() + "\t\t" + c.getCinemaType());
            i++;
        }

        System.out.print("Enter ID to update: ");
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
        System.out.println("==== List of Cinemas ====");
        System.out.println("ID  Code    Type");
        int i = 0;
        for (Cinema c : cinemaList) {
            System.out.println((i + 1) + "\t" + c.getCinemaCode() +"\t\t" +c.getCinemaType());
            i++;
        }
        System.out.println("Enter ID to remove: ");
        choice = sc.nextInt();

        Cinema c = cinemaList.get(choice - 1);
        cinemaList.remove(choice - 1);

        cineplexUserChoice.setCinemas(cinemaList);
        cineplexControl.updateCineplex(cineplexUserChoice);

        System.out.println(c.getCinemaCode() + " cinema has been successfully removed from Cineplex");
    }


    public void holidayMain() {
        Scanner sc = new Scanner(System.in);
        System.out.println("====================== HOLIDAY =======================");
        System.out.println("|1. List Public Holiday                              |");
        System.out.println("|2. Create Public Holiday                            |");
        System.out.println("|3. Update Public Holiday                            |");
        System.out.println("|4. Remove Public Holiday                            |");
        System.out.println("|5. Back                                             |");
        System.out.println("|6. Quit                                             |");
        System.out.println("=======================================================");
        System.out.print("Please input your choice: ");
        int staffChoiceHoliday = sc.nextInt();
        System.out.println();
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


    public void listHoliday() {
        int holID;

        HolidayController hc = new HolidayController();
        List<Holiday> holidayList = hc.retrieveHolidayList();


        if (!holidayList.isEmpty()) {
            int n = 0;
            System.out.println("==== Holiday List ====");
            System.out.println("ID    Holiday Name               Date");
            for (Holiday h : holidayList) {
                System.out.format("%-5s %-25s %s\n", (n+1), h.getHolidayName(),h.getDate());
                n++;
            }
        } else {
            System.out.println("No Public Holidays set.");
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
        System.out.println("Public Holiday added successfully!");
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

        System.out.println("==== Update Public Holiday ====");
        System.out.println("Choose to update: ");
        System.out.println("1. Holiday Name");
        System.out.println("2. Holiday Date");
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
        System.out.println("======================== MOVIE =========================");
        System.out.println("|1. List of Movies                                     |");
        System.out.println("|2. Create New Movie                                   |");
        System.out.println("|3. Update Movie                                       |");
        System.out.println("|4. Remove Movie                                       |");
        System.out.println("|5. Back                                               |");
        System.out.println("|6. Quit                                               |");
        System.out.println("========================================================");
        System.out.print("Please input your choice: ");
        staffChoiceMovie = sc.nextInt();
        System.out.println();
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
            double sum = 0.0;
            System.out.format("Movie Title    : %s\n", m.getTitle());
            System.out.format("Movie Type     : %s\n", m.getMovieType());
            System.out.format("Movie Rating   : %s\n", m.getMovieRating());
            System.out.format("Duration       : %s\n", m.getDuration());
            System.out.format("Synopsis       : %s\n", staffControl.wordWrap(m.getSynopsis()));
            System.out.format("Director       : %s\n", m.getDirector());
            System.out.format("Cast           : %s\n", m.getCast());
            System.out.format("Showing Status : %s\n", m.getMovieStatus());

            List<Review> reviewList = m.getReviews();

            if (reviewList.size() > 1) {
                for (Review re : reviewList)
                    sum += re.getRating();
                System.out.format("Overall Rating: %.1f / 5.0\n", sum/reviewList.size());

            }
            else
                System.out.println("Overall Ratings: N/A");
            if (reviewList.size() > 0) {
                for (Review r : reviewList) {
                    System.out.println("User Rating    : " + r.getRating() + " / 5");
                    System.out.println("User Review    : " + staffControl.wordWrap(r.getFeedback()));
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
        System.out.println("====================== SHOWTIME =======================");
        System.out.println("|1. List Showtime                                     |");
        System.out.println("|2. Create Showtime                                   |");
        System.out.println("|3. Update Showtime                                   |");
        System.out.println("|4. Back                                              |");
        System.out.println("|5. Quit                                              |");
        System.out.println("=======================================================");
        System.out.print("Please input your choice: ");
        int staffChoiceST = sc.nextInt();
        System.out.println();
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

	public void pricechartMain(){
		Scanner sc = new Scanner(System.in);
        System.out.println("===================== PRICE CHART ======================");
        System.out.println("|1. List Price Chart                                   |");
        System.out.println("|2. Update Price Chart                                 |");
        System.out.println("|3. Back                                               |");
        System.out.println("|4. Quit                                               |");
        System.out.println("========================================================");
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
	
	public void staffMenuPriceChart(int pcChoice){
		
	}
	
	public void printPriceChartMenu(){
		
	}
	

	public static String ageGrpMenu(){
		int ageChoice;
		String ageGrp = null;
		List<PriceChart> pList = null;
		Scanner sc = new Scanner(System.in);
		System.out.println("Select an age group: ");
		System.out.println("1. Student");
		System.out.println("2. Adult");
		System.out.println("3. Platinum");
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
			System.out.println("1. 2D");
			System.out.println("2. 3D");
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

	

}