package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import util.Language;

import java.net.URL;
import java.util.ResourceBundle;

public class Login implements Initializable {
    @FXML
    private Label loginTitle;
    @FXML
    private Label username;
    @FXML
    private Label password;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginTitle.setText(Language.getField("Login Title"));
        username.setText(Language.getField("Username"));
        password.setText(Language.getField("Password"));
    }
}
