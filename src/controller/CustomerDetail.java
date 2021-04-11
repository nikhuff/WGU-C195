package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import util.Language;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerDetail implements Initializable {

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
    private Button delete;
    @FXML
    private Button cancel;
    @FXML
    private Button save;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerTitle.setText(Language.getField("Customer"));
        idLabel.setText(Language.getField("ID"));
        nameLabel.setText(Language.getField("Name"));
        addressLabel.setText(Language.getField("Address"));
        zipcodeLabel.setText(Language.getField("Zipcode"));
        divisionLabel.setText(Language.getField("Division"));
        countryLabel.setText(Language.getField("Country"));
        phoneLabel.setText(Language.getField("Phone"));
        delete.setText(Language.getField("Delete"));
        cancel.setText(Language.getField("Cancel"));
        save.setText(Language.getField("Save"));
    }
}
