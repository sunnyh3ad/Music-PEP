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
		MusicTrackerDAO musicdao = new MusicTrackerDAOImpl();
		musicdao.establishConnection();
		
	//*****************First Menu ********************************************		
		
	
	 Scanner scanner = new Scanner (System.in);
	 int user_id;
	 boolean login_success = false;
	 while(!login_success) {
		 System.out.println("Welecome User!!!!");
		 System.out.println("Enter Username: ");
		 String username = scanner.nextLine();
		 System.out.println("Username is  "+ username);
		 System.out.println("Enter Password: ");
		 String password = scanner.nextLine();
		 login_success = musicdao.userLogIn(username,password);
		 user_id = musicdao.getUserByUsername(username);
	}
	 
	 
	 
	 
	 
	 
//***************** Start ********************************************	
	boolean exit = false;
	while(!exit)
	{
	System.out.println("Please choose a option");
	 
	System.out.println("1. Album Search"); 
	System.out.println("2. Tracker View"); 
	System.out.println("3. Exit"); 
	 
	int A1 = scanner.nextInt();
//***************** option 1(Search) ********************************************	 
	if (A1==1) {//search
		
		
		System.out.println("Enter the album you want to Search, if all albums, enter nothing.");
		String albumSearch = scanner.nextLine();
		List<Album> album_list;
		if(albumSearch.equals(""))
		{
			album_list = musicdao.getAllAlbum();
		}
		else
		{
			album_list = musicdao.searchForAlbum(albumSearch);
		}
		
		System.out.println(album_list);
		
		System.out.println("1.Select album to track");
		
		 
		System.out.println("2.View album info");

		System.out.println("3.Back");
		int A_1 = scanner.nextInt();
		
		if (A_1==1) {
			System.out.println("Enter the album_id");
			int selectedAlbum = scanner.nextInt();
			musicdao.addAlbumTracker(musicdao.getAlbumById(selectedAlbum),trackerId);
		}
		else if (A_1==2) {
			System.out.println("Enter the album_id");
			int selectedAlbum = scanner.nextInt();
			musicdao.getAlbumById(selectedAlbum);
		}
		else if (A_1==3) {
			continue;
		} 
			 
		 
	}
	
	 
	//***************2(Tack view)********************************************	 
	 
	else  if(A1==2) {
	 List<Album_Trackers> album_tracker_list = musicdao.getAllAlbumTrackersByTracker(musicdao.getTrackerID(user_id));
		 
	 System.out.println("These are all the Trackers you have: \n");
	 System.out.println(album_tracker_list);
	 System.out.println("Would you like to: \n");
	 System.out.println("1. Update tracker");
	 System.out.println("2. Add Tracker");
	 System.out.println("3. View Tracker");	
	 System.out.println("4. Delete a Tracker");
	 int B_1= scanner.nextInt();
	 
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
	 
	 
	else  if(A1==3) {
		exit = true;
	} 
	}
	
	
	
	try {
		musicdao.closeConnection();
	} catch (SQLException e) {
		System.out.println("Could not close connection properly");
	}
}

}
