package app;

import controller.StaffController;

import java.util.Scanner;


public class MoblimaApp {

    private static final StaffMenu staff_m = new StaffMenu();
    private static final CustomerMenu customer = new CustomerMenu();


    public static void main(String[] args) {
        StaffController staff = new StaffController();
        Scanner sc = new Scanner(System.in);
        boolean login = false;
        int choice = 0;

        do {
            System.out.println("======================= MOBLIMA =======================");
            System.out.println("|1. Customer                                          |");
            System.out.println("|2. Staff                                             |");
            System.out.println("|3. Quit                                              |");
            System.out.println("=======================================================");
            System.out.print("Please input your choice: ");

            choice = sc.nextInt();
            System.out.print("\n");

            switch (choice) {
                case 1:
                    customer.show();
                    break;
                case 2:
                    for (int i = 3; i > 0; i--) {
                        if (staff.authenticate()) {
                            staff_m.show();
                            break;
                        } else
                            System.out.println("Wrong username or password. " + (i - 1) + " tries remaining.\n");
                    }
                    break;
                case 3:
                    sc.close();
                    System.out.println("Program terminating...");
                    System.exit(0);
                    break;
                default:
                    break;
            }
        } while (choice != 3);
    }

}
