package musicpep.data;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import musicpep.data.User;
import musicpep.dao.MusicTrackerDAO;
import musicpep.dao.MusicTrackerDAOImpl;
import musicpep.data.User;

public class login {
	
	public void log_in() {
	
		
	//*****************ConnectionMenu ********************************************
		MusicTrackerDAO musicdao= new MusicTrackerDAOImpl();
		musicdao.establishConnection();
		
	//*****************First Menu ********************************************		
		
	
	 Scanner login = new Scanner (System.in);
	
	
	System.out.println("Welecome User!!!!");
	System.out.println("Enter Username: ");
	 String username= login.nextLine();
	 musicdao.setUser_name(username);
	 System.out.println("Username is  "+ username);
	 System.out.println("Enter Password: ");
	 String password= login.nextLine();
	 musicdao.setPassword(password);
	
	 
	 login.close();
	 
	 musicdao.userLogIn(username,password);
	 
	 
//***************** Start ********************************************	
	
	
	 Scanner options = new Scanner (System.in);
	System.out.println("Please choose a option");
	 
	 System.out.println("1. Album Search"); 
	 int A1= options.nextInt();
	 
	 System.out.println("2. Tracker View"); 
	 int B1= options.nextInt();
	 System.out.println("3. Exit"); 
	 int C1= options.nextInt();
	 
	 options.close();
//***************** option 1(Search) ********************************************	 
	if (A1==1) {//search
		
		
		Scanner search = new Scanner (System.in);
		System.out.println("Enter the album you want to Search: ");
		String albumSearch =search.nextLine();
		
	
		List<Album> albumlist=musicdao.searchForAlbum(albumSearch);
		search.close();
		
		 Scanner options2 = new Scanner (System.in);
		 System.out.println("1.Select album to track");
		
		 
		 System.out.println("2.View album info");

		 System.out.println("3.Back");
		 int A_1 =options2 .nextInt();
		
		if (A_1==1) {
			System.out.println("Enter the albumname" + " and TrackerID you want to track: ");
			
			musicdao.addAlbumTracker(selectedAlbum,trackerId);
		
		 }
		 else if (A_1==2) {
			 musicdao.getAllAlbum();
		 }
		 
		else if (A_1==3)	{
			
		} 
			 
		 
	}
	
	 
	//***************2(Tack view)********************************************	 
	 
	else  if(B1==2) {
	 musicdao.getAlbumTracker(int trackerId, int albumId);
	 Scanner option_2 = new Scanner (System.in);
		 
	 System.out.println("These are all the Trackers you have: \n");
	 musicdao.getAlbumTracker(trackerId, albumId);
	 
	 System.out.println("Would you like to: \n");
	 System.out.println("1. Update tracker");
	 int B_1= option_2.nextInt();
	 System.out.println("2. Add Tracker");
	 System.out.println("3. View Tracker");	
	 System.out.println("4. Delete a Tracker");
	
	 
	 if (B_1==1) {
		 System.out.println("What album do you want to update?");
		 
		 
		 musicdao.getAlbumTracker(int trackerId, int albumId);
		 musicdao.updateAlbumTracker(trackerId,  albumId, completedTracks);
		 
	 }
	 else if (B_1==2) {
		 System.out.println("Add a Tracker");
		
		 
	musicdaogetAlbumTracker(int trackerId, int albumId);
		 
		
	 }
	 
	 
	else if (B_1==3)	{
		
		musicdao.getAlbumTracker(int trackerId, int albumId);
	} 

	else if (B_1==4)	//delete
		{
		musicdao.getAlbumTracker(int trackerId, int albumId);
		System.out.println("Enter TrackerID and AlmbumID you want to delete from your Tracker:");
		
		musicdao.deleteAlbumTracker(int trackerId, int albumId)
		} 
	 option_2.close();
	 }
	//***************Exit********************************************	 
	 
	 
	else  if(C1==3) {
		Scanner option_3 = new Scanner (System.in);
		System.out.println("Back");
		
	
	} 
	
	
	
	try {
		musicdao.closeConnection();
	} catch (SQLException e) {
		System.out.println("Could not close connection properly");
	}
}

}
