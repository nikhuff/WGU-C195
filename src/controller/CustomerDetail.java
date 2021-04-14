package controller;

import database.DBCountry;
import database.DBCustomer;
import database.DBDivision;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Customer;
import util.InvalidInputException;
import util.Language;
import util.SceneChange;
import util.Validate;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

public class CustomerDetail implements Initializable, Controller {

    private String viewURL;
    private Customer customer;
    private int id;

    @FXML
    private Label customerTitle;
    @FXML
    private Label idLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private Label zipcodeLabel;
    @FXML
    private Label divisionLabel;
    @FXML
    private Label countryLabel;
    @FXML
    private Label phoneLabel;
    @FXML
    private TextField idText;
    @FXML
    private TextField nameText;
    @FXML
    private TextField addressText;
    @FXML
    private TextField zipcodeText;
    @FXML
    private ChoiceBox<String> division;
    @FXML
    private ChoiceBox<String> country;
    @FXML
    private TextField phoneText;
    @FXML
    private Button delete;
    @FXML
    private Button cancelButton;
    @FXML
    private Button save;
    @FXML
    private Button update;

    public CustomerDetail() {
        String url = "../view/CustomerDetail.fxml";
        this.viewURL = url;
        id = ThreadLocalRandom.current().nextInt(1000,2000);
    }

    public CustomerDetail(Customer customer) {
        String url = "../view/CustomerDetail.fxml";
        this.viewURL = url;
        this.customer = customer;
    }

    @Override
    public String getViewURL() {
        return viewURL;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        division.setItems(DBDivision.getDivisions());
        country.setItems(DBCountry.getCountries());

        customerTitle.setText(Language.getField("Customer"));
        idLabel.setText(Language.getField("ID"));
        nameLabel.setText(Language.getField("Name"));
        addressLabel.setText(Language.getField("Address"));
        zipcodeLabel.setText(Language.getField("Zipcode"));
        divisionLabel.setText(Language.getField("Division"));
        countryLabel.setText(Language.getField("Country"));
        phoneLabel.setText(Language.getField("Phone"));
        delete.setText(Language.getField("Delete"));
        cancelButton.setText(Language.getField("Cancel"));
        save.setText(Language.getField("Save"));

        delete.setVisible(false);
        update.setVisible(false);
        idText.setText(String.valueOf(id));

        if (customer != null) {
            idText.setText(String.valueOf(customer.getId()));
            nameText.setText(customer.getName());
            addressText.setText(customer.getAddress());
            zipcodeText.setText(customer.getZipcode());
            zipcodeText.setText(customer.getZipcode());
            country.setValue(customer.getCountry());
            division.setValue(customer.getDivision());
            phoneText.setText(customer.getPhone());

            delete.setVisible(true);
            update.setVisible(true);
            save.setVisible(false);
        }
    }

    private void returnHome(ActionEvent event) {
        SceneChange sc = new SceneChange((Stage)((Node)event.getSource()).getScene().getWindow(), "../view/home.fxml");
        sc.changeScene(Language.getField("Stage Title"), 500, 790, 200, 790);
    }

    public void cancel(ActionEvent event) {
        returnHome(event);
    }

    public void filterDivisions(ActionEvent event) {
        System.out.println(country.getValue());
        ObservableList<String> divisions = DBDivision.getDivisions(country.getValue());
        division.setItems(divisions);
        // set default value so it's populated
        division.setValue(divisions.get(0));
    }

    private Customer prepareCustomer() throws InvalidInputException {
        int id;
        try {
            id = Validate.validateCustomerID(idText);
        } catch (InvalidInputException e) {
            throw e;
        }

        String name = nameText.getText();
        String address = addressText.getText();
        String zipcode = zipcodeText.getText();
        String phone = phoneText.getText();
        String countryValue = country.getValue();
        String divisionValue = division.getValue();

        return new Customer(id, name, address, zipcode, phone, countryValue, divisionValue);
    }

    public void updateCustomer(ActionEvent event) {

        System.out.println("updating customer...");
        Customer customer;
        try {
            customer = prepareCustomer();
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
            return;
        }
        DBCustomer.updateCustomer(customer);
        returnHome(event);
    }

    public void saveCustomer(ActionEvent event) {

        System.out.println("saving customer...");
        Customer customer;
        try {
            customer = prepareCustomer();
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
            return;
        }
        DBCustomer.addCustomer(customer);
        returnHome(event);
    }

    public void deleteCustomer(ActionEvent event) {
        String id = idText.getText();
//        DBAppointment.deleteAppointment(id);
        DBCustomer.deleteCustomer(id);
        returnHome(event);
    }
}
