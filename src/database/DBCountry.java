package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBCountry {

    public static ObservableList<String> getCountries() {

        ObservableList<String> countries = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM countries";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                String name = rs.getString("Country");

                countries.add(name);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return countries;
    }
}
