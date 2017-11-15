package app;

import controller.StaffController;

import java.util.Scanner;


public class MoblimaApp {

    private static StaffMenu staff_m = new StaffMenu();
    private static CustomerMenu customer = new CustomerMenu();


    public static void main(String[] args) {
        StaffController staff = new StaffController();
        Scanner sc = new Scanner(System.in);
        boolean login = false;
        int choice = 0;

        do {
            System.out.println("MAIN MENU");
            System.out.println("Select the type of user: ");
            System.out.println("|1. Customer");
            System.out.println("|2. Staff/Admin");
            System.out.println("|3. Quit");

            System.out.println("Choice (1-3): ");
            choice = sc.nextInt();


            switch (choice) {

                case 1:
                    customer.show();
                    break;

                case 2:
                    do {
                        login = staff.authenticate();
                    } while (login == false);
                    while (login) {
                        staff_m.show();
                    }
                    break;

                case 3:
                    System.out.println("Program terminating...");
                    System.exit(0);
                    break;

                default:
                    break;
            }
        } while (choice != 3);
    }


}
