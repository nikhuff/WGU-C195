package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUser {

    public static User authenticateUser(String providedUsername, String providedPassword) {

        try {
            String sql = "SELECT * FROM users";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("User_ID");
                String username = rs.getString("User_Name");
                String password = rs.getString("Password");

                User user = new User(id, username);
                if (providedUsername.matches(username) && providedPassword.matches(password)) {
                    return user;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ObservableList<Integer> getUsers() {

        ObservableList<Integer> userIds = FXCollections.observableArrayList();
        try {
            String sql = "SELECT User_ID, User_Name FROM users;";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("User_ID");
                userIds.add(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userIds;
    }
}
