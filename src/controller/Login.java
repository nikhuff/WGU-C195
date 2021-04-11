package controller;

import database.DBUser;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;
import util.Language;
import util.SceneChange;

import java.io.IOException;
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
    }

    public void signIn(ActionEvent actionEvent) {

        User user = DBUser.authenticateUser(this.providedUsername.getText(), this.providedPassword.getText());
        if (user != null) {
            System.out.println("Success! Welcome " + user.getUsername() + "!");
            SceneChange sc = new SceneChange((Stage)((Node)actionEvent.getSource()).getScene().getWindow(), "../view/home.fxml");
            sc.changeScene(Language.getField("Stage Title"), 500, 500);
        } else {
            System.out.println("Invalid user name or password");
        }
    }
}
