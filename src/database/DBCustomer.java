package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;
import model.User;

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

    public static void updateCustomer(Customer customer) {
        try {
            String sql =
                    "UPDATE customers\n" +
                            "SET Customer_Name = \"" + customer.getName() +"\",\n" +
                            "    Address = \"" + customer.getAddress() + "\",\n" +
                            "    Postal_Code = \"" + customer.getZipcode() + "\",\n" +
                            "    Phone = \"" + customer.getPhone() + "\",\n" +
                            "    Last_Update = NOW(),\n" +
                            "    Last_Updated_By = " + User.getId() + ",\n" +
                            "    Division_ID = (SELECT Division_ID FROM first_level_divisions WHERE Division=\"" +
                            customer.getDivision() + "\") \n" +
                            "WHERE customers.Customer_ID = " + customer.getId() + ";";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addCustomer(Customer customer) {
        try {
            String sql =
                    "INSERT INTO customers VALUES(" + customer.getId() + ", '" + customer.getName() +
                            "', '" + customer.getAddress() + "', '" + customer.getZipcode() + "', '" +
                            customer.getPhone() + "', " +  "NOW(), " + User.getId() + ", NOW(), " + User.getId() +
                            "," +
                            " (SELECT Division_ID FROM first_level_divisions WHERE Division ='" + customer.getDivision() + "'));\n";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteCustomer(String id) {
        DBAppointment.deleteAppointmentByCustomer(id);
        try {
            String sql =
                    "DELETE FROM customers WHERE Customer_ID=" + id;

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
