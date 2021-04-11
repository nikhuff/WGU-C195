package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import util.Language;

import java.net.URL;
import java.util.ResourceBundle;

public class Home implements Initializable {
    // Appointments tab
    @FXML
    private Tab appointments;
    @FXML
    private Tab customers;
    @FXML
    private Tab reports;
    @FXML
    private TabPane tabPane;
    @FXML
    private TableColumn appointmentID;
    @FXML
    private TableColumn title;
    @FXML
    private TableColumn description;
    @FXML
    private TableColumn location;
    @FXML
    private TableColumn contact;
    @FXML
    private TableColumn type;
    @FXML
    private TableColumn start;
    @FXML
    private TableColumn end;
    @FXML
    private TableColumn customerIDA;
    @FXML
    private Label filterBy;
    @FXML
    private RadioButton week;
    @FXML
    private RadioButton month;
    @FXML
    private Button addAppointment;
    @FXML
    private Button editAppointment;

    // Customers tab
    @FXML
    private TableColumn customerIDC;
    @FXML
    private TableColumn name;
    @FXML
    private TableColumn address;
    @FXML
    private TableColumn zipcode;
    @FXML
    private TableColumn division;
    @FXML
    private TableColumn country;
    @FXML
    private TableColumn phone;
    @FXML
    private Button addCustomer;
    @FXML
    private Button editCustomer;
    @FXML
    private Button generate;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // appointments tab
        appointments.setText(Language.getField("Appointments"));
        appointmentID.setText(Language.getField("Appointment ID"));
        title.setText(Language.getField("Title"));
        description.setText(Language.getField("Description"));
        location.setText(Language.getField("Location"));
        contact.setText(Language.getField("Contact"));
        type.setText(Language.getField("Type"));
        start.setText(Language.getField("Start Time"));
        end.setText(Language.getField("End Time"));
        customerIDA.setText(Language.getField("Customer ID"));
        filterBy.setText(Language.getField("Filter By"));
        week.setText(Language.getField("Week"));
        month.setText(Language.getField("Month"));
        addAppointment.setText(Language.getField("Add"));
        editAppointment.setText(Language.getField("Edit"));

        // customers tab
        customers.setText(Language.getField("Customers"));
        customerIDC.setText(Language.getField("Customer ID"));
        name.setText(Language.getField("Name"));
        address.setText(Language.getField("Address"));
        zipcode.setText(Language.getField("Zipcode"));
        division.setText(Language.getField("Division"));
        country.setText(Language.getField("Country"));
        phone.setText(Language.getField("Phone"));
        addCustomer.setText(Language.getField("Add"));
        editCustomer.setText(Language.getField("Edit"));

        // reports tab
        reports.setText(Language.getField("Reports"));
        generate.setText(Language.getField("Generate"));

        // make the tabs pretty
        tabPane.widthProperty().addListener((observable, oldValue, newValue) ->
        {
            tabPane.setTabMinWidth((tabPane.getWidth() / tabPane.getTabs().size()) - 15);
            tabPane.setTabMaxWidth((tabPane.getWidth() / tabPane.getTabs().size()) - 15);
        });
    }
}
