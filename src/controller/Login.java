package controller;

import database.DBUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;
import util.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Login implements Initializable {
    @FXML
    private Label loginTitle;
    @FXML
    private Label username;
    @FXML
    private Label password;
    @FXML
    private Label zoneId;
    @FXML
    private Button signIn;
    @FXML
    private TextField providedUsername;
    @FXML
    private TextField providedPassword;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginTitle.setText(Language.getField("Login Title"));
        username.setText(Language.getField("Username"));
        password.setText(Language.getField("Password"));
        signIn.setText(Language.getField("Sign In"));
        zoneId.setText(Time.getZoneId().toString());
    }

    public void signIn(ActionEvent actionEvent) {

        User user = DBUser.authenticateUser(providedUsername.getText(), providedPassword.getText());
        boolean success = user != null;
        Logger.logAttempt(providedUsername.getText(), success);
        if (success) {
            System.out.println("Success! Welcome " + user.getUsername() + "!");
            SceneChange sc = new SceneChange((Stage)((Node)actionEvent.getSource()).getScene().getWindow(), "../view/home.fxml");
            sc.changeScene(Language.getField("Stage Title"), 500, 500, 500, 500);
        } else {
            System.out.println("Invalid user name or password");
            DialogBox.invalidCredentials();
        }
    }
}
