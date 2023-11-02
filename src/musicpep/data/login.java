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
	
	 
	 System.out.println("Enter Password: ");
	 String password= login.nextLine();
	 musicdao.setPassword(password);
	
	 
	 login.close();
	 
	 musicdao.userLogIn(username,password);
	 
	 
//***************** Start ********************************************	
	
	
	 Scanner options = new Scanner (System.in);
	System.out.println("Please choose a option");
	 System.out.println("1. Album Search"); 
	 System.out.println("2. Tracker View");  
	 System.out.println("3. Exit"); 
	 
	 int A1= options.nextInt();
	 
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
			musicdao.addAlbumTracker(selectedAlbum, trackerId);;
		 }
		 
		else if (A_1==3)	{
			
		} 
			 
		 
	}
	//***************2(Tack view)********************************************	 
	 
	else  if(A1==2) {
	 
	
	 Scanner option_2 = new Scanner (System.in);
	 System.out.println("These are all the Trackers you have: \n");
	 
	 musicdao.getAlbumTracker(trackerId, albumId);
	 
	 
	 System.out.println("Would you like to: \n");
	 System.out.println("1. Update tracker");
	 System.out.println("2. Add Tracker");
	 System.out.println("3. View Tracker");	
	 System.out.println("4. Delete a Tracker");
	 int B_1= option_2.nextInt();
	 
	 if (B_1==1) {
		 System.out.println("What album do you want to update?");
		
		 musicdao.updateAlbumTracker(trackerId,  albumId, completedTracks);
		 
	 }
	 else if (B_1==2) {
		 System.out.println("Add a Tracker, Enter a album id:");	 
		 musicdaogetAlbumTracker(trackerId, albumId);	
	 }
	 
	else if (B_1==3)	{
		
		musicdao.getAlbumTracker(trackerId,albumId);
	} 

	else if (B_1==4)	//delete
		{
		System.out.println("Enter AlmbumID you want to delete from your Tracker:");
		musicdao.deleteAlbumTracker(trackerId, albumId)
		
		} 
	 option_2.close();
	 }
	//***************Exit********************************************	 
	 
	 
	else  if(A1==3) {
		System.out.println("Back");
	
	} 
	
	

	
	
	
	
	
	try {
		musicdao.closeConnection();
	} catch (SQLException e) {
		System.out.println("Could not close connection properly");
	}
}

}
