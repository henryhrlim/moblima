import java.util.Scanner;
import controller.StaffController;

public class MoblimaApp {
	private static StaffMenu staff_m = new StaffMenu();
	private static CustomerMenu customermenu = new CustomerMenu();
	
	public static void main(String[] args) {
		StaffController employee = new StaffController();
		Scanner one = new Scanner(System.in);
		int choice = 0;
		boolean login  = false;
		
		do{
			System.out.println("Welcome to the selection menu. ");
			System.out.println("Please select the type of user that you are: ");
			System.out.println("1. Customer");
			System.out.println("2. Staff/Admin");
			System.out.println("3. Quit");
			choice = sc.nextInt();
			

			switch(choice){

				case 1://customer menu
					customer.show();
					break;
	
				case 2: //staff menu
					do {
						login = staff.authenticate();
					} while(login == false); 
					while(login){
						staff_m.show();
					}
					break;
	
				case 3: //quit menu
					System.out.println("Program terminating...");
					System.exit(0);
					break;
	
				default:
					break;
			}// end switch
		}while(choice != 3);

	} // end main


}

}
