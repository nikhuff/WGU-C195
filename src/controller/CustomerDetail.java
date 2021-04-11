package controller;

import database.DBCountry;
import database.DBDivision;
import javafx.beans.value.ObservableValue;
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
import util.Language;
import util.SceneChange;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerDetail implements Initializable, Controller {

    private String viewURL;
    private Customer customer;

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

    public CustomerDetail() {
        String url = "../view/CustomerDetail.fxml";
        this.viewURL = url;
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
        }
    }

    public void cancel(ActionEvent event) {
        SceneChange sc = new SceneChange((Stage)((Node)event.getSource()).getScene().getWindow(), "../view/home.fxml");
        sc.changeScene(Language.getField("Stage Title"), 500, 720);
    }

    public void filterDivisions(ActionEvent event) {
        System.out.println(country.getValue());
        ObservableList<String> divisions = DBDivision.getDivisions(country.getValue());
        division.setItems(divisions);
        // set default value so it's populated
        division.setValue(divisions.get(0));
    }
}
