package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import util.Language;

import java.net.URL;
import java.util.ResourceBundle;

public class AppointmentDetail implements Initializable {

    @FXML
    private Label appointmentTitle;
    @FXML
    private Label idLabel;
    @FXML
    private Label titleLabel;
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
    private Button delete;
    @FXML
    private Button cancel;
    @FXML
    private Button save;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        appointmentTitle.setText(Language.getField("Appointment"));
        idLabel.setText(Language.getField("ID"));
        titleLabel.setText(Language.getField("Title"));
        locationLabel.setText(Language.getField("Location"));
        contactLabel.setText(Language.getField("Contact"));
        typeLabel.setText(Language.getField("Type"));
        startLabel.setText(Language.getField("Start Time"));
        endLabel.setText(Language.getField("End Time"));
        customerLabel.setText(Language.getField("Customer ID"));
        userLabel.setText(Language.getField("User ID"));
        delete.setText(Language.getField("Delete"));
        cancel.setText(Language.getField("Cancel"));
        save.setText(Language.getField("Save"));
    }
}
