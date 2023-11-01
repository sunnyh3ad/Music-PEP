package musicpep.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import musicpep.data.*;
import musicpep.connection.ConnectionManager;


public class MusicTrackerDAOImpl implements MusicTrackerDAO {

    private static Connection connection = null;

     public static Connection getConnection() throws ClassNotFoundException, SQLException {
        if (connection == null) {
            connection = ConnectionManager.getConnection();
        }
        return connection;
    }

    @Override
    public void establishConnection() throws ClassNotFoundException, SQLException {

        if (connection == null) {
            connection = ConnectionManager.getConnection();
        }
    }

    @Override
    public void closeConnection() throws SQLException {

        connection.close();
    }

    @Override
    public boolean userLogIn(String username, String password) {
        boolean credential_check = false;

        try {

            if (connection == null) {
                establishConnection();
            }

            try (PreparedStatement pstmt = connection.prepareStatement("SELECT password FROM users WHERE username=?")) {

                pstmt.setString(1, username);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    String storedPassword = rs.getString(1);
                    if (storedPassword.equals(password)) {
                        credential_check = true;
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return credential_check;
    }

	

	

	@Override
	public Album_Trackers addAlbumTracker(Album selectedAlbum, int trackerId) {

		
		try( PreparedStatement pstmt = connection.prepareStatement("INSERT into Albums_Trackers(album_id, trackers_id, completed_tracks) values(?, ?, ?)") ) {
			
			pstmt.setInt(1, selectedAlbum.getId());
			pstmt.setInt(2, trackerId);
			pstmt.setInt(3, 0);
			
			int count = pstmt.executeUpdate();
			
			if(count > 0) {
				Album_Trackers album=new Album_Trackers(selectedAlbum.getId(),trackerId,0);
				return album;
			}
			
			
		} catch (SQLException e) {
			
				System.out.println("A SQL exception has occured for the musictracker database while adding album to tracker, the following exception was given.");
				System.out.println(e.getMessage());
		}
			
		return null;
	
	}

	@Override
	public Album_Trackers getAlbumTracker(int trackerId, int albumId) {
		
		try( PreparedStatement pstmt = connection.prepareStatement("select * from Albums_Trackers where tracker_id = ? and album_id = ?") ) {
			
			pstmt.setInt(1, trackerId);
			pstmt.setInt(2, albumId);
			
			ResultSet rs = pstmt.executeQuery();
			
			// rs.next() will return false if nothing found
			if( rs.next() ) {
				
				int tracksCompleted =rs.getInt("completed_tracks");
				
				rs.close();
				
				// constructing the object
				Album_Trackers album=new Album_Trackers(albumId,trackerId,tracksCompleted);
				
				return album;
				
			}
			else {
				rs.close();
			}
		
		} catch(SQLException e) {
			System.out.println("A SQL exception has occured for the musictracker database while retrieving album tracker, the following exception was given.");
			System.out.println(e.getMessage());
		}
		
		return null;
	}

	@Override
	public Album_Trackers updateAlbumTracker(int trackerId, int albumId, int completedTracks) {
		
		try( PreparedStatement pstmt = connection.prepareStatement("update Albums_Trackers set album_id = ?, completed_tracks = ? where tracker_id = ?") ) {
			
			pstmt.setInt(1, albumId);
			pstmt.setInt(2, completedTracks);
			pstmt.setInt(3, trackerId);
			
			int count = pstmt.executeUpdate();
			
			if(count > 0) {
				Album_Trackers album=new Album_Trackers(albumId,trackerId,completedTracks);
				return album;
			}
			
		} catch(SQLException e) {
			System.out.println("A SQL exception has occured for the musictracker database while updating album tracker, the following exception was given.");
			System.out.println(e.getMessage());
		}
		
		return null;
	}

    
    public List<Album> getAllAlbum() {
    	List<Album> albumList = new ArrayList<>();
    	
    	try(Statement statement = connection.createStatement();
    			ResultSet resSet = statement.executeQuery("select * from albums"))
    	{
    		while(resSet.next())
			{
				int id = resSet.getInt("album_id");
				String album_name = resSet.getString("album_name");
				String artist = resSet.getString("artist");
				String genre = resSet.getString("genre");
				int track_count = resSet.getInt("track_count");
				Album album = new Album(id, album_name, artist, genre, track_count);
				albumList.add(album);
			}
    	} catch(SQLException e)
    	{
    		System.out.println("An SQL exception has occured for the musictracker database while retreiving all albums, the following exception message was given.");
			System.out.println(e.getMessage());
			albumList = new ArrayList<>();
    	}
    	
    	return albumList;
    }
    
    public List<Album> searchForAlbum(String albumSearch) {
    	List<Album> albumList = new ArrayList<>();
    	
    	try(PreparedStatement prepStat = connection.prepareStatement("select * from albums where album_name = ?"))
		{
    		prepStat.setString(1, albumSearch);
			ResultSet resSet = prepStat.executeQuery();
			while(resSet.next())
			{
				int id = resSet.getInt("album_id");
				String album_name = resSet.getString("album_name");
				String artist = resSet.getString("artist");
				String genre = resSet.getString("genre");
				int track_count = resSet.getInt("track_count");
				Album album = new Album(id, album_name, artist, genre, track_count);
				albumList.add(album);
			}
			return albumList;
		} catch(SQLException e) {
			System.out.println("An SQL exception has occured for the musictracker database while searching for an album, the following exception message was given.");
			System.out.println(e.getMessage());
			return new ArrayList<>();
		}
    }
    
    public boolean deleteAlbumTracker(int trackerId, int albumId) {
    	
    	try( PreparedStatement pstmt = connection.prepareStatement("DELETE from album_trackers WHERE ((album_id = ?) AND (tracker_id = ?))") ) {
			
			pstmt.setInt(1, albumId);
			pstmt.setInt(2, trackerId);
			
			int i = pstmt.executeUpdate();
			
			if(i > 0) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("An SQL exception has occured while deleting from the musictracker database the following exception message was given.");
			System.out.println(e.getMessage());
			return false;
		}
    	
    	return false;
    }

}
