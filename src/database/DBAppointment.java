package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBAppointment {

    public static ObservableList<Appointment> getAppointments() {

        ObservableList<Appointment> appointments = FXCollections.observableArrayList();

        try {
            String sql =
                    "SELECT appointments.Appointment_ID, appointments.Title, appointments.Description," +
                            "appointments.Location,appointments.Type, contacts.Contact_Name, appointments.Start," +
                            "appointments.End, appointments.Customer_ID, appointments.User_ID\n" +
                            "FROM appointments\n" +
                            "INNER JOIN contacts ON appointments.Contact_ID=contacts.Contact_ID;";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                String contact = rs.getString("Contact_Name");
                String start = rs.getString("Start");
                String end = rs.getString("End");
                int customerID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");

                Appointment appointment = new Appointment(id, title, description, location, type, start, end, customerID, userID, contact);
                appointments.add(appointment);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appointments;
    }
}
