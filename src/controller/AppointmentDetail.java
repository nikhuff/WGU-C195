package controller;

import database.DBAppointment;
import database.DBContact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Appointment;
import util.*;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class AppointmentDetail implements Initializable, Controller {

    private String viewURL;
    private Appointment appointment;
    private int id;

    @FXML
    private Label appointmentTitle;
    @FXML
    private Label idLabel;
    @FXML
    private Label titleLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private Label locationLabel;
    @FXML
    private Label contactLabel;
    @FXML
    private Label typeLabel;
    @FXML
    private Label startDate;
    @FXML
    private Label endDate;
    @FXML
    private Label startLabel;
    @FXML
    private Label endLabel;
    @FXML
    private Label customerLabel;
    @FXML
    private Label userLabel;
    @FXML
    private TextField idText;
    @FXML
    private TextField titleText;
    @FXML
    private TextField descriptionText;
    @FXML
    private TextField locationText;
    @FXML
    private ChoiceBox<String> contact;
    @FXML
    private TextField typeText;
    @FXML
    private ChoiceBox<LocalTime> startHour;
    @FXML
    private ChoiceBox<String> startMin;
    @FXML
    private ChoiceBox<LocalTime> endHour;
    @FXML
    private ChoiceBox<String> endMin;
    @FXML
    private DatePicker startDateText;
    @FXML
    private DatePicker endDateText;
    @FXML
    private TextField customerText;
    @FXML
    private TextField userText;
    @FXML
    private Button delete;
    @FXML
    private Button cancelButton;
    @FXML
    private Button save;
    @FXML
    private Button update;

    public AppointmentDetail() {
        this.viewURL = "../view/AppointmentDetail.fxml";
        this.id = ThreadLocalRandom.current().nextInt(1000,2000);
    }

    public AppointmentDetail(Appointment appointment) {
        this.viewURL = "../view/AppointmentDetail.fxml";
        this.appointment = appointment;
        this.id = appointment.getId();
    }

    @Override
    public String getViewURL() {
        return viewURL;
    }

    private void initializeTimes() {
        int[] hours = IntStream.rangeClosed(Time.getOpen(), Time.getClose() - 1).toArray();
        String[] minutes = {":0", ":15", ":30", ":45"};

        ObservableList<LocalTime> hoursStart = FXCollections.observableArrayList();
        ObservableList<LocalTime> hoursEnd = FXCollections.observableArrayList();

        ObservableList<String> minutesStart = FXCollections.observableArrayList();
        ObservableList<String> minutesEnd = FXCollections.observableArrayList();

        for (int i : hours) {
            LocalTime time = LocalTime.of(i, 0);
            hoursStart.add(time);
            hoursEnd.add(time);
        }

        for (String i : minutes) {
            minutesStart.add(i);
            minutesEnd.add(i);
        }

        startHour.setItems(hoursStart);
        endHour.setItems(hoursStart);
        startMin.setItems(minutesStart);
        endMin.setItems(minutesEnd);
    }

    private void addListeners() {
        customerText.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal){
                try {
                    Validate.validateCustomerID(customerText);
                } catch (InvalidInputException e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        userText.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal){
                try {
                    Validate.validateUserID(userText);
                } catch (InvalidInputException e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        titleText.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal){
                try {
                    Validate.requiredField(titleText);
                } catch (InvalidInputException e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        descriptionText.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal){
                try {
                    Validate.requiredField(descriptionText);
                } catch (InvalidInputException e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        locationText.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal){
                try {
                    Validate.requiredField(locationText);
                } catch (InvalidInputException e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        contact.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal){
                try {
                    Validate.requiredField(contact);
                } catch (InvalidInputException e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        typeText.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal){
                try {
                    Validate.requiredField(typeText);
                } catch (InvalidInputException e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        startDateText.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal){
                try {
                    Validate.requiredDate(startDateText);
                } catch (InvalidInputException e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        endDateText.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal){
                try {
                    Validate.requiredDate(endDateText);
                } catch (InvalidInputException e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        startHour.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal){
                try {
                    Validate.requiredTime(startHour);
                } catch (InvalidInputException e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        endHour.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal){
                try {
                    Validate.requiredTime(endHour);
                } catch (InvalidInputException e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        startMin.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal){
                try {
                    Validate.requiredField(startMin);
                } catch (InvalidInputException e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        endMin.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal){
                try {
                    Validate.requiredField(endMin);
                } catch (InvalidInputException e) {
                    System.out.println(e.getMessage());
                }
            }
        });
    }

    private void initLanguage() {

        appointmentTitle.setText(Language.getField("Appointment"));
        idLabel.setText(Language.getField("ID"));
        titleLabel.setText(Language.getField("Title"));
        locationLabel.setText(Language.getField("Location"));
        descriptionLabel.setText(Language.getField("Description"));
        contactLabel.setText(Language.getField("Contact"));
        typeLabel.setText(Language.getField("Type"));
        startDate.setText(Language.getField("Start Date"));
        endDate.setText(Language.getField("End Date"));
        startLabel.setText(Language.getField("Start Time"));
        endLabel.setText(Language.getField("End Time"));
        customerLabel.setText(Language.getField("Customer ID"));
        userLabel.setText(Language.getField("User ID"));
        delete.setText(Language.getField("Delete"));
        cancelButton.setText(Language.getField("Cancel"));
        save.setText(Language.getField("Save"));
    }

    private void initializeAppointment() {

        titleText.setText(appointment.getTitle());
        descriptionText.setText(appointment.getDescription());
        locationText.setText(appointment.getLocation());
        typeText.setText(appointment.getType());
        contact.setValue(appointment.getContact());
        startDateText.setValue(appointment.getStart().toLocalDate());
        endDateText.setValue(appointment.getEnd().toLocalDate());
        startHour.setValue(appointment.getStart().toLocalTime());
        endHour.setValue(appointment.getEnd().toLocalTime());
        startMin.setValue(Time.getMinuteString(appointment.getStart().toLocalTime()));
        endMin.setValue(Time.getMinuteString(appointment.getEnd().toLocalTime()));
        customerText.setText(String.valueOf(appointment.getCustomerID()));
        userText.setText(String.valueOf(appointment.getUserID()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        contact.setItems(DBContact.getContacts());
        initializeTimes();
        initLanguage();

        delete.setVisible(false);
        update.setVisible(false);

        idText.setText(String.valueOf(id));

        if (appointment != null) {
            initializeAppointment();
            delete.setVisible(true);
            save.setVisible(false);
            update.setVisible(true);
        }

        addListeners();
    }

    private void returnHome(ActionEvent event) {
        SceneChange sc = new SceneChange((Stage)((Node)event.getSource()).getScene().getWindow(), "../view/home.fxml");
        sc.changeScene(Language.getField("Stage Title"), 500, 790, 200, 790);
    }

    public void cancel(ActionEvent event) {
        returnHome(event);
    }

    private Appointment prepareAppointment() throws InvalidInputException {

        int id = Validate.validateID(idText);
        int customerID = Validate.validateCustomerID(customerText);
        int userID = Validate.validateUserID(userText);
        String title = Validate.requiredField(titleText);
        String description = Validate.requiredField(descriptionText);
        String location = Validate.requiredField(locationText);
        String type = Validate.requiredField(typeText);
        String contactName = Validate.requiredField(contact);

        int minS = Time.getMinutes(Validate.requiredField(startMin));
        int minE = Time.getMinutes(Validate.requiredField(endMin));
        LocalTime start = Validate.requiredTime(startHour).withMinute(minS);
        LocalTime end = Validate.requiredTime(endHour).withMinute(minE);
        LocalDate startDate = Validate.requiredDate(startDateText);
        LocalDate endDate = Validate.requiredDate(endDateText);

        ZonedDateTime startZonedDateTime = Time.getZonedDateTime(startDate, start, Time.getZoneId());
        ZonedDateTime endZonedDateTime = Time.getZonedDateTime(endDate, end, Time.getZoneId());

        Validate.checkOverlap(startZonedDateTime, contactName, customerID, this.id);
        Validate.checkTimeOrder(startZonedDateTime, endZonedDateTime);

        return new Appointment(id, title, description, location, type, startZonedDateTime, endZonedDateTime, customerID, userID, contactName);
    }

    public void updateAppointment(ActionEvent event) {

        System.out.println("updating appointment...");
        Appointment appointment;
        try {
            appointment = prepareAppointment();
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
            return;
        }
        DBAppointment.updateAppointment(appointment);
        returnHome(event);
    }

    public void saveAppointment(ActionEvent event) {

        System.out.println("saving appointment...");
        Appointment appointment;
        try {
            appointment = prepareAppointment();
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
            return;
        }
        DBAppointment.addAppointment(appointment);
        returnHome(event);
    }

    public void deleteAppointment(ActionEvent event) {
        String id = idText.getText();
        DBAppointment.deleteAppointment(id);
        returnHome(event);
    }
}
