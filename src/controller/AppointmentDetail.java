package controller;

import database.DBAppointment;
import database.DBContact;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Appointment;
import util.InvalidInputException;
import util.Language;
import util.SceneChange;
import util.Validate;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

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
    private TextField startText;
    @FXML
    private TextField endText;
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
        String url = "../view/AppointmentDetail.fxml";
        this.viewURL = url;
        id = ThreadLocalRandom.current().nextInt(1000,2000);
    }

    public AppointmentDetail(Appointment appointment) {
        String url = "../view/AppointmentDetail.fxml";
        this.viewURL = url;
        this.appointment = appointment;
    }

    @Override
    public String getViewURL() {
        return viewURL;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        contact.setItems(DBContact.getContacts());

        appointmentTitle.setText(Language.getField("Appointment"));
        idLabel.setText(Language.getField("ID"));
        titleLabel.setText(Language.getField("Title"));
        locationLabel.setText(Language.getField("Location"));
        descriptionLabel.setText(Language.getField("Description"));
        contactLabel.setText(Language.getField("Contact"));
        typeLabel.setText(Language.getField("Type"));
        startLabel.setText(Language.getField("Start Time"));
        endLabel.setText(Language.getField("End Time"));
        customerLabel.setText(Language.getField("Customer ID"));
        userLabel.setText(Language.getField("User ID"));
        delete.setText(Language.getField("Delete"));
        cancelButton.setText(Language.getField("Cancel"));
        save.setText(Language.getField("Save"));

        delete.setVisible(false);
        update.setVisible(false);

        idText.setText(String.valueOf(id));

        if (appointment != null) {
            idText.setText(String.valueOf(appointment.getId()));
            titleText.setText(appointment.getTitle());
            descriptionText.setText(appointment.getDescription());
            locationText.setText(appointment.getLocation());
            typeText.setText(appointment.getType());
            contact.setValue(appointment.getContact());
            startText.setText(appointment.getStart());
            endText.setText(appointment.getEnd());
            customerText.setText(String.valueOf(appointment.getCustomerID()));
            userText.setText(String.valueOf(appointment.getUserID()));

            delete.setVisible(true);
            save.setVisible(false);
            update.setVisible(true);
        }

        customerText.focusedProperty().addListener((obs, oldVal, newVal) -> {
            try {
                Validate.validateID(customerText);
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        });

        userText.focusedProperty().addListener((obs, oldVal, newVal) -> {
            try {
                Validate.validateID(userText);
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        });
    }

    private void returnHome(ActionEvent event) {
        SceneChange sc = new SceneChange((Stage)((Node)event.getSource()).getScene().getWindow(), "../view/home.fxml");
        sc.changeScene(Language.getField("Stage Title"), 500, 720);
    }

    public void cancel(ActionEvent event) {
        returnHome(event);
    }

    private Appointment prepareAppointment() throws InvalidInputException {
        int id;
        int customerID;
        int userID;
        String start = startText.getText();
        String end = endText.getText();

        try {
            id = Validate.validateID(idText);
            customerID = Validate.validateID(customerText);
            userID = Validate.validateID(userText);
        } catch (InvalidInputException e) {
            throw e;
        }

        String title = titleText.getText();
        String description = descriptionText.getText();
        String location = locationText.getText();
        String type = typeText.getText();
        String contactName = contact.getValue();

        return new Appointment(id, title, description, location, type, start, end, customerID, userID, contactName);
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
