package util;

import controller.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class SceneChange {

    private String viewUrl;
    private Stage stage;
    private Controller controller;

    public SceneChange(Stage stage, String url) {
        this.stage = stage;
        this.viewUrl = url;
    }

    public SceneChange(Stage stage, Controller controller) {
        this.stage = stage;
        this.controller = controller;
    }

    public void changeScene(String title, int height, int width) {
        Parent root = null;
        try {
            if (controller != null) {
                String url = controller.getViewURL();
                FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
                loader.setController(controller);
                root = loader.load();
            } else {
                root = new FXMLLoader(getClass().getResource(viewUrl)).load();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root, width, height);
        scene.getStylesheets().add("styles/styles.css");
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
}
