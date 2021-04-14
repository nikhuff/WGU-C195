package util;

import database.DBAppointment;
import database.DBCustomer;
import database.DBUser;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.Appointment;
import model.Customer;

import java.time.*;

public class Validate {

    private static final String invalid = "-fx-border-color: red;";

    public static int validateID(TextField field) throws InvalidInputException {
        int id;
        String value = field.getText();
        try {
            id = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            field.setStyle(invalid);
            throw new InvalidInputException("Invalid ID");
        }
        return id;
    }

    public static int validateUserID(TextField field) throws InvalidInputException {
        int id = validateID(field);
        ObservableList<Integer> userIds = DBUser.getUsers();
        boolean validId = false;
        for (int u : userIds) {
            if (u == id) {
                validId = true;
                break;
            }
        }
        if (!validId) {
            field.setStyle(invalid);
            throw new InvalidInputException("User ID does not exist");
        }
        field.setStyle(null);
        return id;
    }

    public static int validateCustomerID(TextField field) throws InvalidInputException {
        int id = validateID(field);
        ObservableList<Customer> customers = DBCustomer.getCustomers();
        boolean validId = false;
        for (Customer c : customers) {
            if (c.getId() == id) {
                validId = true;
                break;
            }
        }
        if (!validId) {
            field.setStyle(invalid);
            throw new InvalidInputException("Customer ID does not exist");
        }
        field.setStyle(null);
        return id;
    }

    public static String requiredField(TextField field) throws  InvalidInputException {
        String value = field.getText();
        if (value.isEmpty()) {
            field.setStyle(invalid);
            throw new InvalidInputException("Required Field");
        } else {
            field.setStyle(null);
            return value;
        }
    }

    public static String requiredField(ChoiceBox<String> box) throws  InvalidInputException {
        String value = box.getValue();
        if (value == null) {
            box.setStyle(invalid);
            throw new InvalidInputException("Choose an Option");
        } else {
            box.setStyle(null);
            return value;
        }
    }

    public static LocalTime requiredTime(ChoiceBox<LocalTime> hourBox) throws InvalidInputException {
        LocalTime hour = hourBox.getValue();
        if (hour == null) {
            hourBox.setStyle(invalid);
            throw new InvalidInputException("Choose an Option");
        } else {
            hourBox.setStyle(null);
            return hour;
        }
    }

    public static LocalDate requiredDate(DatePicker datePicker) throws InvalidInputException {
        LocalDate date = datePicker.getValue();
        if (date == null) {
            datePicker.setStyle(invalid);
            throw new InvalidInputException("Required Field");
        } else {
            datePicker.setStyle(null);
            return date;
        }
    }

    public static void checkOverlap(ZonedDateTime startZonedDateTime, String contact, int customerId, int appointmentId) throws InvalidInputException {
        ObservableList<Appointment> appointments = DBAppointment.getAppointments();
        for (Appointment appointment : appointments) {
            ZonedDateTime start = appointment.getStart();
            ZonedDateTime end = appointment.getEnd();

            if ((startZonedDateTime.isAfter(start) || startZonedDateTime.isEqual(start)) && startZonedDateTime.isBefore(end) && appointment.getId() != appointmentId) {
                if (contact.matches(appointment.getContact())) {
                    throw new InvalidInputException("The contact already has an appointment from " +
                            start.toLocalTime() + " to " + end.toLocalTime() + " on " + start.toLocalDate());
                } else if (appointment.getCustomerID() == customerId) {
                    throw new InvalidInputException("The customer already has an appointment from " +
                            start.toLocalTime() + " to " + end.toLocalTime() + " on " + start.toLocalDate());
                }
            }
        }
    }

    public static void checkTimeOrder(ZonedDateTime start, ZonedDateTime end) throws InvalidInputException {
        if (start.isAfter(end)) {
            throw new InvalidInputException("Start time must be before end time");
        } else if (start.isEqual(end)) {
            throw new InvalidInputException("End time must not equal start time");
        }
    }
}
