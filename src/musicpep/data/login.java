package musicpep.data;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import musicpep.data.User;
import musicpep.dao.MusicTrackerDAO;
import musicpep.dao.MusicTrackerDAOImpl;
import musicpep.dao.NegativeTrackCountException;
import musicpep.dao.TrackCountExceededException;
import musicpep.data.User;

public class login {
	
	public static void log_in() {
	
		
	//*****************ConnectionMenu ********************************************
		MusicTrackerDAO musicdao = new MusicTrackerDAOImpl();
		try {
			musicdao.establishConnection();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Connection could not be established.");
			e.printStackTrace();
			return;
		}
		
	//*****************First Menu ********************************************		
		
	
	 Scanner scanner = new Scanner (System.in);
	 int user_id;
	 String username = "";
	 boolean login_success = false;
	 System.out.println("Welecome User!!!!");
	 while(!login_success) {
		 
		 System.out.println("Enter Username: ");
		 username = scanner.nextLine();
		 System.out.println("Enter Password: ");
		 String password = scanner.nextLine();
		 login_success = musicdao.userLogIn(username,password);
		 if(login_success == false)
		 {
			 System.out.println("Failed to Login, Try again!");
		 }
		 user_id = musicdao.getUserByUsername(username);
	}
	 user_id = musicdao.getUserByUsername(username);
	 
	 
	 
	 
	 
//***************** Start ********************************************	
	boolean exit = false;
	while(!exit)
	{
	System.out.println("Please choose a option");
	 
	System.out.println("1. Album Search"); 
	System.out.println("2. Tracker View"); 
	System.out.println("3. Exit"); 
	 

	int A1 = 0;
	boolean input_accepter = false;
	while(!input_accepter)
	{
		try {
			A1 = scanner.nextInt();
			input_accepter = true;
		} catch(InputMismatchException e)
		{
			System.out.println("Input must be an integer. Please try again.");
			scanner.next();
		}
	}
//***************** option 1(Search) ********************************************	 
	if (A1==1) {//search
	boolean escape_from_a1 = false;
	while(!escape_from_a1)
	{
		
		System.out.println("Enter the album you want to Search, if all albums, enter nothing.");
		scanner.nextLine();
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
		
		System.out.println("ID, Name, Artist, Genere, Track Count");
		for(Album i : album_list)
		{
			System.out.println(i);
		}
		
		System.out.println("1.Select album to track");
		
		 
		System.out.println("2.View album info");

		System.out.println("3.Back");

		int A_1 = 0;
		boolean input_accept = false;
		while(!input_accept)
		{
			try {
				A_1 = scanner.nextInt();
				input_accept = true;
			} catch(InputMismatchException e)
			{
				System.out.println("Input must be an integer. Please try again.");
				scanner.next();
			}
		}
		
		if (A_1==1) {
			System.out.println("Enter the album_id");
			int selectedAlbum = 0;
			boolean input_accept2 = false;
			while(!input_accept2)
			{
				try {
					selectedAlbum = scanner.nextInt();
					input_accept2 = true;
				} catch(InputMismatchException e)
				{
					System.out.println("Input must be an integer. Please try again.");
					scanner.next();
				}
			}
			Album album = musicdao.getAlbumByID(selectedAlbum);
			if(album != null)
			{
				musicdao.addAlbumTracker(album, musicdao.getTrackerID(user_id).getId());
				System.out.println("Album Added!");
			}
			else
			{
				System.out.println("Album Failed To Add!");
			}
			break;
		}
		else if (A_1==2) {
			System.out.println("Enter the album_id");
			int selectedAlbum = 0;
			boolean input_accept2 = false;
			while(!input_accept2)
			{
				try {
					selectedAlbum = scanner.nextInt();
					input_accept2 = true;
				} catch(InputMismatchException e)
				{
					System.out.println("Input must be an integer. Please try again.");
					scanner.next();
				}
			}
			if(musicdao.getAlbumByID(selectedAlbum) == null)
			{
				System.out.println("No such album!");
			}
			else
			{
				System.out.println(musicdao.getAlbumByID(selectedAlbum));
			}

		}
		else if (A_1==3) {
			escape_from_a1 = true;
		} 
			 
	}
	}
	
	 
	//***************2(Tack view)********************************************	 
	
	
	else  if(A1==2) {
	boolean escape = false;
	while(!escape)
	{
	 List<Album_Trackers> album_tracker_list = musicdao.getAllAlbumTrackersbyTracker(musicdao.getTrackerID(user_id).getId());
	 String completed;
	 
	 System.out.println("These are all the Trackers you have: \n");
	 for(Album_Trackers i : album_tracker_list)
	 {
		 double completion_percent = ((double)i.getCompleted_tracks()) / musicdao.getAlbumByID(i.getAlbum_id()).getTrack_count();
		 if(completion_percent == 0)
		 {
			 completed = "Not Started";
		 }
		 else if((completion_percent > 0) && (completion_percent < 1))
		 {
			 completed = "In Progress";
		 }
		 else
		 {
			 completed = "Completed";
		 }
		 System.out.println(i.getAlbum_id() + " " + musicdao.getAlbumByID(i.getAlbum_id()).getAlbum_name() + " "
				 + i.getCompleted_tracks() + " Tracks Completed: " + completed);
	 }
	 System.out.println("Would you like to: \n");
	 System.out.println("1. Update tracker");
	 System.out.println("2. Add Tracker");
	 System.out.println("3. View Tracker");	
	 System.out.println("4. Delete a Tracker");
	 System.out.println("5. Back");

	 int B_1 = 0;
	 boolean input_accept = false;
	 while(!input_accept)
	 {
		 try {
			 B_1 = scanner.nextInt();
			 input_accept = true;
		 } catch(InputMismatchException e)
		 {
			 System.out.println("Input must be an integer. Please try again.");
			 scanner.next();
		 }
	 }
	 
	 if (B_1==1) {
		 System.out.println("What album tracker do you want to update? Select an album number.");
		 
		 int album_id = 0;
		 boolean input_accept2 = false;
		 while(!input_accept2)
		 {
			 try {
				 album_id = scanner.nextInt();
				 input_accept2 = true;
			 } catch(InputMismatchException e)
			 {
			 	 System.out.println("Input must be an integer. Please try again.");
			 	scanner.next();
			 }
		 }
		 boolean appropriate_track = false;
		 while(!appropriate_track)
		 {
			 try{
				 System.out.println("How many tracks have you completed on the album?");
				 int completed_tracks = 0;
					boolean input_accept3 = false;
					while(!input_accept3)
					{
						try {
							completed_tracks = scanner.nextInt();
							input_accept3 = true;
						} catch(InputMismatchException e)
						{
							System.out.println("Input must be an integer. Please try again.");
							scanner.next();
						}
					}
				 Album check = musicdao.getAlbumByID(album_id);
				 
				 if(check != null)
				 {
					 musicdao.updateAlbumTracker(musicdao.getTrackerID(user_id).getId(),  album_id, completed_tracks);
				 }
				 else
				 {
					 System.out.println("Album ID not a valid ID.");
					 break;
				 }
			 } catch (NegativeTrackCountException e)
			 {
				 System.out.println(e.getMessage());
				 continue;
				 
			 } catch (TrackCountExceededException e)
			 {
				 System.out.println(e.getMessage());
				 continue;
			 }
			 appropriate_track = true;
		 }
	 }
	 else if (B_1==2) {
		 List<Album> album_list = musicdao.getAllAlbum();
		 System.out.println("ID, Name, Artist, Genere, Track Count");
		for(Album i : album_list)
		{
			System.out.println(i);
		}
		 System.out.println("Select album number to track.");
		 
		 int selectedAlbum = 0;
			boolean input_accept2 = false;
			while(!input_accept2)
			{
				try {
					selectedAlbum = scanner.nextInt();
					input_accept2 = true;
				} catch(InputMismatchException e)
				{
					System.out.println("Input must be an integer. Please try again.");
					scanner.next();
				}
			}
		 Album album = musicdao.getAlbumByID(selectedAlbum);
		 if(album != null)
		 {
			 musicdao.addAlbumTracker(album, musicdao.getTrackerID(user_id).getId());
		 }
		 else
		 {
			 System.out.println("Album ID not a valid ID.");
		 }
	 }
	 
	 
	else if (B_1==3)	{
		System.out.println("Select album number to view.");
		int selectedAlbum = 0;
		boolean input_accept2 = false;
		while(!input_accept2)
		{
			try {
				selectedAlbum = scanner.nextInt();
				input_accept2 = true;
			} catch(InputMismatchException e)
			{
				System.out.println("Input must be an integer. Please try again.");
				scanner.next();
			}
		}
		if(musicdao.getAlbumTracker(musicdao.getTrackerID(user_id).getId(), selectedAlbum) == null)
		{
			System.out.println("Album ID not a valid ID.");
		}
		else
		{
			System.out.println(musicdao.getAlbumTracker(musicdao.getTrackerID(user_id).getId(), selectedAlbum));
		}
	} 

	else if (B_1==4)	//delete
	{
		System.out.println("Enter AlmbumID you want to delete from your Tracker:");
		int selectedAlbum = 0;
		boolean input_accept2 = false;
		while(!input_accept2)
		{
			try {
				selectedAlbum = scanner.nextInt();
				input_accept2 = true;
			} catch(InputMismatchException e)
			{
				System.out.println("Input must be an integer. Please try again.");
				scanner.next();
			}
		}
		if(!musicdao.deleteAlbumTracker(musicdao.getTrackerID(user_id).getId(), selectedAlbum))
		{
			System.out.println("Album ID not a valid ID.");
		}
		
	}
	else if (B_1==5)
	{
		escape = true;
	}
	}
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
	} finally
	{
		scanner.close();
	}
}

}
