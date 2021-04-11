package controller;

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
import util.Language;
import util.SceneChange;

import java.net.URL;
import java.util.ResourceBundle;

public class AppointmentDetail implements Initializable, Controller {

    private String viewURL;
    private Appointment appointment;

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
    }

    public void cancel(ActionEvent event) {
        SceneChange sc = new SceneChange((Stage)((Node)event.getSource()).getScene().getWindow(), "../view/home.fxml");
        sc.changeScene(Language.getField("Stage Title"), 500, 720);
    }

    public void updateAppointment(ActionEvent event) {
        System.out.println("updating appointment...");
    }

    public void saveAppointment(ActionEvent event) {
        System.out.println("saving appointment...");
    }
}
