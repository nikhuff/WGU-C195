package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBContact {

    public static ObservableList<String> getContacts() {

        ObservableList<String> contacts = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM contacts";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                String name = rs.getString("Contact_Name");

                contacts.add(name);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contacts;
    }
}
