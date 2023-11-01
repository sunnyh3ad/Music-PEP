package musicpep.data;

import java.util.Scanner;
import musicpep.data.User;
import musicpep.data.MusicTrackerDAO;

public class login {
	
	public void log_in() {
		
	//*****************ConnectionMenu ********************************************
		//ChefDao chefDao = new ChefDaoImpl();
		MusicTrackerDAO musicdao= new  MusicDaoImpl();
		
		
		private Connection connection = null;
		public void establishConnection() throws ClassNotFoundException, SQLException {
			
			if(connection == null) {
				connection = ConnectionManager.getConnection();
			}
		}
		
		@Override
		public void closeConnection() throws SQLException {
			connection.close();
		}
	//*****************First Menu ********************************************		
		
	Scanner scan = new Scanner (File(""));
	 Scanner login = new Scanner (System.in);
	
	
	System.out.println("Welecome User!!!!");
	System.out.println("Enter Username: ");
	 String username= scan.nextLine();
	getUser_name(username);
	 System.out.println("Username is  "+ username);
	 System.out.println("Enter Password: ");
	 String password= scan.nextLine();
	 
	 
	 userLogIn(username, password);
	 
	 scanner.close();

//***************** option 1 ********************************************	
	 
	
	 Scanner options = new Scanner (System.in);
	 System.out.println("Please choose a option");
	 
	 System.out.println("1. Album Search"); 
	 int A1= options.nextInt();
	 
	 System.out.println("2. Tracker View"); 
	 int B1= options.nextInt();
	 System.out.println("3. Exit"); 
	 int C1= options.nextInt();
	 
	 scanner.close();
	 
	if (A1==1) {
		
		 Scanner options2 = new Scanner (System.in);
		 System.out.println("Select album to track");
		 int A_1 =options2 .nextInt();
		 
		 System.out.println("View album info");
		 int A_2 =options2 .nextInt();
		
		 System.out.println("Back");
		 int A_3 =options2 .nextInt();
		
		if (A_1==1) {
			 
		 }
		 else if (A_2==2) {
			 
		 }
		 
		 
		else if (A_3==3)	{} 
			 
		 
	}
	
	 
	//***************2********************************************	 
	 
	else  if(B1==2) {
		
		 Scanner option_2 = new Scanner (System.in);
		 
		 
	 System.out.println("These are all the Trackers you have: \n");
	 System.out.println("Would you like to: \n");
	 System.out.println("1. Update tracker");
	 int B_1= option_2.nextInt();
	 System.out.println("2. Add Tracker");
	 int B_2= option_2.nextInt();
	 System.out.println("3. View Tracker");
	 int B_3= option_2.nextInt();
	 System.out.println("4. Delete a Tracker");
	 int B_4= option_2.nextInt();
	 
	 if (B_1==1) {
		 
	 }
	 else if (B_2==2) {
		 
	 }
	 
	 
	else if (B_3==3)	{
		
	} 

	else if (B_4==4)	//delete
		{
		
		} 
	 
	 }
	//***************3********************************************	 
	 
	 
	else  if(C1==3) {
		Scanner option_3 = new Scanner (System.in);
		System.out.println("Back");
	} 
}

}
