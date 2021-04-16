package util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.Appointment;

import java.util.Optional;

public class DialogBox {

    public static void improperInput(String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(Language.getField("Improper Input"));
        alert.setHeaderText(Language.getField("Input Error"));
        alert.setContentText(Language.getField(content));

        alert.show();
    }

    public static void invalidCredentials() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(Language.getField("Invalid Credentials"));
        alert.setHeaderText(Language.getField("Invalid username or password"));
        alert.setContentText(Language.getField("Check username and/or password and try again."));

        alert.show();
    }

    public static boolean deleteConfirmation() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(Language.getField("Delete"));
        alert.setHeaderText(Language.getField("Are you sure?"));
        alert.setContentText(Language.getField("Click ok to confirm"));

        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.OK;
    }

    public static void deleteNotificationCustomer(String id) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(Language.getField("Delete"));
        alert.setHeaderText(Language.getField("Deletion Occurred"));
        alert.setContentText(Language.getField("Customer") + " " + id + " " +
                Language.getField("deleted"));

        alert.show();
    }

    public static void appointmentAlert(Appointment appointment) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(Language.getField("Appointment Alert"));
        if (appointment != null) {
            alert.setHeaderText(Language.getField("Appointment Soon"));
            alert.setContentText(Language.getField("Appointment ID") + ": " + appointment.getId() + "\n" +
                    Language.getField("Time") + ": " + appointment.getStart().toLocalTime() + "\n" +
                    Language.getField("Date") + ": " + appointment.getStart().toLocalDate());
        } else {
            alert.setHeaderText(Language.getField("No upcoming appointments"));
            alert.setContentText(Language.getField("No upcoming appointments"));
        }

        alert.show();
    }

    public static void deleteNotificationAppointment(Appointment app) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(Language.getField("Delete"));
        alert.setHeaderText(Language.getField("Appointment Deleted"));
        alert.setContentText(Language.getField("Appointment") + ": " + app.getId() + "\n" +
                Language.getField("Type") + ": " + app.getType());

        alert.show();
    }
}
