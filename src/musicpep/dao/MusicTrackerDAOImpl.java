package musicpep.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import musicpep.connection.ConnectionManager;

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

}
