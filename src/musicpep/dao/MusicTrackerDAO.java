package musicpep.dao;

import java.sql.SQLException;

/*
	DAO INTERFACE
	--------------------
*/

public interface MusicTrackerDAO {

    // needed for later so we make sure that the connection manager gets called
    public void establishConnection() throws ClassNotFoundException, SQLException;

    // as well, this method will help with closing the connection
    public void closeConnection() throws SQLException;

    // User login
    public boolean userLogIn(String username, String password);
}
