package musicpep.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import musicpep.connection.ConnectionManager;
import musicpep.data.Album;
import musicpep.data.Album_Trackers;
import musicpep.data.Trackers;

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

	@Override
	public List<Album> getAllAlbum() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Album> searchForAlbum(String albumSearch) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Album_Trackers addAlbumTracker(Album selectedAlbum, int trackerId) {
		// TODO Auto-generated method stub
		
		
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
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
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

	@Override
	public boolean deleteAlbumTracker(int trackerId, int albumId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Trackers getTrackerID(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Trackers addTrackers(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
