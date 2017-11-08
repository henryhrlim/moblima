package controllers;

import java.util.Scanner;

public class StaffController {
	private StaffMenu s;
	private MoblimaApp app;
	Scanner sc = new Scanner(System.in);
	
//	Return true if successfully authenticated and false if credentials are wrong
	public boolean authenticate() {

		System.out.println("Username: ");
		char username[] = sc.nextLine();
		System.out.println("Password: ");
		char password[] = sc.nextLine();
		if (username.equals("admin") && password.equals("admin")) {
			System.out.println("Logged in.");
			return true;
		}
		else
			return false;
	}
	
//	Prints cineplex menu
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
			s.show();
		else if (choice == 7) {
			System.out.println("Exiting...");
			sc.close();
			System.exit(0);
			break;
		}
	}
	
	public void displayCineplexList() {
		
	}
	
	 public void addCineplex() {
		 
	 }
	 
	 public void addMovieToCineplex() {
		 
	 }
	  
	 public void listMovieSpecificToCineplex(Cineplex cineUserChoice) {
		 
	 }
	 
	 public void removeMovieFromCineplex() {
		 
	 }
	 
	 public void addShowTimeToCineplex() {
		 
	 }
	 
	 public void removeShowTimeFromCineplex() {
		 
	 }
	 
	 public void addCinemaToCineplex(Cineplex cineUserChoice) {
		 
	 }
	 
	 public void removeCinemaFromCineplex() {
		 
	 }
	 
	 public void listShowTimeSpecificToCineplex(Cineplex cineUserChoice) {
		 
	 }
	 
	 public void staffMenuCinema() {
		 
	 }
	 
	 public void displayCinemaList() {
		 
	 }
	 
	 public void updateCinemaInCineplex() {
		 
	 }
	 
	 public void holidayMenu() {
		 
	 }
	 
	 public static String retrieveList() {

	 }
	 
	 public void staffMenuHoliday(int choice) {
		 
	 }
	 
	 public void listHoliday() {
		 
	 }
	 
	 public void addHoliday() {
		 
	 }
	 
	 public void updateHoliday() {
		 
	 }
	 
	 public void deleteHoliday() {
		 
	 }
	 
	 public void printHolidayMenu() {
		 
	 }
	 
	 public void staffMenuMovie() {
		 
	 }
	 
	 public void displayMovieList() {
		 
	 }
	 
	 public void updateMovieList() {
		 
	 }
	 
	 public void addMovieToList() {
		 
	 }
	 
	 public void removeMovieList() {
		 
	 }
	 
	 public void showTimeMain() {
		 
	 }
	 
	 public void staffMenuShowTime(int choice) {
		 
	 }
	 
	 public static void listShowTime() {
		 
	 }
	 
	 public static void listByMovie() {
		 
	 }
	 
	 public static void listByCinema() {
		 
	 }
	 
	 public static void addShowTime() {
		 
	 }
	 
	 public static void updateShowTime() {
		 
	 }
	 
	 public void printShowTimeMenu() {
		 
	 }
	 
	 public static void updateSTDate(int selected) {
		 
	 }
	 
	 public static void updateSTTime(int selected) {
		 
	 }
	 
	 public static void deleteShowTime() {
		 
	 }
	 
	 public static Date validateDate() {
		 
	 }
	 
	 public static Date validateTime() {
		 
	 }
	 
	 public static int retrieveCineplex() {
		 
	 }
	 
	 public static String retrieveCinemaCode(int cineplexID) {
		 
	 }
	 
	 public operation() {
		 
	 }
	 
	 public void priceChartMain() {
		 
	 }
	 
	 public void staffMenuPriceChart(int pcChoice) {
		 
	 }
	 
	 public void printPriceChartMenu() {
		 
	 }
	 
	 public static String ageGrpMenu() {
		 
	 }
	 
	 public static void updatePriceChart() {
		 
	 }
	 
	 public static void listPriceChart() {
		 
	 }
	 
	 public static List<PriceChart> filterAdult(String ageGrp) {
		 
	 }
	 
	 public static List<PriceChart> filterListPriceChart(String ageGrp) {
		 
	 }
	 
	 sc.close();
}
