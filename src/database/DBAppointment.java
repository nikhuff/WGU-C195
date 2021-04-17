package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;
import model.User;
import util.Time;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.HashMap;

public class DBAppointment {

    public static ObservableList<Appointment> getAppointments() {

        ObservableList<Appointment> appointments = FXCollections.observableArrayList();

        try {
            String sql =
                    "SELECT appointments.Appointment_ID, appointments.Title, appointments.Description, " +
                            "appointments.Location,appointments.Type, contacts.Contact_Name, appointments.Start, " +
                            "appointments.End, appointments.Customer_ID, appointments.User_ID " +
                            "FROM appointments " +
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
                String startString = rs.getString("Start");
                String endString = rs.getString("End");
                int customerID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");

                ZonedDateTime start = Time.parseString(startString);
                ZonedDateTime end = Time.parseString(endString);

                Appointment appointment = new Appointment(id, title, description, location, type, start, end, customerID, userID, contact);
                appointments.add(appointment);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appointments;
    }

    public static void updateAppointment(Appointment appointment) {
        try {
            String sql =
                    "UPDATE appointments\n" +
                            "SET Contact_ID = (SELECT Contact_ID FROM contacts WHERE Contact_Name=\"" + appointment.getContact() + "\"),\n" +
                            "    Title = \"" + appointment.getTitle() +"\",\n" +
                            "    Description = \"" + appointment.getDescription() + "\",\n" +
                            "    Location = \"" + appointment.getLocation() + "\",\n" +
                            "    Type = \"" + appointment.getType() + "\",\n" +
                            "    Start = \"" + Time.convertToDBString(appointment.getStart()) + "\",\n" +
                            "    End = \"" + Time.convertToDBString(appointment.getEnd()) + "\",\n" +
                            "    Customer_ID = " + appointment.getCustomerID() + ",\n" +
                            "    User_ID = " + appointment.getUserID() + "\n" +
                            "WHERE appointments.Appointment_ID = " + appointment.getId() + ";";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addAppointment(Appointment appointment) {
        try {
            String sql =
                    "INSERT INTO appointments VALUES(" + appointment.getId() + ", '" + appointment.getTitle() +
                            "', '" + appointment.getDescription() + "', '" + appointment.getLocation() + "', " +
                            "'" + appointment.getType() + "', '" + Time.convertToDBString(appointment.getStart()) +
                            "', '" + Time.convertToDBString(appointment.getEnd()) + "', " +
                            "NOW(), '" + User.getId() + "', NOW(), '" + User.getId() +
                            "', " + appointment.getCustomerID() + ", " + appointment.getUserID() + ", " +
                            "(SELECT Contact_ID FROM contacts WHERE Contact_Name='" + appointment.getContact() + "'));\n";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Appointment getAppointment(int id) {

        try {
            String sql =
                    "SELECT appointments.Appointment_ID, appointments.Title, appointments.Description, " +
                            "appointments.Location,appointments.Type, contacts.Contact_Name, appointments.Start, " +
                            "appointments.End, appointments.Customer_ID, appointments.User_ID " +
                            "FROM appointments " +
                            "INNER JOIN contacts ON appointments.Contact_ID=contacts.Contact_ID " +
                            "WHERE Appointment_ID=" + String.valueOf(id) + ";";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int resultId = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                String contact = rs.getString("Contact_Name");
                String startString = rs.getString("Start");
                String endString = rs.getString("End");
                int customerID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");

                ZonedDateTime start = Time.parseString(startString);
                ZonedDateTime end = Time.parseString(endString);

                return new Appointment(resultId, title, description, location, type, start, end, customerID, userID, contact);
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void deleteAppointment(String id) {
        try {
            String sql =
                    "DELETE FROM appointments WHERE Appointment_ID=" + id;

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteAppointmentByCustomer(String id) {
        try {
            String sql =
                    "DELETE FROM appointments WHERE Customer_ID=" + id;

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet getAppointmentsByTypeAndMonth() {

        try {
            String sql = "SELECT type, count(type) AS total " +
                    "FROM appointments " +
                    "GROUP BY type " +
                    "UNION " +
                    "SELECT substring(Start, 6, 2), count(*) " +
                    "FROM appointments " +
                    "GROUP BY substring(Start, 6, 2);";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            return ps.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
