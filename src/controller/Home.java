package controller;

import database.DBAppointment;
import database.DBCustomer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointment;
import model.Customer;
import util.Language;
import util.SceneChange;
import util.Time;

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
    private TableColumn date;
    @FXML
    private TableColumn start;
    @FXML
    private TableColumn end;
    @FXML
    private TableColumn customerIDA;
    @FXML
    private Label filterBy;
    @FXML
    private RadioButton all;
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

    @FXML
    private TableView appointmentTable;
    @FXML
    private TableView customerTable;

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
        date.setText(Language.getField("Date"));
        start.setText(Language.getField("Start Time"));
        end.setText(Language.getField("End Time"));
        customerIDA.setText(Language.getField("Customer ID"));
        filterBy.setText(Language.getField("Filter By"));
        all.setText(Language.getField("All"));
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
        tabPane.widthProperty().addListener((observable, oldValue, newValue) -> {
            tabPane.setTabMinWidth((tabPane.getWidth() / tabPane.getTabs().size()) - 15);
            tabPane.setTabMaxWidth((tabPane.getWidth() / tabPane.getTabs().size()) - 15);
        });

        appointmentTable.setItems(DBAppointment.getAppointments());
        customerTable.setItems(DBCustomer.getCustomers());

        appointmentID.setCellValueFactory(new PropertyValueFactory<>("id"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        location.setCellValueFactory(new PropertyValueFactory<>("location"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        start.setCellValueFactory(new PropertyValueFactory<>("startLocalTime"));
        end.setCellValueFactory(new PropertyValueFactory<>("endLocalTime"));
        contact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        customerIDA.setCellValueFactory(new PropertyValueFactory<>("customerID"));

        customerIDC.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        zipcode.setCellValueFactory(new PropertyValueFactory<>("zipcode"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        country.setCellValueFactory(new PropertyValueFactory<>("country"));
        division.setCellValueFactory(new PropertyValueFactory<>("division"));
    }

    public void addAppointment(ActionEvent event) {
        AppointmentDetail appointmentDetail = new AppointmentDetail();
        SceneChange sc = new SceneChange((Stage)((Node)event.getSource()).getScene().getWindow(), appointmentDetail);
        sc.changeScene(Language.getField("Stage Title"), 550, 350, 550, 330);
    }

    public void editAppointment(ActionEvent event) {
        Appointment appointment = (Appointment) appointmentTable.getSelectionModel().getSelectedItem();
        if (appointment == null) {
            System.out.println("nothing selected");
            return;
        }
        AppointmentDetail appointmentDetail = new AppointmentDetail(appointment);
        SceneChange sc = new SceneChange((Stage)((Node)event.getSource()).getScene().getWindow(), appointmentDetail);
        sc.changeScene(Language.getField("Stage Title"), 550, 350, 550, 330);
    }

    public void addCustomer(ActionEvent event) {
        CustomerDetail customerDetail = new CustomerDetail();
        SceneChange sc = new SceneChange((Stage)((Node)event.getSource()).getScene().getWindow(), customerDetail);
        sc.changeScene(Language.getField("Stage Title"), 450, 350, 550, 300);
    }

    public void editCustomer(ActionEvent event) {
        Customer customer = (Customer) customerTable.getSelectionModel().getSelectedItem();
        if (customer == null) {
            System.out.println("nothing selected");
            return;
        }
        CustomerDetail customerDetail = new CustomerDetail(customer);
        SceneChange sc = new SceneChange((Stage)((Node)event.getSource()).getScene().getWindow(), customerDetail);
        sc.changeScene(Language.getField("Stage Title"), 450, 350, 400, 300);
    }

    public void filterAppointments() {
        if (all.isSelected()) {
            appointmentTable.setItems(DBAppointment.getAppointments());
        } else if (week.isSelected()) {
            appointmentTable.setItems(Time.filterWeek(DBAppointment.getAppointments()));
        } else if (month.isSelected()) {
            appointmentTable.setItems(Time.filterMonth(DBAppointment.getAppointments()));
        }
    }
}
