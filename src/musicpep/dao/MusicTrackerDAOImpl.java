package musicpep.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import musicpep.connection.ConnectionManager;
import musicpep.data.Album;

public class MusicTrackerDAOImpl implements MusicTrackerDAO {

    private Connection connection = null;

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
            Connection conn = ConnectionManager.getConnection();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE username='" + username + "';");

            if (rs.next()) {
                if (rs.getString(3).equals(password)) {
                    credential_check = true;
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return credential_check;
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
