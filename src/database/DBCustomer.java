package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBCustomer {

    public static ObservableList<Customer> getCustomers() {

        ObservableList<Customer> customers = FXCollections.observableArrayList();

        try {
            String sql =
                    "SELECT customers.Customer_ID, customers.Customer_Name, customers.Address, customers.Postal_Code," +
                            "customers.Phone, countries.Country, first_level_divisions.Division\n" +
                            "FROM customers\n" +
                            "INNER JOIN first_level_divisions ON customers.Division_ID=first_level_divisions.Division_ID\n" +
                            "INNER JOIN countries ON first_level_divisions.COUNTRY_ID=countries.Country_ID;";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("Customer_ID");
                String name = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String zipcode = rs.getString("Postal_Code");
                String phone = rs.getString("Phone");
                String division = rs.getString("Division");
                String country = rs.getString("Country");

                Customer customer = new Customer(id, name, address, zipcode, phone, country, division);
                customers.add(customer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customers;
    }
}
