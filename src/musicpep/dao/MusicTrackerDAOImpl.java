package musicpep.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

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
            // Ensure the connection is established
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
}
