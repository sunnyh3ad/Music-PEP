package musicpep.dao;

import java.sql.SQLException;
import java.util.List;
import musicpep.data.*;

/*
	DAO INTERFACE
	--------------------
*/

public interface MusicTrackerDAO {

    // needed for later so we make sure that the connection manager gets called
    public void establishConnection() throws ClassNotFoundException, SQLException;

    // as well, this method will help with closing the connection
    public void closeConnection() throws SQLException;

    // Find user by username
    public int getUserByUsername(String username);

    // User login
    public boolean userLogIn(String username, String password);

    public List<Album> getAllAlbum();

    public List<Album> searchForAlbum(String albumSearch);

    public Album_Trackers addAlbumTracker(Album selectedAlbum, int trackerId);

    public Album_Trackers getAlbumTracker(int trackerId, int albumId);

    public List<Album_Trackers> getAllAlbumTrackersbyTracker(int trackerId);

    public Album_Trackers updateAlbumTracker(int trackerId, int albumId, int completedTracks)
            throws TrackCountExceededException, NegativeTrackCountException;

    public boolean deleteAlbumTracker(int trackerId, int albumId);

    public Trackers getTrackerID(int userId);

    public Trackers addTrackers(int userId);

}
