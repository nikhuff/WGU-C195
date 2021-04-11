package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBDivision {

    public static ObservableList<String> getDivisions() {

        ObservableList<String> divisions = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM first_level_divisions";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                String name = rs.getString("Division");

                divisions.add(name);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return divisions;
    }

    public static ObservableList<String> getDivisions(String country) {

        ObservableList<String> divisions = FXCollections.observableArrayList();

        try {
            String sql = "SELECT first_level_divisions.Division, countries.Country\n" +
                    "FROM first_level_divisions\n" +
                    "INNER JOIN countries ON first_level_divisions.COUNTRY_ID=countries.Country_ID\n" +
                    "WHERE countries.Country=\"" + country + "\"";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                String name = rs.getString("Division");

                divisions.add(name);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return divisions;
    }
}
